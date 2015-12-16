/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.sistemanotificaciones.NotificacionDB;
import tempranillo.entidades.Seguimiento;
import tempranillo.entidades.sistemalogros.TipoAccion;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.sistemanotificaciones.Notificacion;
import tempranillo.modelo.GestionEventos;
import tempranillo.modelo.GestionNotificacion;
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author Jose Adame
 */


@Controller
public class addAmigoController {
    
    GestionUsuarios gestorusuarios;
    GestionEventos gestorEventos;
    GestionNotificacion gestorNotificacion;
    
    @RequestMapping("addamigo.htm")
    public @ResponseBody String addAmigo(HttpServletRequest hsr, HttpServletResponse hsr1) throws IOException
    {
           HttpSession session = hsr.getSession();
           Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
           String idamigo= hsr.getParameter("idamigo");
           String mensaje = hsr.getParameter("mensaje");
           
           gestorusuarios = new GestionUsuarios();
           gestorEventos = new GestionEventos();
           
           try{
               
               Usuario useramigo= gestorusuarios.getUsuarioPorId(Integer.parseInt(idamigo));
               if (useramigo.getTipoperfil().equals("privado"))
               {
                   gestorusuarios.enviarSolicitudAmigo(userSesion.getId(),Integer.parseInt(idamigo), mensaje);
               }
               else
               {
                   Seguimiento seg = new Seguimiento();
                   seg.setFecha(new Date());
                   seg.setIdamigo(Integer.parseInt(idamigo));
                   seg.setIdusuario(userSesion.getId());
                   gestorusuarios.addSeguimiento(seg);
                   gestorEventos.comprobarEvento(TipoAccion.SEGUIMIENTO, userSesion.getId());
                   gestorNotificacion= new GestionNotificacion();
                   gestorNotificacion.createNotificacion(
                           new Notificacion(Notificacion.TipoNotificacion.ACEPTARSEGUIMIENTO, userSesion, useramigo)
                           );
                   //TODO: gestorEventos.comprobarEvento(TipoAccion.SEGUIMIENTO,Integer.parseInt(idamigo), new MutableInteger());
               }
               return "ok";
           }
           catch(HibernateException e)
           {
               return "ko";
           }
    }
}
