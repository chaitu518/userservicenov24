package com.example.userservicenov24.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDto {
    private String token;
    private long expires_in;
}
