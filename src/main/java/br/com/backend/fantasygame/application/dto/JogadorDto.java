package br.com.backend.fantasygame.application.dto;

import java.util.List;

import br.com.backend.fantasygame.domain.entity.Jogador;

public class JogadorDto {

    private Long id;
    private String nome;
    private Integer pontos;    
    private PosicaoDto posicao;
    private PaisDto pais;

    public JogadorDto(Jogador jogador) {
        this.id = jogador.getId();
        this.nome = jogador.getNome();
        this.pontos = jogador.getPontos();
        this.posicao = new PosicaoDto(jogador.getPosicao());
        this.pais = new PaisDto(jogador.getPais());
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

    public PosicaoDto getPosicao() {
        return posicao;
    }

    public PaisDto getPais() {
        return pais;
    }

    public static List<JogadorDto> toList(List<Jogador> jogadores) {
        return jogadores.stream()
        .map(JogadorDto::new)
        .toList();
    }
}
