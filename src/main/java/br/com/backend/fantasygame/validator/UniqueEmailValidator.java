package br.com.backend.fantasygame.validator;

import br.com.backend.fantasygame.domain.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    public RepositorioUsuario repositorioUsuario;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !repositorioUsuario.existsByEmail(email);
    }
}
