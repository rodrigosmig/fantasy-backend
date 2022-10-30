package br.com.backend.fantasygame.application.service;

import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.exception.FormacaoNaoEncontradaException;
import br.com.backend.fantasygame.domain.repository.RepositorioFormacao;
import br.com.backend.fantasygame.domain.repository.RepositorioTime;
import br.com.backend.fantasygame.domain.request.RequisicaoAlterarTime;
import br.com.backend.fantasygame.domain.service.ServicoTime;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ServicoTimeImpl implements ServicoTime {

    private final RepositorioTime repositorioTime;
    private final RepositorioFormacao repositorioFormacao;

    private static final Long DEFAULT_FORMATION_ID = 1L;

    public ServicoTimeImpl(RepositorioTime repositorioTime, RepositorioFormacao repositorioFormacao) {
        this.repositorioTime = repositorioTime;
        this.repositorioFormacao = repositorioFormacao;
    }

    @Transactional
    @Override
    public Time criarTime(User user, String teamName) {
        var formacao = repositorioFormacao.findById(DEFAULT_FORMATION_ID).orElseThrow(FormacaoNaoEncontradaException::new);

        var novoTime = new Time(new Nome(teamName), new Pontos(0), user);
        novoTime.setFormacao(formacao);
        novoTime = repositorioTime.save(novoTime);
        
        user.setTime(novoTime);

        return novoTime;
    }

    @Override
    public Optional<Time> obterTimePorId(Long id) {
        return repositorioTime.findById(id);
    }

    @Override
    public Time obterTimeDoUsuario() {
        var userSchema = (UserSchema) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return repositorioTime.findByUser(userSchema.toUser());
    }

    @Override
    public List<Time> obterTodosOsTimes() {
        return repositorioTime.findAll();
    }

    @Override
    public Time alterarTime(RequisicaoAlterarTime requisicao) {
        Formacao formacao = repositorioFormacao.findById(requisicao.getFormacaoId())
                .orElseThrow(FormacaoNaoEncontradaException::new);

        Time time = obterTimeDoUsuario();
        time.setFormacao(formacao);
        time.setNome(requisicao.getNomeTime());

        return repositorioTime.save(time);
    }
}
