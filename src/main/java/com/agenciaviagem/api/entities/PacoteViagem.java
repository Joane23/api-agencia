package com.agenciaviagem.api.entities;

import java.math.BigDecimal;

public class PacoteViagem {
    private Cliente cliente;
    private Destino destino;
    private BigDecimal valor;

    public PacoteViagem(Cliente cliente, Destino destino, BigDecimal valor) {
        this.cliente = cliente;
        this.destino = destino;
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Destino getDestino() {
        return destino;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
