package com.agenciaviagem.api.services.dtos;

public class CriarDestinoDto {
    private String nome;
    private String cidade;
    private String estado;
    private String pais;

    public CriarDestinoDto() {
    }

    public CriarDestinoDto(String nome, String cidade, String estado, String pais) {
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
