package br.com.backend.fantasygame.domain.entity;

import br.com.backend.fantasygame.domain.vo.Nome;

public class Posicao {

    private Long id;
    private Nome nome;

    public Posicao(Long id, Nome nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome.getNome();
    }
}
