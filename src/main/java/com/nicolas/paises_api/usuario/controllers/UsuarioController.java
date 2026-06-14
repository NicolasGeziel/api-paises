package com.nicolas.paises_api.usuario.controllers;

import com.nicolas.paises_api.usuario.dtos.LoginRequestDTO;
import com.nicolas.paises_api.usuario.dtos.UsuarioAutenticadoDTO;
import com.nicolas.paises_api.usuario.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/autenticar")
    public ResponseEntity<UsuarioAutenticadoDTO> autenticar(@RequestBody LoginRequestDTO loginRequest) {
        UsuarioAutenticadoDTO resposta = usuarioService.autenticar(loginRequest);

        if (resposta.isAutenticado()) {
            return ResponseEntity.ok(resposta);
        } else {
            return ResponseEntity.status(401).body(resposta);
        }
    }

    @GetMapping("/renovar-ticket")
    public ResponseEntity<Boolean> renovarTicket(@RequestParam String token) {
        boolean renovado = usuarioService.renovarTicket(token);
        return ResponseEntity.ok(renovado);
    }
}