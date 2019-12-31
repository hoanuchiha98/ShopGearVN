package com.uchiha.gearshop.controller;

import com.uchiha.gearshop.common.dto.Response;
import com.uchiha.gearshop.common.util.Constants;
import com.uchiha.gearshop.dao.entity.UserEntity;
import com.uchiha.gearshop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/users")
@Api(tags = {"users"})
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Xem danh sách User")
    @GetMapping(value = "/getAll", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy")
    })
    public Response getAll() {
        Response response = null;
        List<UserEntity> userEntityList = null;
        try {
            userEntityList = userService.getAll();

            response = (userEntityList.size() > 0) ? Response.ok().setPayload(userEntityList) : Response.notFound().setErrors(Constants.ERR_MSG_NOT_FOUND);
        } catch (Exception e) {
            response = Response.badRequest().setErrors(Constants.ERR_MSG_BAD_REQUEST);
        }
        // return
        return response;
    }

    @ApiOperation(value = "Lấy thông tin người dùng theo id")
    @GetMapping(value = "/getById/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response getById(@PathVariable("id") int id) {
        System.out.println("User ::User Id" + id);
        Response response = null;
        UserEntity userEntity = null;
        try {
            userEntity = userService.getById(id);
            response = (userEntity != null) ? Response.ok().setPayload(userEntity) : Response.notFound().setErrors(Constants.ERR_MSG_NOT_FOUND);
        } catch (Exception e) {
            response = Response.badRequest().setErrors(Constants.ERR_MSG_BAD_REQUEST);
        }
        // return
        return response;
    }

}
