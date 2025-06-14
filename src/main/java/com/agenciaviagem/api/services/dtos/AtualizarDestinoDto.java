package com.agenciaviagem.api.services.dtos;

public class AtualizarDestinoDto {
    private String nome;
    private String cidade;
    private String estado;
    private String pais;

    public AtualizarDestinoDto() {
    }

    public AtualizarDestinoDto(String nome, String cidade, String estado, String pais) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }
}
