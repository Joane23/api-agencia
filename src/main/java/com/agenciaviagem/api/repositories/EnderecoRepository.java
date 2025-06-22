package com.agenciaviagem.api.repositories;

import com.agenciaviagem.api.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
