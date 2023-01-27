package br.com.backend.fantasygame.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.request.RequisicaoListarJogador;
import org.springframework.data.domain.Page;

public interface RepositorioJogador {
    Optional<Jogador> findById(Long id);
    Page<Jogador> findAll(int page, int size, RequisicaoListarJogador requisicao);
    Boolean existsPlayers(Set<Long> idsJogadores);
    Set<Jogador> buscarJogadores(Set<Long> ids);
}
