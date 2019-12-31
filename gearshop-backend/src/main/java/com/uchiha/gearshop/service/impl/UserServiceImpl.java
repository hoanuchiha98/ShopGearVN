package com.uchiha.gearshop.service.impl;

import com.uchiha.gearshop.common.dto.UserRequestDTO;
import com.uchiha.gearshop.dao.entity.UserEntity;
import com.uchiha.gearshop.dao.repository.UserRepository;
import com.uchiha.gearshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> userEntities = new ArrayList<>();
        try {
            for (UserEntity userEntity : userRepository.findAll()) {
                UserEntity userEntity1 = new UserEntity(userEntity.getId(), userEntity.getUsername(), userEntity.getFullname(), userEntity.getBirthday(), userEntity.getTypeOfficer());
                System.out.println("User::Item" + userEntity1);
                userEntities.add(userEntity1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("User::List" + userEntities.get(0).getPassword());
        return userEntities;
    }

    @Override
    public int create(UserRequestDTO userRequestDTO) {
        UserEntity userEntity = null;
        try {
            userEntity = new UserEntity(userRequestDTO.getUsername(), userRequestDTO.getPassword(), userRequestDTO.getFullname(), userRequestDTO.getBirthday(), userRequestDTO.getTypeOfficer());
            userRepository.save(userEntity);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int update(UserRequestDTO userRequestDTO) {
        UserEntity userEntity = null;
        try {
            userEntity = new UserEntity(userRequestDTO.getUsername(), userRequestDTO.getPassword(), userRequestDTO.getFullname(), userRequestDTO.getBirthday(), userRequestDTO.getTypeOfficer());
            userRepository.save(userEntity);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int delete(Long id) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.getOne(id);
            return (userEntity == null) ? 0 : 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public UserEntity getById(Long id) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.getOne(id);
        } catch (Exception e) {
            System.out.println("User create::Error" + e);
        }
        return userEntity;
    }
}
