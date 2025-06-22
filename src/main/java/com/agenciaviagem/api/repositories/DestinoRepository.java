package com.agenciaviagem.api.repositories;

import com.agenciaviagem.api.entities.Destino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinoRepository extends JpaRepository<Destino, Long> {
}
