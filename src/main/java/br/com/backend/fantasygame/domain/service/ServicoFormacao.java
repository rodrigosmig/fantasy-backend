package br.com.backend.fantasygame.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Formacao;

public interface ServicoFormacao {
    Optional<Formacao> findById(Long id);
    List<Formacao> getAll();
}
