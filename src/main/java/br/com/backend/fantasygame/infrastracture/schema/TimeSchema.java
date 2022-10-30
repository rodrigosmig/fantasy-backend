package br.com.backend.fantasygame.infrastracture.schema;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;

@Entity
@Table(name = "times")
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

    public TimeSchema() {
    }

    public TimeSchema(Time time) {
        this.id = time.getId();
        this.nome = time.getNome();
        this.pontos = time.getPontos();
        this.formacao = new FormacaoSchema(time.getFormacao());
        this.user = new UserSchema(time.getUser());
        this.criadoEm = time.getCriadoEm();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getPontos() {
        return pontos;
    }

    public FormacaoSchema getFormacao() {
        return formacao;
    }

    public UserSchema getUser() {
        return user;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public Time toTime() {
        return new Time(
            id, 
            new Nome(nome), 
            new Pontos(pontos),
            this.getFormacao().toFormacao(),
            user.toUser(), 
            criadoEm
        );
    }
}
