package br.com.backend.fantasygame.validator;

import br.com.backend.fantasygame.domain.repository.RepositorioJogador;
import br.com.backend.fantasygame.domain.repository.RepositorioTime;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class ValidPlayersValidator implements ConstraintValidator<ValidPlayers, Set<Long>> {
    @Autowired
    public RepositorioJogador repositorioJogador;

    @Override
    public boolean isValid(Set<Long> idsJogadores, ConstraintValidatorContext context) {
        var teste = repositorioJogador.existsPlayers(idsJogadores);

        System.out.println(teste);
        return repositorioJogador.existsPlayers(idsJogadores);
    }
}
