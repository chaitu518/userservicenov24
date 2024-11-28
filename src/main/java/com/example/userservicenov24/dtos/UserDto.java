package com.example.userservicenov24.dtos;

import com.example.userservicenov24.models.Role;
import com.example.userservicenov24.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDto {
    private long id;
    private String name;
    private String email;
    private boolean isEmailVerified;
    @ManyToMany
    List<Role> roles;
}
