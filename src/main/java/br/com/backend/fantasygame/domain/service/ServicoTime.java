package br.com.backend.fantasygame.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.request.RequisicaoAlterarTime;
import br.com.backend.fantasygame.domain.request.RequisicaoSalvarJogadores;

public interface ServicoTime {
    Time criarTime(User user, String teamName);
    Optional<Time> obterTimePorId(Long id);
    Time obterTimeDoUsuario();
    List<Time> obterTodosOsTimes();
    Time alterarTime(RequisicaoAlterarTime requisicao);
    Time salvarJogadores(RequisicaoSalvarJogadores requisicao);
}
