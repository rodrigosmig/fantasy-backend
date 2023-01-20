package br.com.backend.fantasygame.application.dto;

import java.util.List;

import br.com.backend.fantasygame.domain.entity.Posicao;

public class PosicaoDTO {
    private Long id;
    private String nome;

    public PosicaoDTO(Posicao posicao) {
        this.id = posicao.getId();
        this.nome = posicao.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static List<PosicaoDTO> convertToList(List<Posicao> posicoes) {
        return posicoes.stream()
            .map(PosicaoDTO::new)
            .toList();
    }
}
