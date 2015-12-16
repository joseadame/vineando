/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import tempranillo.entidades.Usuario;

/**
 *
 * @author Jose Adame
 * Esta clase se encarga de validar todos los campos del formulario de alta.
 * 
 * 
 */
public class altaUsuarioValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Usuario.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Usuario form = (Usuario) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "alias", "error.formulario.alias", "El Alias no puede estar vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.formulario.email", "El email no puede estar vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.formulario.password", "El password no puede estar vacio");
        validarEmail(form.getEmail(), errors);
    }

    public void validarEmail(String email, Errors error) {


        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(email);
        if (!mat.find()) {

            error.reject("email",
                    "No es un mail valido");
        }





    }
}
