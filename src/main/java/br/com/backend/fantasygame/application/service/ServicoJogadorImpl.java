package br.com.backend.fantasygame.application.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.repository.RepositorioJogador;
import br.com.backend.fantasygame.domain.request.RequisicaoListarJogador;
import br.com.backend.fantasygame.domain.service.ServicoJogador;
import org.springframework.data.domain.Page;

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
    public Page<Jogador> getAll(int page, int size, RequisicaoListarJogador requisicao) {
        return repositorioJogador.findAll(page, size, requisicao);
    }    
}
