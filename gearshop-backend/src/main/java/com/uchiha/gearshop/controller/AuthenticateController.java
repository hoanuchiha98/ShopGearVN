package com.uchiha.gearshop.controller;

import com.uchiha.gearshop.common.configuration.jwt.JwtTokenProvider;
import com.uchiha.gearshop.common.dto.model.UserDto;
import com.uchiha.gearshop.common.dto.response.LoginResponse;
import com.uchiha.gearshop.common.dto.response.Response;
import com.uchiha.gearshop.controller.request.LoginRequest;
import com.uchiha.gearshop.controller.request.UserSignupRequest;
import com.uchiha.gearshop.model.CustomUserDetails;
import com.uchiha.gearshop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = {"authenticate"})
public class AuthenticateController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Dăng nhập")
    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

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
        System.out.println("UserDTO::========" + userDto);
        return userService.signup(userDto);
    }
}
