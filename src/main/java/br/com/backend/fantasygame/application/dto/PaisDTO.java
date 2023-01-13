package br.com.backend.fantasygame.application.dto;

import java.util.List;

import br.com.backend.fantasygame.domain.entity.Pais;
import lombok.Getter;

@Getter
public class PaisDTO {
    private Long id;
    private String nome;
    private String sigla;

    public PaisDTO(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
        this.sigla = pais.getSigla();
    }

    public static List<PaisDTO> convertToList(List<Pais> countries) {
        return countries.stream()
            .map(PaisDTO::new)
            .toList();
    }
}
