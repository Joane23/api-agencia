package com.agenciaviagem.api.controllers;

import com.agenciaviagem.api.entities.PacoteViagem;
import com.agenciaviagem.api.exceptions.DestinoNaoEncontradoException;
import com.agenciaviagem.api.services.PacoteViagemService;
import com.agenciaviagem.api.services.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservasController {

    private final PacoteViagemService pacoteViagemService;

    public ReservasController(PacoteViagemService pacoteViagemService) {
        this.pacoteViagemService = pacoteViagemService;
    }

    @GetMapping
    public ResponseEntity<List<PacoteViagem>> listarTodos() {
        var reservas = pacoteViagemService.listarReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacoteViagem> criar(@RequestBody ReservarPacoteDto reservarPacoteDto) throws DestinoNaoEncontradoException {
        try {
            var pacote = pacoteViagemService.reservar(reservarPacoteDto);
            return new ResponseEntity<>(pacote, HttpStatus.CREATED);
        } catch (DestinoNaoEncontradoException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
