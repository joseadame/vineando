package tempranillo.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionVinos;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class indexController extends AbstractCommandController{
//...
       private GestionVinos gesvinos;

    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
        
        Usuario loginuser=(Usuario)o;
        gesvinos= new GestionVinos();
        List listanuevosvinos=gesvinos.ultimosVinosAlta();
        
        ModelAndView model = new ModelAndView("login");
        model.addObject("busquedavino", new BusquedaVino());
        model.addObject("login", loginuser);
        model.addObject("listanuevosvinos",listanuevosvinos);
		return model;
    }

};