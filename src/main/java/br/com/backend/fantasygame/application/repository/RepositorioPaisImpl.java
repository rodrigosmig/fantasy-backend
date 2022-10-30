package br.com.backend.fantasygame.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.backend.fantasygame.domain.entity.Pais;
import br.com.backend.fantasygame.domain.repository.RepositorioPais;
import br.com.backend.fantasygame.infrastracture.repository.RepositorioPaisJpa;
import br.com.backend.fantasygame.infrastracture.schema.PaisSchema;

@Component
public class RepositorioPaisImpl implements RepositorioPais {

    @Autowired
    private RepositorioPaisJpa paisRepository;

    @Override
    public Optional<Pais> findById(Long id) {
        var paisSchema = paisRepository.findById(id);

        return paisSchema.map(PaisSchema::toPais);
    }

    @Override
    public List<Pais> findAll() {
        return paisRepository.findAll().stream()
            .map(PaisSchema::toPais)
            .toList();
    }
    
}
