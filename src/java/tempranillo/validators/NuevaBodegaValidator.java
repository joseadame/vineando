/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import tempranillo.entidades.Bodega;


/**
 *
 * @author Jose Adame
 */
public class NuevaBodegaValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Bodega.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Bodega form = (Bodega) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "error.nuevaBodega.descripcion", "La descripcion de la bodega no puede estar vacia");
       
}

    
}