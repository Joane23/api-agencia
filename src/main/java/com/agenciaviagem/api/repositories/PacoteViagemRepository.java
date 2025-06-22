package com.agenciaviagem.api.repositories;

import com.agenciaviagem.api.entities.PacoteViagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacoteViagemRepository extends JpaRepository<PacoteViagem, Long> {
}
