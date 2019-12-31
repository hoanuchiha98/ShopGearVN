package com.uchiha.gearshop.controller;

import com.uchiha.gearshop.common.util.Constants;
import com.uchiha.gearshop.dao.entity.UserEntity;
import com.uchiha.gearshop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/users")
@Api(value = "authenticate")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Xem danh sách User")
    @GetMapping(value = "/getAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getAll() {
        List<UserEntity> userEntityList = null;
        try {
            userEntityList = userService.getAll();

            return (userEntityList.size() > 0) ? new ResponseEntity<>(userEntityList, HttpStatus.ACCEPTED) : new ResponseEntity<>(Constants.ERR_MSG_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Constants.ERR_MSG_BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }
    }


}
