package com.agenciaviagem.api.entities;

public class Destino {
    private Long id;
    private String nome;
    private Double avaliacao;
    private Long numeroAvaliacoes;
    private Endereco endereco;

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
