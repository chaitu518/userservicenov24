package com.example.userservicenov24.services;

import com.example.userservicenov24.models.Token;
import com.example.userservicenov24.models.User;
import com.example.userservicenov24.repos.TokenRepo;
import com.example.userservicenov24.repos.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepo tokenRepo;
    public UserService(UserRepo userRepo,TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }
    public User signUp(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepo.save(user);
    }
    public Token login(String email, String password) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        Token token = generateToken(user);
        return tokenRepo.save(token);
    }
    private Token generateToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(9));
        token.setExpiryAt(System.currentTimeMillis() + 1000 * 60 * 60);
        return token;
    }
    public User validateToken(String token) {
        Optional<Token> optionalToken = tokenRepo.findByValueAndDeletedAndExpiryAtGreaterThan(token,false,System.currentTimeMillis());
        if (optionalToken.isEmpty()) {
            throw new BadCredentialsException("Token not found");
        }
        return optionalToken.get().getUser();
    }
}
