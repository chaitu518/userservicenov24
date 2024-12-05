package com.example.userservicenov24.models;

import com.example.userservicenov24.dtos.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    @Column(unique = true)
    private String email;
    private String hashedPassword;
    private boolean isEmailVerified;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;
    public UserDto from(){
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setName(name);
        userDto.setId(this.getId());
        userDto.setRoles(this.getRoles());
        return userDto;
    }
}
