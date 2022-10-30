package br.com.backend.fantasygame.infrastracture.schema;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.vo.Nome;

@Entity
@Table(name = "formacoes")
public class FormacaoSchema {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;   

    @OneToMany(mappedBy = "formacao")
    private List<TimeSchema> times;

    public FormacaoSchema() {
    }

    public FormacaoSchema(Formacao formacao) {
        this.id = formacao.getId();
        this.nome = formacao.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<TimeSchema> getTimes() {
        return times;
    }

    public Formacao toFormacao() {
        return new Formacao(id, new Nome(nome));
    }
}
