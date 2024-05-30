package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.exceptions.UserException;
import org.alexkings.productdeliveryapi.model.RegisterReq;
import org.alexkings.productdeliveryapi.model.SignInReq;
import org.alexkings.productdeliveryapi.model.SignInRes;
import org.alexkings.productdeliveryapi.model.UserDto;

import java.util.List;

public interface UserService {
    SignInRes createAdminUser(SignInReq signInRes) throws UserException;

    UserDto createUser(RegisterReq registerReq);

    SignInRes updateAdminData(Long id, SignInReq signInReq);

    void deleteById(Long id);

    List<UserDto> getAllUsers();

    UserDto addCarrier(UserDto userDto);

    UserDto getUserById(Long userId);

    UserDto updateStatusByIdAndStatus(Long id, Boolean status);

    List<UserDto> getCarriersByPlaceName(String name);
}
