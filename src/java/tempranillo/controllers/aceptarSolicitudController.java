/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.sistemanotificaciones.NotificacionDB;
import tempranillo.entidades.sistemalogros.TipoAccion;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.sistemanotificaciones.Notificacion;
import tempranillo.modelo.GestionEventos;
import tempranillo.modelo.GestionNotificacion;
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author Jose Adame
 * Controlador que permite a un usuario aceptar una solicitud de amistad.
 */

@Controller
public class aceptarSolicitudController {
    
    GestionUsuarios gesusuarios;
    GestionEventos gestorEventos;
    GestionNotificacion gestorNotificacion;
    
    @RequestMapping("aceptarsolicitud.htm")
    public @ResponseBody String aceptarSolicitudAmistad(HttpServletRequest hsr, HttpServletResponse hsr1)
    {        
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        String idsolicitante = hsr.getParameter("idsol");
        gesusuarios = new GestionUsuarios();
        gestorEventos = new GestionEventos();
        
        try{
            gesusuarios.aceptarSolicitudAmistad(Integer.parseInt(idsolicitante),userSesion.getId());
            gestorEventos.comprobarEvento(TipoAccion.SEGUIMIENTO, userSesion.getId());

            gestorNotificacion = new GestionNotificacion();
             Usuario useramigo = gesusuarios.getUsuarioPorId(Integer.parseInt(idsolicitante));
            gestorNotificacion.createNotificacion(
                    new Notificacion(Notificacion.TipoNotificacion.SEGUIMIENTOAMIGO, useramigo, userSesion )
                    );
            return "ok";
        }catch(HibernateException e){
            Logger.getLogger(aceptarSolicitudController.class.getName()).log(Level.SEVERE, null, e);
            return "ko";
        }
    }
    
    
    
}
