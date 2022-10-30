package br.com.backend.fantasygame.domain.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Sigla;

public class Pais {
    
    private Long id;
    private Nome nome;
    private Sigla sigla;        
    private List<Jogador> jogadores;

    public Pais(Long id, String nome, String sigla) {
        this.id = id;
        this.nome = new Nome(nome);
        this.sigla = new Sigla(sigla);
        this.jogadores = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome.getNome();
    }

    public String getSigla() {
        return sigla.getSigla();
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }
}
