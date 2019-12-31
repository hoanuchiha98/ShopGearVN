package com.uchiha.gearshop.common.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserResponseDTO {
    private int id;
    private String password;
    private String username;
    private String fullname;
    private Timestamp birthday;
    private String typeOfficer;
}
