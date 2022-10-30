package br.com.backend.fantasygame.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Pais;

public interface ServicoPais {
    Optional<Pais> findById(Long id);
    List<Pais> getAll();
}
