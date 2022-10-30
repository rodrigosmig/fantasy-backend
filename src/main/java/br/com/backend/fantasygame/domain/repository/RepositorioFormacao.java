package br.com.backend.fantasygame.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Formacao;

public interface RepositorioFormacao {
    Optional<Formacao> findById(Long id);
    List<Formacao> findAll();
}
