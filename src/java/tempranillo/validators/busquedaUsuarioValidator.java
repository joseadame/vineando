/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import tempranillo.entidades.BusquedaUsuario;

/**
 *
 * @author Jose Adame
 */
public class busquedaUsuarioValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return BusquedaUsuario.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {

        BusquedaUsuario form = (BusquedaUsuario) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "alias", "error.formulario.alias", "Debe introducir un alias valido");



    }
}
