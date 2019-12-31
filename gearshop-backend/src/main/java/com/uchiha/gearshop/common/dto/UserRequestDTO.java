package com.uchiha.gearshop.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequestDTO {
    private int id;
    private String password;
    private String username;
    private String fullname;
    private Date birthday;
    private String typeOfficer;
}