package com.uchiha.gearshop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class UserRequestDTO {
    private int id;
    private String password;
    private String username;
    private String fullname;
    private Timestamp birthday;
    private String typeOfficer;
}
