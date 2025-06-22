package com.agenciaviagem.api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pacotes_viagem")
public class PacoteViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "destino_id")
    private Destino destino;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    public PacoteViagem() {
    }

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

    public Long getId() {
        return id;
    }
}
