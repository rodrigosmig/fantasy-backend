package br.com.backend.fantasygame.application.dto;

import java.util.List;

import br.com.backend.fantasygame.domain.entity.Posicao;

public class PosicaoDto {
    private Long id;
    private String nome;

    public PosicaoDto(Posicao posicao) {
        this.id = posicao.getId();
        this.nome = posicao.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static List<PosicaoDto> convertToList(List<Posicao> posicoes) {
        return posicoes.stream()
            .map(PosicaoDto::new)
            .toList();
    }
}
