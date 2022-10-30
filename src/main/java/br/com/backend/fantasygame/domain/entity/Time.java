package br.com.backend.fantasygame.domain.entity;

import java.time.LocalDateTime;

import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@AllArgsConstructor
public class Time {
    @Getter
    private Long id;
    private Nome nome;
    private Pontos pontos;
    @Getter @Setter
    private Formacao formacao;
    @Getter
    private User user;
    @Getter
    private LocalDateTime criadoEm;

    public Time(Nome nome, Pontos pontos, User user) {
        this.nome = nome;
        this.pontos = pontos;
        this.user = user;
        this.criadoEm = LocalDateTime.now();
    }

    public Time(Long id, Nome nome, Pontos pontos, Formacao formacao, User user) {
        this.id = id;
        this.nome = nome;
        this.pontos = pontos;
        this.formacao = formacao;
        this.user = user;
        this.criadoEm = LocalDateTime.now();
    }

    public String getNome() {
        return nome.getNome();
    }

    public void setNome(String nome) {
        this.nome = new Nome(nome);
    }

    public Integer getPontos() {
        return pontos.getPontos();
    }
}
