/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.sistemalogros.TipoAccion;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionComentarios;
import tempranillo.modelo.GestionEventos;

/**
 *
 * @author Jose Adame
 */
@Controller
public class puntuarComentarioController {

    GestionComentarios gestorComentarios;
    GestionEventos gestorEventos;

    @RequestMapping("puntuarComentario.htm")
    public @ResponseBody
    String Puntuar(HttpServletRequest hsr, HttpServletResponse hsr1) {
        //ModelAndView _modelAndView = null;
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        String idcomentario = hsr.getParameter("idcomentario");
        String accion = hsr.getParameter("accion");

        try {
            gestorComentarios = new GestionComentarios();
            gestorEventos = new GestionEventos();
            int idusuario = gestorComentarios.recuperarIdUsuarioComentario(Integer.parseInt(idcomentario));
            
            if (accion.equals("p")) {

                if (userSesion.getId() != idusuario) {
                    if (gestorComentarios.aumentarPuntuacion(Integer.parseInt(idcomentario), userSesion.getId())) {
                       gestorEventos.comprobarEvento(TipoAccion.VOTOPOSITIVO  , idusuario);
                        return "aceptado";
                    } else {
                        return "Ya has votado este comentario anteriormente";
                    }
                } else {
                    return "No puede votarse asimismo";
                }
            } else {
                if (userSesion.getId() != idusuario) {
                    if (gestorComentarios.votonegativo(Integer.parseInt(idcomentario), userSesion.getId())) {
                        return "aceptado";
                    } else {
                        return "Ya has votado este comentario anteriormente";
                    }
                } else {
                    return "No puede votarse asimismo";
                }

            }      
        } catch (HibernateException e) {
            Logger.getLogger(puntuarComentarioController.class.getName()).log(Level.SEVERE, null, e);
            return "no aceptado";
        }
    }
}
