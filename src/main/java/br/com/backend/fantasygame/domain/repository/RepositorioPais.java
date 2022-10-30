package br.com.backend.fantasygame.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Pais;

public interface RepositorioPais {
    Optional<Pais> findById(Long id);
    List<Pais> findAll();
}
