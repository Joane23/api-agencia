package com.agenciaviagem.api.services.dtos;

public class AvaliarDestinoDto {
    private Long nota;

    public AvaliarDestinoDto() {
    }

    public AvaliarDestinoDto(Long nota) {
        this.nota = nota;
    }

    public Long getNota() {
        return nota;
    }
}
