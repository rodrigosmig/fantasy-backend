package br.com.backend.fantasygame.domain.request;

import br.com.backend.fantasygame.validator.ValidPlayers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoSalvarJogadores {

    @NotNull
    @Size(min = 11, max = 11)
    @ValidPlayers
    private Set<Long> jogadoresIds;
}
