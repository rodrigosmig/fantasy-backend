package br.com.backend.fantasygame.application.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Posicao;
import br.com.backend.fantasygame.domain.repository.RepositorioPosicao;
import br.com.backend.fantasygame.domain.service.ServicoPosicao;

public class ServicoPosicaoImpl implements ServicoPosicao {

    private final RepositorioPosicao positionRepository;

    public ServicoPosicaoImpl(RepositorioPosicao positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public List<Posicao> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Posicao> findById(Long id) {
        return positionRepository.findById(id);
    }
    
}
