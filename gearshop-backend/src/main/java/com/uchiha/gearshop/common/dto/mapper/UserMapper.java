package com.uchiha.gearshop.common.dto.mapper;

import com.uchiha.gearshop.common.dto.model.UserDto;
import com.uchiha.gearshop.model.UserEntity;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public static UserDto toUserDto(UserEntity user) {
        return new UserDto()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setBirthday(user.getBirthday())
                .setTypeOfficer(user.getTypeOfficer());
    }
}
