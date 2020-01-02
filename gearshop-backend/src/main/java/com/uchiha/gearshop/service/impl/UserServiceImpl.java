package com.uchiha.gearshop.service.impl;

import com.uchiha.gearshop.common.dto.mapper.UserMapper;
import com.uchiha.gearshop.common.dto.model.UserDto;
import com.uchiha.gearshop.common.enums.ResultEnum;
import com.uchiha.gearshop.common.exception.CustomException;
import com.uchiha.gearshop.dao.entity.UserEntity;
import com.uchiha.gearshop.dao.repository.UserRepository;
import com.uchiha.gearshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto signup(UserDto userDto) {
        UserEntity user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
            if (userDto.getTypeOfficer().equals("admin")) {
                userDto.setTypeOfficer("admin");
            } else {
                userDto.setTypeOfficer("user");
            }
            user = new UserEntity()
                    .setUsername(userDto.getUsername())
                    .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .setBirthday(userDto.getBirthday())
                    .setTypeOfficer(userDto.getTypeOfficer());
            return UserMapper.toUserDto(userRepository.save(user));
        }
        throw new CustomException(ResultEnum.USER_NOT_FOUNT);
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByUsername(userDto.getUsername()));
        if (user.isPresent()) {
            UserEntity userModel = user.get();
            userModel.setFullname(userDto.getFullname())
                    .setBirthday(userDto.getBirthday());
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw new CustomException(ResultEnum.USER_NOT_FOUNT);
    }

    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByUsername(userDto.getUsername()));
        if (user.isPresent()) {
            UserEntity userModel = user.get();
            userModel.setPassword(newPassword);
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw new CustomException(ResultEnum.USER_NOT_FOUNT);
    }

    /**
     * Search an existing user
     *
     * @param username
     * @return
     */
    @Override
    public UserDto findByUsername(String username) {
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isPresent()) {
            return modelMapper.map(user.get().setPassword(null), UserDto.class);
        }
        System.out.println("User ::Username" + username);
        throw new CustomException(ResultEnum.USER_ALREADY_EXIT);
    }
}
