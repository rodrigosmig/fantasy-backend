package br.com.backend.fantasygame.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoListarJogador {
    private String nome;
    private Long paisId;
    private Long posicaoId;

    public boolean isRequestNull() {
        return isNomeNull() && isPositionNull() && isPaisNull();
    }

    public boolean isNomeNull() {
        return Objects.isNull(nome) || nome.equals("");
    }

    public boolean isPositionNull() {
        return Objects.isNull(posicaoId);
    }

    public boolean isPaisNull() {
        return Objects.isNull(paisId);
    }
}
