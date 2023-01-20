package br.com.backend.fantasygame.application.dto;

import br.com.backend.fantasygame.domain.entity.Time;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class TimeDTO {
    private Long id;
    private String nome;
    private Integer pontos;
    private FormacaoDTO formacao;
    private LocalDateTime criadoEm;
    private Set<JogadorDTO> jogadores;
    
    public TimeDTO(Time time) {
        this.id = time.getId();
        this.nome = time.getNome();
        this.pontos = time.getPontos();
        this.formacao = new FormacaoDTO(time.getFormacao());
        this.criadoEm = time.getCriadoEm();
        this.jogadores = time.getJogadores()
                .stream()
                .map(JogadorDTO::new)
                .collect(Collectors.toSet());
    }

    public static List<TimeDTO> toList(List<Time> players) {
        return players.stream()
            .map(TimeDTO::new)
            .toList();
    }
}
