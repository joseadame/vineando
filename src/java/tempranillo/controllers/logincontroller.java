/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionUsuarios;



/**
 *
 * @author joseadamefernandez
 */
public class logincontroller extends AbstractController {
    
    private GestionUsuarios gestorUsuarios;
    private GestionBodegas gestorBodegas;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
        
        ModelAndView vista;
            Map model = new HashMap();
            model.put("login",new Usuario());
            model.put("loginUsuario",new BusquedaVino());
            model.put("error","");
            vista = new  ModelAndView("loginUsuario",model);
            return vista;
        
    }
    
    
    
  
    
    
   
}

 
        
        
        
        
        
        
        


