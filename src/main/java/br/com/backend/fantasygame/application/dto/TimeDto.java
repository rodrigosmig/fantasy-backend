package br.com.backend.fantasygame.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.backend.fantasygame.domain.entity.Time;

public class TimeDto {
    private Long id;
    private String nome;
    private Integer pontos;
    private FormacaoDto formacao;
    private LocalDateTime criadoEm;
    
    public TimeDto(Time time) {
        this.id = time.getId();
        this.nome = time.getNome();
        this.pontos = time.getPontos();
        this.formacao = new FormacaoDto(time.getFormacao());
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

    public FormacaoDto getFormacao() {
        return formacao;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public static List<TimeDto> toList(List<Time> players) {
        return players.stream()
            .map(TimeDto::new)
            .toList();
    }
}
