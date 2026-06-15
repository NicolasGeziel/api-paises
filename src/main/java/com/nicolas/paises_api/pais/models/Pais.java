package com.nicolas.paises_api.pais.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "paises")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String nome;

    @NotBlank
    @Size(max = 2)
    @Column(unique = true, nullable = false, length = 2)
    private String sigla;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String gentilico;


    public Pais() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public String getGentilico() { return gentilico; }
    public void setGentilico(String gentilico) { this.gentilico = gentilico; }
}