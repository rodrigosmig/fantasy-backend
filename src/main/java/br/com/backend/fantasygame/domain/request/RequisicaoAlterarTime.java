package br.com.backend.fantasygame.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class RequisicaoAlterarTime {
    @NotNull(message = "O nome do time é obrigatório")
    @NotBlank
    private String nomeTime;

    @NotNull(message = "A formação é obrigatória")
    private Long formacaoId;
}
