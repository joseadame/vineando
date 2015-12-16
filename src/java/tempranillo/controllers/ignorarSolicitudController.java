/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author Jose Adame
 */
@Controller
public class ignorarSolicitudController {
    GestionUsuarios gesusuarios;
 
   @RequestMapping("ignorarsolicitud.htm")
   public @ResponseBody String aceptarSolicitudAmistad(HttpServletRequest hsr, HttpServletResponse hsr1)
    {
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        String idsolicitante = hsr.getParameter("idsol");
        gesusuarios = new GestionUsuarios();
        try{
        gesusuarios.ignorarSolicitudAmistad(Integer.parseInt(idsolicitante),userSesion.getId());
        return "ok";
        }
        catch(HibernateException e)
        {
        
        return "ko";
        }

    
    
    }
    
}
