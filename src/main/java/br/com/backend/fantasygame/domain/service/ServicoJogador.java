package br.com.backend.fantasygame.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Jogador;

public interface ServicoJogador {
    Optional<Jogador> findById(Long id);
    List<Jogador> getAll(int page, int size);
}
