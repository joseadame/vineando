/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
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
 * @author Oscar
 */
@Controller
public class paginarController {

    GestionNotificacion gestnotificacion;
    @RequestMapping("paginarNotificacion.htm")
    public @ResponseBody String paginarNotificacion(HttpServletRequest hsr, HttpServletResponse hsr1) {        
        int numPage = Integer.parseInt(hsr.getParameter("nPage"));
       StringBuffer resp= new StringBuffer();
       HttpSession session = hsr.getSession();//obtenemos el usuario logueado.
       Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
       gestnotificacion = new GestionNotificacion();
       String urlxslt =String.format("%s%ssources%sXmlNotificacion.xslt", hsr.getRealPath(""),File.separator,File.separator);
       List lista= gestnotificacion.obtenerNotificaciones(userSesion.getId(),urlxslt,5,numPage); 
       
       for (int i=0;i<lista.size();i++)
           
       {
           String aux = (String)lista.get(i);
           
           resp.append(aux);
       
       }
       
      
        
        return resp.toString();
    }
}
