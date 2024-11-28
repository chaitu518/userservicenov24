package com.example.userservicenov24.repos;

import com.example.userservicenov24.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {
    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String Value, Boolean deleted, Long expiryAt);
}
