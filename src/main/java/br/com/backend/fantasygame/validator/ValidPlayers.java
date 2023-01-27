package br.com.backend.fantasygame.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidPlayersValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPlayers {
    String message() default "Invalid players";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
