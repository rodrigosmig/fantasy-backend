package br.com.backend.fantasygame.infrastracture.schema;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "times")
@NoArgsConstructor
@Getter
public class TimeSchema {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String nome;

    private Integer pontos;

    @ManyToOne 
    @JoinColumn(name = "formacao_id")
    private FormacaoSchema formacao;

    @OneToOne @JoinColumn(name = "user_id", nullable = false)
    private UserSchema user;

    private LocalDateTime criadoEm = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
        name = "time_jogador",
        joinColumns = @JoinColumn(name = "time_id"),
        inverseJoinColumns = @JoinColumn(name = "jogador_id")
    )
    private Set<JogadorSchema> jogadores;

    public TimeSchema(Time time) {
        this.id = time.getId();
        this.nome = time.getNome();
        this.pontos = time.getPontos();
        this.formacao = new FormacaoSchema(time.getFormacao());
        this.user = new UserSchema(time.getUser());
        this.criadoEm = time.getCriadoEm();
        this.jogadores = time.getJogadores()
                .stream()
                .map(JogadorSchema::new)
                .collect(Collectors.toSet());
    }

    public Time toTime() {
        var novosJogadores = jogadores.stream()
                .map(JogadorSchema::toJogador)
                .collect(Collectors.toSet());

        return new Time(
            id, 
            new Nome(nome), 
            new Pontos(pontos),
            this.getFormacao().toFormacao(),
            user.toUser(), 
            criadoEm,
            novosJogadores
        );
    }
}
