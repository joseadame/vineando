/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

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
import tempranillo.entidades.PruebaVino;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionComentarios;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author Jose Adame
 * Controlador que muestra la vista de los logros conseguidos por un usuario.
 * 
 */
public class getLogrosUsuarioController extends AbstractController {
    
    
    private GestionBodegas gesbodegas;
    private GestionComentarios gestorComentarios;
    private GestionVinos gesvinos;
    private ModelAndView vista;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
     
        HttpSession session = hsr.getSession();//obtenemos el usuario logueado.
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        
        String idvino = hsr.getParameter("idvino");
        
        
        Map model = new HashMap();
       if (userSesion!=null)
       {
        try{
          
           
           
           gesvinos=new GestionVinos();
           gesbodegas= new GestionBodegas();
           gestorComentarios = new GestionComentarios();
           List<Logro> listalogros = Logro.getLogrosUsuario(userSesion.getId());
           List<Bodega> listadobodegas = gesbodegas.listarBodegasUsuario(userSesion.getId());  
           model.put("listabodegas",listadobodegas);
           model.put("UsuarioSesion",userSesion);
           model.put("listalogros",listalogros);
           
           model.put("bodega", new Bodega());
           
          
           model.put("updatevino",new Vino());
           model.put("busquedavino", new BusquedaVino());
           model.put("pruebavino", new PruebaVino());
           model.put("checkin","");
           model.put("error","");
          
          
           
           
           vista = new ModelAndView("logrosUsuario", model);
           return vista;
            
        
        }
        catch (HibernateException he)
        {
        
           
           List<Bodega> listadobodegas = gesbodegas.listarBodegasUsuario(userSesion.getId());  
           model.put("listabodegas",listadobodegas);
           model.put("UsuarioSesion",userSesion);
           model.put("bodega", new Bodega());
           model.put("error",he.getMessage());
           model.put("busquedavino", new BusquedaVino());
           
           vista = new ModelAndView("logrosUsuario", model);
           return vista;
        
        
        }
       }
       else
       {
       
           //si el usuariono esta logueado le llevamos a la pagina de login.
            ModelAndView vista;
            model.put("login",new Usuario());
            model.put("loginUsuario",new BusquedaVino());
            model.put("error","");
            vista = new  ModelAndView("loginUsuario",model);
            return vista;
       
       
       
       
       }
        
        
    }
    
}
