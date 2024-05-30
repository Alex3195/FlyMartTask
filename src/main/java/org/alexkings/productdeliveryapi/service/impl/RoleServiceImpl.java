package org.alexkings.productdeliveryapi.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.alexkings.productdeliveryapi.auth.JwtUtil;
import org.alexkings.productdeliveryapi.entities.Permissions;
import org.alexkings.productdeliveryapi.entities.Roles;
import org.alexkings.productdeliveryapi.model.PermissionDto;
import org.alexkings.productdeliveryapi.model.RoleDto;
import org.alexkings.productdeliveryapi.model.UserDto;
import org.alexkings.productdeliveryapi.repository.RolesRepository;
import org.alexkings.productdeliveryapi.service.RoleService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RolesRepository rolesRepository;
    private final JwtUtil jwtUtil;

    public RoleServiceImpl(RolesRepository rolesRepository, JwtUtil jwtUtil) {
        this.rolesRepository = rolesRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public RoleDto addRole(RoleDto role, HttpServletRequest request) {
        UserDto user = jwtUtil.parseToken(request);
        Roles roles = dtoToEntity(role, user);
        return entityToDto(rolesRepository.save(roles));
    }

    @Override
    public RoleDto updateRole(RoleDto role, Long id, HttpServletRequest request) {
        UserDto user = jwtUtil.parseToken(request);
        Optional<Roles> roles = rolesRepository.findById(id);
        if (roles.isPresent()) {
            Roles rolesEntity = dtoToEntity(role, user);
            rolesEntity.setId(id);
            rolesEntity.setCreatedDate(roles.get().getCreatedDate());
            rolesEntity.setUpdateDate(Date.from(Instant.now()));
            return entityToDto(rolesRepository.save(rolesEntity));
        }
        return null;
    }

    @Override
    public RoleDto getRole(Long id, HttpServletRequest request) {
        UserDto user = jwtUtil.parseToken(request);
        Optional<Roles> roles = rolesRepository.findById(id);
        return roles.map(this::entityToDto).orElse(null);
    }

    @Override
    public List<RoleDto> getRoles(HttpServletRequest request) {
        List<Roles> roles = rolesRepository.findAll();
        if (roles.isEmpty()) {
            return null;
        }
        return roles.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(Long id, HttpServletRequest request) {
        rolesRepository.deleteById(id);
    }

    private Roles dtoToEntity(RoleDto dto, UserDto user) {
        Roles entity = new Roles();
        entity.setId(dto.getId());
        entity.setRole(dto.getName());
        entity.setCreatedBy(user.getId());
        entity.setCreatedDate(Date.from(Instant.now()));
        entity.setUpdateDate(Date.from(Instant.now()));
        return entity;
    }

    private Permissions dtoToEntity(PermissionDto dto) {
        Permissions entity = new Permissions();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCreatedDate(Date.from(Instant.now()));
        entity.setUpdateDate(Date.from(Instant.now()));
        return entity;
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
