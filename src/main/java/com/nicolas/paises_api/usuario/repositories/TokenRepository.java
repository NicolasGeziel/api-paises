package com.nicolas.paises_api.usuario.repositories;

import com.nicolas.paises_api.usuario.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}