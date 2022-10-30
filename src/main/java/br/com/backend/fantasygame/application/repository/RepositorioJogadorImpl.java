package br.com.backend.fantasygame.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.repository.RepositorioJogador;
import br.com.backend.fantasygame.infrastracture.repository.RepositorioJogadorJpa;
import br.com.backend.fantasygame.infrastracture.schema.JogadorSchema;

@Component
public class RepositorioJogadorImpl implements RepositorioJogador {

    @Autowired
    private RepositorioJogadorJpa jogadorRepository;

    @Override
    public Optional<Jogador> findById(Long id) {
        var jogadorSchema = jogadorRepository.findById(id);

        return jogadorSchema.map(JogadorSchema::toJogador);
    }

    @Override
    public List<Jogador> findAll(int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("nome"));
        
        return jogadorRepository.findAll(pageable).stream()
            .map(JogadorSchema::toJogador)
            .toList();
    }
    
}
