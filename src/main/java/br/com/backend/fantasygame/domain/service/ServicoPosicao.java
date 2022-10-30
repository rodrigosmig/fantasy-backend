package br.com.backend.fantasygame.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Posicao;

public interface ServicoPosicao {
    public List<Posicao> getAll();
    public Optional<Posicao> findById(Long id);
}
