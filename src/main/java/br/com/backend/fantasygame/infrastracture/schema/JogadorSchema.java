package br.com.backend.fantasygame.infrastracture.schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;

@Entity
@Table(name = "jogadores")
public class JogadorSchema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer pontos;

    @ManyToOne
    @JoinColumn(name = "posicao_id", nullable = false)
    private PosicaoSchema posicao;

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = false)
    private PaisSchema pais;

    public JogadorSchema() {
    }

    public JogadorSchema(Jogador jogador) {
        this.id = jogador.getId();
        this.nome = jogador.getNome();
        this.pontos = jogador.getPontos();
        this.posicao = new PosicaoSchema(jogador.getPosicao());
        this.pais = new PaisSchema(jogador.getPais());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getPontos() {
        return pontos;
    }

    public PosicaoSchema getPosicao() {
        return posicao;
    }

    public PaisSchema getPais() {
        return pais;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JogadorSchema other = (JogadorSchema) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Jogador toJogador() {
        return new Jogador(
            this.id, 
            new Nome(nome), 
            new Pontos(pontos), 
            this.getPosicao().toPosicao(), 
            this.pais.toPais()
        );
    }
}
