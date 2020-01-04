package com.uchiha.gearshop.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignupRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String fullname;
    private Date birthday;

}
