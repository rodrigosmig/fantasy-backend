package br.com.backend.fantasygame.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.request.RequisicaoListarJogador;
import org.springframework.data.domain.Page;

public interface ServicoJogador {
    Optional<Jogador> findById(Long id);
    Page<Jogador> getAll(int page, int size, RequisicaoListarJogador requisicao);
}
