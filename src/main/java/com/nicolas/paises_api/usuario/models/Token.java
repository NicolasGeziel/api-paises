package com.nicolas.paises_api.usuario.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private LocalDateTime expiracao;

    private boolean administrador;

    public Token() {}

    public Token(String token, String login, LocalDateTime expiracao, boolean administrador) {
        this.token = token;
        this.login = login;
        this.expiracao = expiracao;
        this.administrador = administrador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getExpiracao() {

        return expiracao;
    }

    public void setExpiracao(LocalDateTime expiracao) {

        this.expiracao = expiracao;
    }

    public boolean isAdministrador() {

        return administrador;
    }
}