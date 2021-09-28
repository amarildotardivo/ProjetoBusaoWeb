package br.com.horariodobusao.ProjetoBusao.model.annotation;

import javax.validation.*;

public class EmailValidator implements javax.validation.ConstraintValidator<EmailValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) return false;
        if(value.contains(" ")) return false;
        return value.matches("[\\W\\S]+[@]+[\\W\\S]+[.]+[\\W\\S]+");
        
        
    }
    
}
