package br.com.backend.fantasygame.application.repository;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.backend.fantasygame.application.specification.JogadorSpecification;
import br.com.backend.fantasygame.domain.request.RequisicaoListarJogador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.repository.RepositorioJogador;
import br.com.backend.fantasygame.infrastracture.repository.RepositorioJogadorJpa;
import br.com.backend.fantasygame.infrastracture.schema.JogadorSchema;

@Component
public class RepositorioJogadorImpl implements RepositorioJogador {

    @Autowired
    private RepositorioJogadorJpa repositorioJogador;

    @Override
    public Optional<Jogador> findById(Long id) {
        var jogadorSchema = repositorioJogador.findById(id);

        return jogadorSchema.map(JogadorSchema::toJogador);
    }

    @Override
    public Page<Jogador> findAll(int page, int size, RequisicaoListarJogador requisicao) {
        var pageable = PageRequest.of(page, size, Sort.by("nome"));

        if (requisicao.isRequestNull()) {
            return repositorioJogador.findAll(pageable).map(JogadorSchema::toJogador);
        }

        Specification<JogadorSchema> specification = null;

        if (!requisicao.isNomeNull()) {
            specification = JogadorSpecification.nome(requisicao.getNome());
        }

        if (!requisicao.isPaisNull()) {
            if (Objects.isNull(specification)) {
                specification = JogadorSpecification.pais(requisicao.getPaisId());
            } else {
                specification = specification.and(
                        JogadorSpecification.pais(requisicao.getPaisId())
                );
            }
        }

        if (!requisicao.isPositionNull()) {
            if (Objects.isNull(specification)) {
                specification = JogadorSpecification.posicao(requisicao.getPosicaoId());
            } else {
                specification = specification.and(
                        JogadorSpecification.posicao(requisicao.getPosicaoId())
                );
            }
        }

        return repositorioJogador.findAll(Specification.where(specification), pageable)
                .map(JogadorSchema::toJogador);
    }

    @Override
    public Boolean existsPlayers(Set<Long> idsJogadores) {
        return repositorioJogador.countAllByIdIn(idsJogadores).equals(idsJogadores.size());
    }

    @Override
    public Set<Jogador> buscarJogadores(Set<Long> ids) {
        return repositorioJogador.findByIdIn(ids)
                .stream()
                .map(JogadorSchema::toJogador)
                .collect(Collectors.toSet());
    }

}
