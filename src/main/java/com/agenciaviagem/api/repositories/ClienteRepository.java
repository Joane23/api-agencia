package com.agenciaviagem.api.repositories;

import com.agenciaviagem.api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
