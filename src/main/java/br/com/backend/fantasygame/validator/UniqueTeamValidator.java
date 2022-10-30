package br.com.backend.fantasygame.validator;

import br.com.backend.fantasygame.domain.repository.RepositorioTime;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTeamValidator implements ConstraintValidator<UniqueTeam, String> {
    @Autowired
    public RepositorioTime repositorioTime;

    @Override
    public boolean isValid(String nomeTime, ConstraintValidatorContext context) {
        return !repositorioTime.existsByNome(nomeTime);
    }
}
