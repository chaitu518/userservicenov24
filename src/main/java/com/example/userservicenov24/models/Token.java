package com.example.userservicenov24.models;

import com.example.userservicenov24.dtos.TokenResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    @ManyToOne
    private User user;
    private String value;
    private long expiryAt;
    public TokenResponseDto from(){
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken(value);
        tokenResponseDto.setExpires_in(expiryAt);
        return tokenResponseDto;
    }
}
