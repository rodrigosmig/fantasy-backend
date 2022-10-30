package br.com.backend.fantasygame.infrastracture.schema;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.backend.fantasygame.domain.entity.Pais;

@Entity
@Table(name = "paises")
public class PaisSchema {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sigla;        

    @OneToMany(mappedBy = "pais")
    private List<JogadorSchema> jogadores;

    public PaisSchema() {
    }

    public PaisSchema(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
        this.sigla = pais.getSigla();
        this.jogadores = pais.getJogadores().stream()
            .map(JogadorSchema::new)
            .toList();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public List<JogadorSchema> getPlayers() {
        return jogadores;
    }

    public Pais toPais() {
        return new Pais(id, nome, sigla);
    }
}
