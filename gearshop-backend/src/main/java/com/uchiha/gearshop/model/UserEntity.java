package com.uchiha.gearshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tbuser")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -3629800334935029751L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;
    @Column(name = "password")
    private String password;
    @Column(name = "username", nullable = true, length = 50)
    private String username;
    @Column(name = "fullname", nullable = true, length = 50)
    private String fullname;
    @Column(name = "birthday", nullable = true)
    private Date birthday;
    @Column(name = "typeOfficer", nullable = true, length = 50)
    private String typeOfficer;
}
