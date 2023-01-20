package br.com.backend.fantasygame.application.dto;

import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.entity.Pais;
import br.com.backend.fantasygame.domain.entity.Posicao;
import lombok.Getter;

import java.util.List;

@Getter
public class AppDataDTO {

    private final List<PaisDTO> paises;
    private final List<PosicaoDTO> posicoes;
    private final List<FormacaoDTO> formacoes;

    public AppDataDTO(List<Pais> paises, List<Posicao> posicoes, List<Formacao> formacoes) {
        this.paises = PaisDTO.convertToList(paises);
        this.posicoes = PosicaoDTO.convertToList(posicoes);
        this.formacoes = FormacaoDTO.convertToList(formacoes);
    }
}
