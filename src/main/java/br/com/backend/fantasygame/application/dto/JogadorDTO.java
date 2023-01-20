package br.com.backend.fantasygame.application.dto;

import java.util.List;

import br.com.backend.fantasygame.domain.entity.Jogador;

public class JogadorDTO {

    private Long id;
    private String nome;
    private Integer pontos;    
    private PosicaoDTO posicao;
    private PaisDTO pais;

    public JogadorDTO(Jogador jogador) {
        this.id = jogador.getId();
        this.nome = jogador.getNome();
        this.pontos = jogador.getPontos();
        this.posicao = new PosicaoDTO(jogador.getPosicao());
        this.pais = new PaisDTO(jogador.getPais());
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

    public PosicaoDTO getPosicao() {
        return posicao;
    }

    public PaisDTO getPais() {
        return pais;
    }

    public static List<JogadorDTO> toList(List<Jogador> jogadores) {
        return jogadores.stream()
        .map(JogadorDTO::new)
        .toList();
    }
}
