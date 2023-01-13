package br.com.backend.fantasygame.domain.entity;

import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Jogador {

    private Long id;
    private Nome nome;
    private Pontos pontos;    
    private Posicao posicao;
    private Pais pais;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome.getNome();
    }

    public Integer getPontos() {
        return pontos.getPontos();
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public Pais getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return "Jogador [id=" + id + ", nome=" + nome + ", pais=" + pais + ", pontos=" + pontos + ", posicao=" + posicao
                + "]";
    }

    
}
