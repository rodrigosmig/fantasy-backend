package br.com.backend.fantasygame.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueTeamValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTeam {
    String message() default "There is already a record with this value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
