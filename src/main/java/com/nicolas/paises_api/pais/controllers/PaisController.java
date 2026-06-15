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
    public ResponseEntity<?> listar(@RequestParam String token) {
        if (validarToken(token) == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(paisRepository.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestParam String token, @Valid @RequestBody Pais pais) {
        Token tokenValido = validarToken(token);
        if (tokenValido == null || !tokenValido.isAdministrador()) {
            return ResponseEntity.status(401).build(); // HTTP_ERROR 401
        }

        if (pais.getId() != null && pais.getId() == 0) {
            pais.setId(null);
        }
        Pais paisSalvo = paisRepository.save(pais);
        return ResponseEntity.ok(paisSalvo);
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar(@RequestParam String token, @RequestParam String filtro) {
        if (validarToken(token) == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(paisRepository.findByNomeContainingIgnoreCase(filtro));
    }

    @GetMapping("/excluir")
    public ResponseEntity<Boolean> excluir(@RequestParam String token, @RequestParam Long id) {
        Token tokenValido = validarToken(token);

        if (tokenValido == null || !tokenValido.isAdministrador()) {
            return ResponseEntity.status(401).build();
        }

        if (paisRepository.existsById(id)) {
            paisRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}