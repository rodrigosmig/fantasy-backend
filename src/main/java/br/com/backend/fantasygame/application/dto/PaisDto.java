package br.com.backend.fantasygame.application.dto;

import java.util.List;

import br.com.backend.fantasygame.domain.entity.Pais;

public class PaisDto {
    private Long id;
    private String nome;
    private String sigla;

    public PaisDto(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
        this.sigla = pais.getSigla();
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
    
    public static List<PaisDto> convertToList(List<Pais> countries) {
        return countries.stream()
            .map(PaisDto::new)
            .toList();
    }
}
