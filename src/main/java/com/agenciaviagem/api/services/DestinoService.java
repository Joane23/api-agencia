package com.agenciaviagem.api.services;

import com.agenciaviagem.api.entities.Destino;
import com.agenciaviagem.api.entities.Endereco;
import com.agenciaviagem.api.exceptions.DestinoNaoEncontradoException;
import com.agenciaviagem.api.repositories.DestinoRepository;
import com.agenciaviagem.api.repositories.EnderecoRepository;
import com.agenciaviagem.api.services.dtos.AtualizarDestinoDto;
import com.agenciaviagem.api.services.dtos.AvaliarDestinoDto;
import com.agenciaviagem.api.services.dtos.CriarDestinoDto;
import com.agenciaviagem.api.services.dtos.PesquisarDestinoDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class DestinoService {

    private final DestinoRepository destinoRepository;
    private final EnderecoRepository enderecoRepository;

    public DestinoService(DestinoRepository destinoRepository, EnderecoRepository enderecoRepository) {
        this.destinoRepository = destinoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Destino criar(CriarDestinoDto criarDestinoDto) {
        Endereco novoEndereco = new Endereco(criarDestinoDto.getCidade(), criarDestinoDto.getEstado(), criarDestinoDto.getPais());
        Endereco enderecoSalvo = enderecoRepository.save(novoEndereco);

        Destino novoDestino = new Destino(
                null,
                criarDestinoDto.getNome(),
                enderecoSalvo
        );
        return destinoRepository.save(novoDestino);
    }

    public Destino atualizar(Long id, AtualizarDestinoDto atualizarDestinoDto) throws DestinoNaoEncontradoException {
        Destino destinoExistente = getById(id);
        destinoExistente.setNome(atualizarDestinoDto.getNome());
        destinoExistente.setEndereco(new Endereco(
                atualizarDestinoDto.getCidade(),
                atualizarDestinoDto.getEstado(),
                atualizarDestinoDto.getPais()
        ));
        return destinoRepository.save(destinoExistente);
    }

    public Destino getById(Long id) throws DestinoNaoEncontradoException {
        var destino = destinoRepository.findById(id);
        if (destino.isEmpty()) {
            throw new DestinoNaoEncontradoException("Destino não encontrado com ID: " + id);
        }
        return destino.get();
    }

    public void excluir(Long id) throws DestinoNaoEncontradoException {
        Destino destino = getById(id);
        destinoRepository.delete(destino);
    }

    public List<Destino> pesquisar(PesquisarDestinoDto pesquisarDestinoDto) {
        return destinoRepository.findAll().stream()
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
        return destinoRepository.save(destino);

    }
}