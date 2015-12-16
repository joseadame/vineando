/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import tempranillo.entidades.Vino;

/**
 *
 * @author Jose Adame
 */
public class altaVinoValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Vino.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Vino form = (Vino) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "error.formulario.nombre", "El nombre del vino no puede estar vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipovino", "error.formulario.tipvino", "El tipo del vino no puede estar vacio");
    }
}
