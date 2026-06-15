package com.nicolas.paises_api.usuario.services;

import com.nicolas.paises_api.usuario.dtos.LoginRequestDTO;
import com.nicolas.paises_api.usuario.dtos.UsuarioAutenticadoDTO;
import com.nicolas.paises_api.usuario.models.Token;
import com.nicolas.paises_api.usuario.models.Usuario;
import com.nicolas.paises_api.usuario.repositories.TokenRepository;
import com.nicolas.paises_api.usuario.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioAutenticadoDTO autenticar(LoginRequestDTO loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(loginRequest.getLogin());

        if (usuarioOpt.isPresent() && passwordEncoder.matches(loginRequest.getSenha(), usuarioOpt.get().getSenha())) {
            Usuario usuario = usuarioOpt.get();

            String tokenString = UUID.randomUUID().toString();
            LocalDateTime expiracao = LocalDateTime.now().plusMinutes(5);

            Token novoToken = new Token(tokenString, usuario.getLogin(), expiracao, usuario.getAdministrador());
            tokenRepository.save(novoToken);

            return new UsuarioAutenticadoDTO(
                    usuario.getLogin(),
                    usuario.getNome(),
                    tokenString,
                    usuario.getAdministrador(),
                    true
            );
        }
        return new UsuarioAutenticadoDTO(loginRequest.getLogin(), null, null, false, false);
    }

    public boolean renovarTicket(String tokenString) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(tokenString);

        if (tokenOpt.isPresent()) {
            Token token = tokenOpt.get();
            token.setExpiracao(LocalDateTime.now().plusMinutes(5));
            tokenRepository.save(token);
            return true;
        }
        return false;
    }
}