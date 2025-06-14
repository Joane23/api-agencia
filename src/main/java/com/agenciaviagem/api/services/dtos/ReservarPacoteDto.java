package com.agenciaviagem.api.services.dtos;

import java.math.BigDecimal;

public class ReservarPacoteDto {
    private Long destinoId;
    private BigDecimal valor;
    private String nomeCliente;

    public ReservarPacoteDto() {
    }

    public ReservarPacoteDto(Long destinoId, BigDecimal valor, String nomeCliente) {
        this.destinoId = destinoId;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
}
