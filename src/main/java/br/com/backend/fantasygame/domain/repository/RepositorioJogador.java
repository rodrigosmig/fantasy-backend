package br.com.backend.fantasygame.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Jogador;

public interface RepositorioJogador {
    Optional<Jogador> findById(Long id);
    List<Jogador> findAll(int page, int size);
}
