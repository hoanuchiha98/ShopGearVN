package com.uchiha.gearshop.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tbuser")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -3629800334935029751L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;
    @Column(name = "password", nullable = true, length = 50)
    private String password;
    @Column(name = "username", nullable = true, length = 50)
    private String username;
    @Column(name = "fullname", nullable = true, length = 50)
    private String fullname;
    @Column(name = "birthday", nullable = true)
    private Date birthday;
    @Column(name = "typeOfficer", nullable = true, length = 50)
    private String typeOfficer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTypeOfficer() {
        return typeOfficer;
    }

    public void setTypeOfficer(String typeOfficer) {
        this.typeOfficer = typeOfficer;
    }

    public UserEntity(int id, String username, String fullname, Date birthday, String typeOfficer) {
        super();
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.birthday = birthday;
        this.typeOfficer = typeOfficer;
    }

    public UserEntity(String username, String password, String fullname, Date birthday, String typeOfficer) {
        super();
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birthday = birthday;
        this.typeOfficer = typeOfficer;
    }

}
