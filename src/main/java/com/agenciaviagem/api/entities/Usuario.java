package com.agenciaviagem.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    public Usuario(String email, String senha) {
        this.email = email;
    }

    public Usuario() {
    }

    public String getNome() {
        return email;
    }

    public Long getId() {
        return id;
    }
}
