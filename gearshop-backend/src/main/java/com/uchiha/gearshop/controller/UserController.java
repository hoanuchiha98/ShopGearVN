package com.uchiha.gearshop.controller;

import com.uchiha.gearshop.common.dto.model.UserDto;
import com.uchiha.gearshop.common.dto.response.Response;
import com.uchiha.gearshop.common.util.Constants;
import com.uchiha.gearshop.controller.request.ChangePassRequest;
import com.uchiha.gearshop.controller.request.UserSignupRequest;
import com.uchiha.gearshop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping(value = "/api/users")
@Api(tags = {"users"})
public class UserController {
    @Autowired
    private UserService userService;

    //    @ApiOperation(value = "Xem danh sách User")
//    @GetMapping(value = "/getAll", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public Response getAll() {
//        Response response = null;
//        List<UserEntity> userEntityList = null;
//        try {
//            userEntityList = userService.getAll();
//            response = Response.ok().setPayload(userEntityList);
//        } catch (Exception e) {
//            response = Response.badRequest().setErrors(Constants.ERR_MSG_BAD_REQUEST);
//        }
//        // return
//        return response;
//    }
//
//    @ApiOperation(value = "Lấy thông tin người dùng theo id")
//    @GetMapping(value = "/getById/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public Response getById(@PathVariable("id") int id){
//        System.out.println("User ::User Id" + id);
//        Response response = null;
//        UserEntity userEntity = null;
//        try {
//            userEntity = userService.getById(id);
//            response = Response.ok().setPayload(userEntity);
//        } catch (Exception e) {
//            return Response.badRequest().setErrors(Constants.ERR_MSG_BAD_REQUEST);
//        }
//        // return
//        return response;
//    }
//    @ApiOperation(value = "Tạo mới người dùng")
//    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public Response create(@Valid @RequestBody UserRequestDTO userRequestDTO){
//        Response response = null;
//        try{
//            userService.create(userRequestDTO);
//            response = Response.ok();
//        }
//        catch (Exception e){
//            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
//        }
//        return response;
//    }
    @ApiOperation("Đăng kí")
    @PostMapping(value = "/signup", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        return Response.ok().setPayload(registerUser(userSignupRequest, "user"));
    }

    private UserDto registerUser(UserSignupRequest userSignupRequest, String isAdmin) {
        UserDto userDto = new UserDto()
                .setUsername(userSignupRequest.getUsername())
                .setPassword(userSignupRequest.getPassword())
                .setFullname(userSignupRequest.getFullname())
                .setBirthday(userSignupRequest.getBirthday())
                .setTypeOfficer(isAdmin);

        return userService.signup(userDto);
    }
    @ApiOperation(value = "Xem user theo username")
    @GetMapping(value = "/findByUsername/{username}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response findByUsername(@PathVariable("username") String username, Principal principal) {
        try {
            if (!principal.getName().equals(username)) throw new UsernameNotFoundException("Username not found");
            return Response.ok().setPayload(userService.findByUsername(username));
        } catch (Exception e) {
            return Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }

    }

    @ApiOperation(value = "Đổi mật khẩu")
    @PutMapping(value = "/changePassword", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Response changePassword(@ModelAttribute ChangePassRequest changePassRequest, Principal principal) {
        try {
            if (!principal.getName().equals(changePassRequest.getUsername()))
                throw new UsernameNotFoundException("Username not found");
            UserDto user = new UserDto()
                    .setPassword(changePassRequest.getOlf_password());
            return Response.ok().setPayload(userService.changePassword(user, changePassRequest.getNew_passwod()));
        } catch (Exception e) {
            return Response.badRequest().setErrors(Constants.ERR_MSG_BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Lấy thông tin user hiện tại")
    @GetMapping(value = "getCurrentProfile", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response getCurretnUser() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            UserDto currentUser = userService.findByUsername(userDetails.getUsername());
            return Response.ok().setPayload(currentUser);
        } catch (Exception e) {
            return Response.badRequest().setErrors(Constants.ERR_MSG_BAD_REQUEST);
        }
    }
}
