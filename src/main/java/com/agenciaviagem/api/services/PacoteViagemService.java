package com.agenciaviagem.api.services;

import com.agenciaviagem.api.entities.Cliente;
import com.agenciaviagem.api.entities.PacoteViagem;
import com.agenciaviagem.api.exceptions.DestinoNaoEncontradoException;
import com.agenciaviagem.api.services.dtos.ReservarPacoteDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacoteViagemService {
    private static final List<PacoteViagem> reservas = new ArrayList<PacoteViagem>();
    private final DestinoService destinoService;

    public PacoteViagemService(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    public PacoteViagem reservar(ReservarPacoteDto reservarPacoteDto) throws DestinoNaoEncontradoException {
        var destino = this.destinoService.getById(reservarPacoteDto.getDestinoId());
        var cliente = new Cliente(reservarPacoteDto.getNomeCliente());
        var valor = reservarPacoteDto.getValor();
        var pacoteViagem = new PacoteViagem(cliente, destino, valor);
        reservas.add(pacoteViagem);
        return pacoteViagem;
    }

    public List<PacoteViagem> listarReservas() {
        return new ArrayList<>(reservas);
    }
}