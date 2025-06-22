package com.agenciaviagem.api.services;

import com.agenciaviagem.api.entities.Cliente;
import com.agenciaviagem.api.entities.PacoteViagem;
import com.agenciaviagem.api.exceptions.DestinoNaoEncontradoException;
import com.agenciaviagem.api.repositories.ClienteRepository;
import com.agenciaviagem.api.repositories.PacoteViagemRepository;
import com.agenciaviagem.api.services.dtos.ReservarPacoteDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacoteViagemService {
    private final PacoteViagemRepository pacoteViagemRepository;
    private final DestinoService destinoService;
    private final ClienteRepository clienteRepository;

    public PacoteViagemService(DestinoService destinoService, PacoteViagemRepository pacoteViagemRepository, ClienteRepository clienteRepository) {
        this.destinoService = destinoService;
        this.pacoteViagemRepository = pacoteViagemRepository;
        this.clienteRepository = clienteRepository;
    }

    public PacoteViagem reservar(ReservarPacoteDto reservarPacoteDto) throws DestinoNaoEncontradoException {
        var destino = this.destinoService.getById(reservarPacoteDto.getDestinoId());
        var cliente = new Cliente(reservarPacoteDto.getNomeCliente());
        var clienteSalvo = clienteRepository.save(cliente);
        var valor = reservarPacoteDto.getValor();
        var pacoteViagem = new PacoteViagem(clienteSalvo, destino, valor);
        return pacoteViagemRepository.save(pacoteViagem);
    }

    public List<PacoteViagem> listarReservas() {
        return pacoteViagemRepository.findAll();
    }
}
