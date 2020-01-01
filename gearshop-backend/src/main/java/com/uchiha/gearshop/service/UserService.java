package com.uchiha.gearshop.service;

import com.uchiha.gearshop.common.dto.model.UserDto;

public interface UserService {
    UserDto signup(UserDto userDto);

    UserDto updateProfile(UserDto userDto);

    UserDto changePassword(UserDto userDto, String newPassword);

    UserDto findByUsername(String username);
}
