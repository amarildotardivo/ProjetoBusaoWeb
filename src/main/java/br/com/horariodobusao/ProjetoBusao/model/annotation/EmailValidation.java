package br.com.horariodobusao.ProjetoBusao.model.annotation;

import java.lang.annotation.*;
import javax.validation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
    String message() default "Email inválido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
