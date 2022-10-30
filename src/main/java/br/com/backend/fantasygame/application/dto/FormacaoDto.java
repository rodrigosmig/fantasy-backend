package br.com.backend.fantasygame.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.backend.fantasygame.domain.entity.Formacao;

public class FormacaoDto {
    private Long id;
    private String nome;

    public FormacaoDto(Formacao formacao) {
        this.id = formacao.getId();
        this.nome = formacao.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static List<FormacaoDto> ToList(List<Formacao> formacoes) {
        return formacoes.stream()
            .map(FormacaoDto::new)
            .collect(Collectors.toList());
    }
}
