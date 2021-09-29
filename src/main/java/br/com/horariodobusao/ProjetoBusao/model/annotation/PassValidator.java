
package br.com.horariodobusao.ProjetoBusao.model.annotation;

import javax.validation.*;

public class PassValidator implements ConstraintValidator<PassValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) return false;
        return value.matches("\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z");
        
        //Obs Regex
        /*
        (?=.*[0-9]) deve ter pelo menos 1 digito
        (?=.*[a-z]) deve ter pelo menos 1 letra minúscula
        (?=.*[A-Z]) deve ter pelo menos 1 letra maiúscula
        (?=.*[@#$%^&+=]) deve ter pelo menos 1 caracter especial
        .{8,} limita os caracteres em no mínimo 8
        \A \z para garantir que toda a string seja validada
        \S{8,} verifica se tem espaço em branco no limitador de caracteres
        Foi substituído todos os pontos por \S, pois se tiver espaço na string a senha, irá evitar fazer todas as verificações até falhar por causa do espaço
        */
    }
    
}
