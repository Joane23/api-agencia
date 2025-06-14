package com.agenciaviagem.api.services.dtos;

import java.util.Optional;

public class PesquisarDestinoDto {
    private Optional<String> nome;
    private Optional<String> cidade;
    private Optional<String> estado;
    private Optional<String> pais;

    public PesquisarDestinoDto() {
    }

    public PesquisarDestinoDto(Optional<String> nome, Optional<String> cidade, Optional<String> estado, Optional<String> pais) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public Optional<String> getNome() {
        return nome;
    }

    public Optional<String> getCidade() {
        return cidade;
    }

    public Optional<String> getEstado() {
        return estado;
    }

    public Optional<String> getPais() {
        return pais;
    }
}
