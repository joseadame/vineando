/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.webservice.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Comentario;
import tempranillo.entidades.Vino;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionComentarios;
import tempranillo.modelo.GestionNotificacion;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author Oscar
 */
@Controller
@RequestMapping(value = "/not")
public class notificacionesController {

    GestionNotificacion _gestionNotificacion = null;
    
    public notificacionesController() {
        _gestionNotificacion = new GestionNotificacion();
    }

    @RequestMapping(value="/{idUsuario}", method= RequestMethod.GET,  produces = "application/json")
    public void  obtenerNotificaciones(@PathVariable int idUsuario) {
                _gestionNotificacion.obtenerNotificacionesUsuarios(idUsuario);                
    }
}
