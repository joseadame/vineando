/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author joseadamefernandez
 * 
 * Esta clase se encarga de hacer el logout y dirigirnos a la pagina principal borrando la sesion del usuario.
 * 
 * 
 */
public class LogOutController extends AbstractController{
    
    private GestionVinos gestionVinos;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
        
      HttpSession session = hsr.getSession(false);
      
      if (session != null) {
            session.removeAttribute("usuarioEnSession");
            session.invalidate();
      }
      gestionVinos = new GestionVinos();
      
      List listanuevosvinos=gestionVinos.ultimosVinosAlta();
      
      ModelAndView index = new ModelAndView("login");
      index.addObject("busquedavino", new BusquedaVino());
      index.addObject("listanuevosvinos",listanuevosvinos);
      index.addObject("login",new Usuario());
      return index;
        
        
        
        
    }
    
    
    
    
    
    
}
