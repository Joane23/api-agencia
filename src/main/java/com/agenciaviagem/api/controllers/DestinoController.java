package com.agenciaviagem.api.controllers;

import com.agenciaviagem.api.entities.Destino;
import com.agenciaviagem.api.exceptions.DestinoNaoEncontradoException;
import com.agenciaviagem.api.services.DestinoService;
import com.agenciaviagem.api.services.dtos.AtualizarDestinoDto;
import com.agenciaviagem.api.services.dtos.AvaliarDestinoDto;
import com.agenciaviagem.api.services.dtos.CriarDestinoDto;
import com.agenciaviagem.api.services.dtos.PesquisarDestinoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destinos")
public class DestinoController {
    
    private final DestinoService destinoService;
    
    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @GetMapping
    public ResponseEntity<List<Destino>> pesquisarTodos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String pais
    ) {
        var pesquisarDestinoDto = new PesquisarDestinoDto(
                Optional.ofNullable(nome),
                Optional.ofNullable(cidade),
                Optional.ofNullable(estado),
                Optional.ofNullable(pais)
        );
        var destinos = destinoService.pesquisar(pesquisarDestinoDto);
        return new ResponseEntity<>(destinos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Destino> criar(@RequestBody CriarDestinoDto criarDestinoDto) {
        var novoDestino = destinoService.criar(criarDestinoDto);
        return new ResponseEntity<>(novoDestino, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizar(@PathVariable Long id, @RequestBody AtualizarDestinoDto atualizarDestinoDto) {
        try {
            var destinoAtualizado = destinoService.atualizar(id, atualizarDestinoDto);
            return new ResponseEntity<>(destinoAtualizado, HttpStatus.OK);
        } catch (DestinoNaoEncontradoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> getById(@PathVariable Long id) {
        try {
            var destino = destinoService.getById(id);
            return new ResponseEntity<>(destino, HttpStatus.OK);
        } catch (DestinoNaoEncontradoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            destinoService.excluir(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DestinoNaoEncontradoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/avaliacoes")
    public ResponseEntity<Destino> avaliar(@PathVariable Long id, @RequestBody AvaliarDestinoDto avaliarDestinoDto) throws DestinoNaoEncontradoException {
        try {
            var destino = destinoService.avaliar(id, avaliarDestinoDto);
            return new ResponseEntity<>(destino, HttpStatus.CREATED);
        } catch (DestinoNaoEncontradoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
