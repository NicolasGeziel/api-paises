package com.nicolas.paises_api.pais.controllers;

import com.nicolas.paises_api.pais.models.Pais;
import com.nicolas.paises_api.pais.repositories.PaisRepository;
import com.nicolas.paises_api.usuario.models.Token;
import com.nicolas.paises_api.usuario.repositories.TokenRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/pais")
public class PaisController {

    private final PaisRepository paisRepository;
    private final TokenRepository tokenRepository;

    public PaisController(PaisRepository paisRepository, TokenRepository tokenRepository) {
        this.paisRepository = paisRepository;
        this.tokenRepository = tokenRepository;
    }


    private Token validarToken(String tokenString) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(tokenString);
        if (tokenOpt.isPresent() && LocalDateTime.now().isBefore(tokenOpt.get().getExpiracao())) {
            return tokenOpt.get();
        }
        return null;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(@RequestHeader("Authorization") String token) {
        if (validarToken(token) == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(paisRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestHeader("Authorization") String token, @Valid @RequestBody Pais pais) {
        Token tokenValido = validarToken(token);
        if (tokenValido == null || !tokenValido.isAdministrador()) {
            return ResponseEntity.status(401).build();
        }
        pais.setId(null);
        return ResponseEntity.status(201).body(paisRepository.save(pais));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestHeader("Authorization") String token,@Valid @RequestBody Pais pais, @PathVariable Long id) {
        Token tokenValido = validarToken(token);
        if (tokenValido == null || !tokenValido.isAdministrador()) {
            return ResponseEntity.status(401).build();
        }
        if (!paisRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pais.setId(id);
        return ResponseEntity.ok(paisRepository.save(pais));
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar(@RequestHeader("Authorization") String token, @RequestParam String filtro) {
        if (validarToken(token) == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(paisRepository.findByNomeContainingIgnoreCase(filtro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        Token tokenValido = validarToken(token);

        if (tokenValido == null || !tokenValido.isAdministrador()) {
            return ResponseEntity.status(401).build();
        }

        if (paisRepository.existsById(id)) {
            paisRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}