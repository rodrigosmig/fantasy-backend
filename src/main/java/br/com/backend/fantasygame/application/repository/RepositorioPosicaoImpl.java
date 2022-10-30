package br.com.backend.fantasygame.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.backend.fantasygame.domain.entity.Posicao;
import br.com.backend.fantasygame.domain.repository.RepositorioPosicao;
import br.com.backend.fantasygame.infrastracture.repository.RepositorioPosicaoJpa;
import br.com.backend.fantasygame.infrastracture.schema.PosicaoSchema;

@Component
public class RepositorioPosicaoImpl implements RepositorioPosicao {

    @Autowired
    private RepositorioPosicaoJpa positionRepository;

    @Override
    public Optional<Posicao> findById(Long id) {
        var positionSchema = positionRepository.findById(id);

        return positionSchema.map(PosicaoSchema::toPosicao);
    }

    @Override
    public List<Posicao> findAll() {
        var positionsSchema = positionRepository.findAll();
        
        return positionsSchema.stream()
            .map(PosicaoSchema::toPosicao)
            .toList();
    }
    
}
