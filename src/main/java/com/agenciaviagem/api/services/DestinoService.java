package com.agenciaviagem.api.services;

import com.agenciaviagem.api.entities.Destino;
import com.agenciaviagem.api.entities.Endereco;
import com.agenciaviagem.api.exceptions.DestinoNaoEncontradoException;
import com.agenciaviagem.api.services.dtos.AtualizarDestinoDto;
import com.agenciaviagem.api.services.dtos.AvaliarDestinoDto;
import com.agenciaviagem.api.services.dtos.CriarDestinoDto;
import com.agenciaviagem.api.services.dtos.PesquisarDestinoDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

@Service
public class DestinoService {
    
    private final List<Destino> destinos = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1L);
    
    public Destino criar(CriarDestinoDto criarDestinoDto) {
        Destino novoDestino = new Destino(
                idCounter.getAndIncrement(),
                criarDestinoDto.getNome(),
                new Endereco(criarDestinoDto.getCidade(), criarDestinoDto.getEstado(), criarDestinoDto.getPais())
        );
        destinos.add(novoDestino);
        return novoDestino;
    }
    
    public Destino atualizar(Long id, AtualizarDestinoDto atualizarDestinoDto) throws DestinoNaoEncontradoException {
        Destino destinoExistente = getById(id);
        destinoExistente.setNome(atualizarDestinoDto.getNome());
        destinoExistente.setEndereco(new Endereco(
                atualizarDestinoDto.getCidade(),
                atualizarDestinoDto.getEstado(),
                atualizarDestinoDto.getPais()
        ));
        return destinoExistente;
    }
    
    public Destino getById(Long id) throws DestinoNaoEncontradoException {
        var destino = destinos.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
        if (destino.isEmpty()) {
            throw new DestinoNaoEncontradoException("Destino não encontrado com ID: " + id);
        }
        return destino.get();
    }
    
    public void excluir(Long id) throws DestinoNaoEncontradoException {
        Destino destino = getById(id);
        destinos.remove(destino);
    }
    
    public List<Destino> pesquisar(PesquisarDestinoDto pesquisarDestinoDto) {
        return destinos.stream()
                .filter(getFiltroPesquisa(pesquisarDestinoDto))
                .toList();
    }

    private static Predicate<Destino> getFiltroPesquisa(PesquisarDestinoDto pesquisarDestinoDto) {
        return destino -> {
            boolean matchesNome = pesquisarDestinoDto.getNome()
                    .map(nome -> destino.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .orElse(true);
            boolean matchesCidade = pesquisarDestinoDto.getCidade()
                    .map(cidade -> destino.getEndereco().getCidade().equalsIgnoreCase(cidade))
                    .orElse(true);
            boolean matchesEstado = pesquisarDestinoDto.getEstado()
                    .map(estado -> destino.getEndereco().getEstado().equalsIgnoreCase(estado))
                    .orElse(true);
            boolean matchesPais = pesquisarDestinoDto.getPais()
                    .map(pais -> destino.getEndereco().getPais().equalsIgnoreCase(pais))
                    .orElse(true);
            return matchesNome && matchesCidade && matchesEstado && matchesPais;
        };
    }

    public Destino avaliar(Long destinoId, AvaliarDestinoDto avaliarDestinoDto) throws DestinoNaoEncontradoException {
        var destino = this.getById(destinoId);
        destino.avaliar(avaliarDestinoDto.getNota());
        return destino;

    }
}