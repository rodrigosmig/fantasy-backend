package br.com.backend.fantasygame.application.dto;

import br.com.backend.fantasygame.domain.entity.Formacao;

import java.util.List;

public class FormacaoDTO {
    private Long id;
    private String nome;

    public FormacaoDTO(Formacao formacao) {
        this.id = formacao.getId();
        this.nome = formacao.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static List<FormacaoDTO> convertToList(List<Formacao> formacoes) {
        return formacoes.stream()
            .map(FormacaoDTO::new)
                .toList();
    }
}
