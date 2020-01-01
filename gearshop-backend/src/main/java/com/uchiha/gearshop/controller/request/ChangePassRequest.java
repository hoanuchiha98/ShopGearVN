package com.uchiha.gearshop.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePassRequest {
    @NotEmpty(message = "constraints.NotEmpty.message")
    private String username;
    @NotEmpty(message = "constraints.NotEmpty.message")
    private String olf_password;
    @NotEmpty(message = "constraints.NotEmpty.message")
    private String new_passwod;
}
