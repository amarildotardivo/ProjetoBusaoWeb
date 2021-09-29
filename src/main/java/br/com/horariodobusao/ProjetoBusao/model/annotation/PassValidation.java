package br.com.horariodobusao.ProjetoBusao.model.annotation;

import java.lang.annotation.*;
import javax.validation.*;

@Documented
@Constraint(validatedBy = PassValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassValidation {
    String message() default "Senha inválida.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
