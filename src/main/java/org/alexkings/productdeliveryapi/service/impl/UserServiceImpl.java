package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.entities.BindCarriersToPlaces;
import org.alexkings.productdeliveryapi.entities.Place;
import org.alexkings.productdeliveryapi.entities.Users;
import org.alexkings.productdeliveryapi.exceptions.UserException;
import org.alexkings.productdeliveryapi.model.RegisterReq;
import org.alexkings.productdeliveryapi.model.SignInReq;
import org.alexkings.productdeliveryapi.model.SignInRes;
import org.alexkings.productdeliveryapi.model.UserDto;
import org.alexkings.productdeliveryapi.repository.BindCarriersToPlaceRepository;
import org.alexkings.productdeliveryapi.repository.PlaceRepository;
import org.alexkings.productdeliveryapi.repository.UserRepository;
import org.alexkings.productdeliveryapi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Map<String, PasswordEncoder> passwordEncoder;
    private final PlaceRepository placeRepository;
    private final BindCarriersToPlaceRepository bindCarriersToPlaceRepository;

    public UserServiceImpl(UserRepository userRepository, Map<String, PasswordEncoder> passwordEncoder, PlaceRepository placeRepository, BindCarriersToPlaceRepository bindCarriersToPlaceRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.placeRepository = placeRepository;
        this.bindCarriersToPlaceRepository = bindCarriersToPlaceRepository;
    }

    @Override
    public SignInRes createAdminUser(SignInReq signInRes) throws UserException {
        Users user = dtoToAdmin(signInRes);
        user.setImage(signInRes.getImage());
        try {
            Users e = userRepository.save(user);
            return entityToAdminRes(e);
        } catch (Exception e) {
            throw new UserException("username already exists");
        }
    }

    @Override
    public UserDto createUser(RegisterReq registerReq) {
        Users e = dtoToUser(registerReq);
        return entityToDto(userRepository.save(e));
    }

    @Override
    public SignInRes updateAdminData(Long id, SignInReq signInReq) {
        Users e = userRepository.getReferenceById(id);
        Users admin = dtoToAdmin(signInReq);
        admin.setId(e.getId());
        admin.setCreatedDate(e.getCreatedDate());
        admin = userRepository.save(admin);
        return entityToAdminRes(admin);
    }

    @Override
    public void deleteById(Long id) {
        Users e = userRepository.getReferenceById(id);
        e.setEnabled(false);
        userRepository.save(e);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public UserDto addCarrier(UserDto userDto) {
        Users e = dtoToEntity(userDto);
        e.setRole("CARRIER");
        return entityToDto(userRepository.save(e));
    }

    @Override
    public UserDto getUserById(Long userId) {
        return entityToDto(userRepository.getReferenceById(userId));
    }

    @Override
    public UserDto updateStatusByIdAndStatus(Long id, Boolean status) {
        Users e = userRepository.getReferenceById(id);
        e.setEnabled(status);
        e.setUpdateDate(Date.from(Instant.now()));
        e = userRepository.save(e);
        return entityToDto(e);
    }

    @Override
    public List<UserDto> getCarriersByPlaceName(String name) {
        Place place = placeRepository.findByName(name);
        if (place != null) {
            List<BindCarriersToPlaces> bindCarriersToPlaces = bindCarriersToPlaceRepository.findByPlaceIdAndRegionId(place.getId(), place.getRegion().getId());

        }

        return List.of();
    }

    private Users dtoToEntity(UserDto userDto) {
        Users e = new Users();
        e.setFirstname(userDto.getFirstName());
        e.setLastname(userDto.getLastName());
        e.setUsername(userDto.getUserName());
        e.setEmail(userDto.getEmail());
        e.setPassword(passwordEncoder.get("BCRYPT").encode(userDto.getPassword()));
        e.setImage(userDto.getImage());
        e.setCreatedDate(Date.from(Instant.now()));
        e.setEnabled(true);
        e.setUpdateDate(Date.from(Instant.now()));
        e.setEncodingType("BCRYPT");
        return e;
    }

    private SignInRes entityToAdminRes(Users user) {
        SignInRes res = new SignInRes();
        res.setId(user.getId());
        res.setEmail(user.getEmail());
        res.setUsername(user.getUsername());
        res.setFirstName(user.getFirstname());
        res.setLastName(user.getLastname());
        return res;
    }

    private Users dtoToAdmin(SignInReq signInReq) {
        Users user = new Users();
        user.setUsername(signInReq.getUsername());
        user.setFirstname(signInReq.getFirstName());
        user.setLastname(signInReq.getLastName());
        user.setEmail(signInReq.getEmail());
        user.setEncodingType(signInReq.getEncodingType());
        user.setPassword(passwordEncoder.get(signInReq.getEncodingType()).encode(signInReq.getPassword()));
        user.setRole("ADMIN");
        user.setCreatedDate(Date.from(Instant.now()));
        user.setUpdateDate(Date.from(Instant.now()));
        return user;
    }

    private Users dtoToUser(RegisterReq registerReq) {
        Users user = new Users();
        user.setUsername(registerReq.getUsername());
        user.setFirstname(registerReq.getFirstName());
        user.setLastname(registerReq.getLastName());
        user.setEmail(registerReq.getEmail());
        user.setPassword(passwordEncoder.get("BCRYPT").encode(registerReq.getPassword()));
        user.setEncodingType("BCRYPT");
        user.setRole("USER");
        user.setCreatedDate(Date.from(Instant.now()));
        user.setUpdateDate(Date.from(Instant.now()));
        return user;
    }

    private UserDto entityToDto(Users user) {
        UserDto userDto = new UserDto();
        user.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        userDto.setImage(userDto.getImage());
        return userDto;
    }
}
