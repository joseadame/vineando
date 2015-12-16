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
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author Jose Adame
 */
public class whistlistUsuarioController extends AbstractController{
    
    GestionUsuarios gesUsuarios;
    GestionBodegas gesBodegas;


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
         ModelAndView vista;
      HttpSession session = hsr.getSession();
      Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
       String idusuario = hsr.getParameter("idusuario"); //recogemos el id del usuario sobre el que vamos a ver sus vinos catados. 
       try{
       gesUsuarios = new GestionUsuarios();
       gesBodegas = new GestionBodegas();
       List lista = gesUsuarios.obtenerWishListUsuario(Integer.parseInt(idusuario)); //obtenemos las catas del usuario.
       List listabodegas = gesBodegas.listarBodegasUsuario(userSesion.getId());
               
           Map model =new HashMap();
           model.put("usuarioSesion",userSesion);
           model.put("listadobodegas",listabodegas);
           model.put("listawish",lista);
           model.put("busquedavino",new BusquedaVino());
           model.put("error","");
           model.put("titulopagina","en tu whislist");
           
           vista = new ModelAndView("wishlist", model);
           return vista;
       
       }
       catch (HibernateException e)
       {
           List listabodegas = gesBodegas.listarBodegasUsuario(userSesion.getId());
           Map model =new HashMap();
           model.put("usuarioSesion",userSesion);
           model.put("listadobodegas",listabodegas);
           //model.put("listacatasusuario",listadocatas);
           model.put("busquedavino",new BusquedaVino());
           model.put("error",e.getMessage());
           
           vista = new ModelAndView("listaVinosCatados", model);
           return vista;
           
       
       
       
       }
      
        
        
        
        
        
    }
    
}
