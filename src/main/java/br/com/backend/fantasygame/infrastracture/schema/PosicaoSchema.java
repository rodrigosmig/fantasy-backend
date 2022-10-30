package br.com.backend.fantasygame.infrastracture.schema;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.backend.fantasygame.domain.entity.Posicao;
import br.com.backend.fantasygame.domain.vo.Nome;

@Entity
@Table(name = "posicoes")
public class PosicaoSchema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public PosicaoSchema() {
    }

    public PosicaoSchema(Posicao posicao) {
        this.id = posicao.getId();
        this.nome = posicao.getNome();
    }

    @OneToMany(mappedBy = "posicao")
    private List<JogadorSchema> players;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<JogadorSchema> getPlayers() {
        return players;
    }
    
    public Posicao toPosicao() {
        return new Posicao(id, new Nome(nome));
    }
}
