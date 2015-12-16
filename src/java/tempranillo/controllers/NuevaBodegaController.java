/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.net.BindException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;

/**
 *
 * @author joseadamefernandez
 */
public class NuevaBodegaController extends AbstractController{
    
    
    GestionBodegas gestorBodegas;
    String error;
    String exito;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
       
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        ModelAndView _modelAndView = null;
        List bodegasusuario = new ArrayList();
        // if (userSesion != null) {
        //esto habria que reemplazarlo por injeccion directa en spring.
        gestorBodegas = new GestionBodegas();
        try {
            bodegasusuario = gestorBodegas.listarBodegasUsuario(userSesion.getId());
        } catch (HibernateException e) {
            //TODO: Realizar log del error.
            Map model = new HashMap();
            exito="";
            model.put("exito",exito);
            model.put("error", e.getMessage().toString());
            model.put("busquedavino",new BusquedaVino());
            model.put("usuario",userSesion);
            model.put("listabodegas", bodegasusuario);
            model.put("nuevaBodega", new Bodega());
            _modelAndView = new ModelAndView("inicioUsuario", model);
        } finally {
                Map model = new HashMap();
                error="";
                exito="";
                model.put("exito",exito);
                model.put("error",error);
                model.put("titulos", "Bodega con exito");
                model.put("listabodegas", bodegasusuario);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",userSesion);
                model.put("nuevaBodega",new Bodega());
                model.put("bodega",new Bodega());
                _modelAndView = new ModelAndView("listadobodegasusuario", model);
        }
        /*} else {
        
        //el usuarion no esta logueado vamos al login.
        _modelAndView = new ModelAndView("login");
        }*/
        return _modelAndView;
        
        
    }

    
    
    
}
