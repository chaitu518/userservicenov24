package com.example.userservicenov24.security.services;

import com.example.userservicenov24.models.User;
import com.example.userservicenov24.repos.UserRepo;
import com.example.userservicenov24.security.models.CustomUserDetails;
import com.example.userservicenov24.services.UserService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@JsonDeserialize
@Service
public class CustomUserServices implements UserDetailsService {
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repo.findByEmail(username);
        if(user.isPresent()) {
            return new CustomUserDetails(user.get());
        }
        throw new UsernameNotFoundException(username);
    }
}
