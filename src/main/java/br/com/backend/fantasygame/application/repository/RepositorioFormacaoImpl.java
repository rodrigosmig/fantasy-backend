package br.com.backend.fantasygame.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.repository.RepositorioFormacao;
import br.com.backend.fantasygame.infrastracture.repository.RepositorioFormacaoJpa;
import br.com.backend.fantasygame.infrastracture.schema.FormacaoSchema;

@Component
public class RepositorioFormacaoImpl implements RepositorioFormacao {

    @Autowired
    private RepositorioFormacaoJpa formationRepository;

    @Override
    public Optional<Formacao> findById(Long id) {
        var formationSchema = formationRepository.findById(id);

        return formationSchema.map(FormacaoSchema::toFormacao);
    }

    @Override
    public List<Formacao> findAll() {
        return formationRepository.findAll().stream()
            .map(FormacaoSchema::toFormacao)
            .toList();
    }    
}
