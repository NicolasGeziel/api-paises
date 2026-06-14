package com.nicolas.paises_api.usuario.dtos;

public class UsuarioAutenticadoDTO {
    private String login;
    private String nome;
    private String token;
    private boolean administrador;
    private boolean autenticado;

    public UsuarioAutenticadoDTO(String login, String nome, String token, boolean administrador, boolean autenticado) {
        this.login = login;
        this.nome = nome;
        this.token = token;
        this.administrador = administrador;
        this.autenticado = autenticado;
    }

    public String getLogin() {
        return login;
    }
    public String getNome() {
        return nome;
    }
    public String getToken() {
        return token;
    }
    public boolean isAdministrador() {
        return administrador;
    }
    public boolean isAutenticado() {
        return autenticado;
    }
}