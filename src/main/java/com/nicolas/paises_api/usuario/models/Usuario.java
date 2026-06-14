package com.nicolas.paises_api.usuario.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nome;

    private boolean administrador;

    public Usuario() {}

    public Long getId() {

        return id;
    }
    public String getLogin() {

        return login;
    }
    public String getSenha() {

        return senha;
    }
    public String getNome() {

        return nome;
    }
    public boolean getAdministrador() {

        return administrador;
    }
    public void setId(Long id) {

        this.id = id;
    }
    public void setLogin(String login) {

        this.login = login;
    }
    public void setSenha(String senha) {

        this.senha = senha;
    }
    public void setNome(String nome) {

        this.nome = nome;
    }
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
}
