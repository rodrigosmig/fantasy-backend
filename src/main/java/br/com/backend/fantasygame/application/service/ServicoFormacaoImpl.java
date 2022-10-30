package br.com.backend.fantasygame.application.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.repository.RepositorioFormacao;
import br.com.backend.fantasygame.domain.service.ServicoFormacao;

public class ServicoFormacaoImpl implements ServicoFormacao {

    private final RepositorioFormacao formationRepository;

    public ServicoFormacaoImpl(RepositorioFormacao formationRepository) {
        this.formationRepository = formationRepository;
    }

    @Override
    public List<Formacao> getAll() {
        return formationRepository.findAll();
    }

    @Override
    public Optional<Formacao> findById(Long id) {
        return formationRepository.findById(id);
    }
    
}
