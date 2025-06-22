package com.agenciaviagem.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "destinos")
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length=250, nullable=false, unique=false)
    private String nome;

    @Column(name="avaliacao", nullable=false, unique=false)
    private Double avaliacao;

    @Column(name="numeroAvaliacoes", nullable=false, unique=false)
    private Long numeroAvaliacoes;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Destino() {
    }

    public Destino(Long id, String nome, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.avaliacao = 0.0;
        this.numeroAvaliacoes = 0L;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Long getNumeroAvaliacoes() {
        return numeroAvaliacoes;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void avaliar(Long nota) {
        var media = this.avaliacao + (nota - avaliacao) / (this.numeroAvaliacoes + 1);
        this.avaliacao = media;
        this.numeroAvaliacoes += 1;
    }
}