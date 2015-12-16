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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionUsuarios;



/**
 * 
 * @author joseadamefernandez
 * Esta clase se encarga de controlar el registro del usuario en Tempranillo
 */
public class registroController extends AbstractController{
    
    private GestionUsuarios gesUsuarios;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
            ModelAndView vista;
            Map model = new HashMap();
            gesUsuarios = new GestionUsuarios();
            List listadousuarios = gesUsuarios.ultimosUsuariosAlta();
            model.put("listadousuarios",listadousuarios);
            model.put("login",new Usuario());
            model.put("busquedavino",new BusquedaVino());
            model.put("error","");
            //solo para la beta
            model.put("mensajebeta","");
            //
            vista = new  ModelAndView("RegistroUsuario",model);
            return vista;
        
        
    }

    
        
    }

 
