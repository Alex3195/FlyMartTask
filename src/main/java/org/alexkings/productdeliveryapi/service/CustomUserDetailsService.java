package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.entities.Permissions;
import org.alexkings.productdeliveryapi.entities.Roles;
import org.alexkings.productdeliveryapi.entities.Users;
import org.alexkings.productdeliveryapi.model.CustomUser;
import org.alexkings.productdeliveryapi.model.PermissionDto;
import org.alexkings.productdeliveryapi.model.RoleDto;
import org.alexkings.productdeliveryapi.repository.RolesRepository;
import org.alexkings.productdeliveryapi.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final Map<String, PasswordEncoder> passwordEncoders;

    public CustomUserDetailsService(UserRepository userRepository, RolesRepository rolesRepository, Map<String, PasswordEncoder> passwordEncoders) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoders = passwordEncoders;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> usersOptional = userRepository.findByUsername(email);
        AtomicReference<Roles> roles = new AtomicReference<>();

        if (usersOptional.isEmpty()) {
            usersOptional = userRepository.findByEmail(email);
            usersOptional.ifPresent(user -> roles.set(rolesRepository.findByRole(user.getRole())));
        } else {
            roles.set(rolesRepository.findByRole(usersOptional.get().getRole()));
        }

        Users user = usersOptional.orElseGet(() -> {
            if (email.equalsIgnoreCase("Alex")) {
                Users defaultUser = new Users();
                defaultUser.setUsername(email);
                defaultUser.setRole("ADMIN");
                defaultUser.setPassword(new BCryptPasswordEncoder().encode("Alex!123"));
                defaultUser.setEncodingType("BCRYPT"); // Ensure this matches the key in your encoder map
                return defaultUser;
            }
            throw new UsernameNotFoundException(email);
        });
        PasswordEncoder encoder = new DelegatingPasswordEncoder(user.getEncodingType(), passwordEncoders);
        if (encoder == null) {
            throw new IllegalArgumentException("No encoder found for encryption type: " + user.getEncodingType());
        }
        RoleDto role = new RoleDto();
        if (roles.get() != null) {
            role.setName(roles.get().getRole());
        } else {
            role.setName("ADMIN");
            role.setId(1L);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        grantedAuthorities.addAll(role.getPermission() != null ? role.getPermission().stream().map(item -> new SimpleGrantedAuthority(item.getName())).toList() : new ArrayList<>());

        CustomUser userDetails = new CustomUser(user.getId(), role, user.getUsername(), user.getPassword(), grantedAuthorities);

        return userDetails;
    }

    private PermissionDto entityToDto(Permissions entity) {
        PermissionDto dto = new PermissionDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    private RoleDto entityToDto(Roles entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getRole());
        return dto;
    }
}
