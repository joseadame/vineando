/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionNotificacion;
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author Jose
 */
public class ActualizacionesGlobalesController extends AbstractController{
    
    private GestionUsuarios gestorUsuarios;
    private GestionBodegas gestorBodegas;
    private GestionNotificacion gestorNotificaciones;
   
    
    

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
        
        gestorUsuarios= new GestionUsuarios();
        gestorBodegas = new GestionBodegas();
        gestorNotificaciones = new GestionNotificacion();
        ModelAndView vista;
        Map model;
        Usuario user;
        HttpSession session = hsr.getSession();
        user = (Usuario) session.getAttribute("usuarioEnSession");
        
        try{
            model= new HashMap();
            if(user!=null)
            {
             List<Bodega> listadobodegas=gestorBodegas.listarBodegasUsuario(user.getId());
             model.put("listabodegas",listadobodegas);
            }
             String urlxslt =String.format("%s%ssources%sXmlNotificacion.xslt", hsr.getRealPath(""),File.separator,File.separator);
             List notificaciones = gestorNotificaciones.obtenerActualizaciones(urlxslt,10,1); 
             
             
             
             model.put("busquedavino", new BusquedaVino());
             model.put("UsuarioSesion",user);
             model.put("listanotificaciones",notificaciones);
             model.put("error","");
             vista=new ModelAndView("global",model);
             return vista;
        }
        catch(HibernateException e)
        {
            model = new HashMap();
            model.put("error", e.getMessage());
            vista=new ModelAndView("global",model);
            return vista;
        
        
        }
        
     
    }
    
    
    
}
