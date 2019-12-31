package com.uchiha.gearshop.service;

import com.uchiha.gearshop.common.dto.UserRequestDTO;
import com.uchiha.gearshop.dao.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAll();

    int create(UserRequestDTO userRequestDTO);

    int update(UserRequestDTO userRequestDTO);

    int delete(Long id);

    UserEntity getById(Long id);
}
