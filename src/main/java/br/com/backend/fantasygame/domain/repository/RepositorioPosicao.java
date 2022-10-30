package br.com.backend.fantasygame.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Posicao;

public interface RepositorioPosicao {
    Optional<Posicao> findById(Long id);
    List<Posicao> findAll();
}
