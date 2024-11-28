package com.example.userservicenov24.controllers;

import com.example.userservicenov24.dtos.LoginRequestDto;
import com.example.userservicenov24.dtos.SignUpRequestDto;
import com.example.userservicenov24.dtos.TokenResponseDto;
import com.example.userservicenov24.dtos.UserDto;
import com.example.userservicenov24.models.Token;
import com.example.userservicenov24.models.User;
import com.example.userservicenov24.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        User user1 = userService.signUp(signUpRequestDto.getName(),signUpRequestDto.getEmail(),signUpRequestDto.getPassword());
        return user1.from();
    }
    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        Token token = userService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        return token.from();
    }
    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable String token){
       User user = userService.validateToken(token);
       return user.from();
    }
}
