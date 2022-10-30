package br.com.backend.fantasygame.application.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.repository.RepositorioJogador;
import br.com.backend.fantasygame.domain.service.ServicoJogador;

public class ServicoJogadorImpl implements ServicoJogador {

    private final RepositorioJogador repositorioJogador;

    public ServicoJogadorImpl(RepositorioJogador repositorioJogador) {
        this.repositorioJogador = repositorioJogador;
    }

    @Override
    public Optional<Jogador> findById(Long id) {
        return repositorioJogador.findById(id);
    }

    @Override
    public List<Jogador> getAll(int page, int size) {
        return repositorioJogador.findAll(page, size);
    }    
}
