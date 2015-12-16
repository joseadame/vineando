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
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Password;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author joseadamefernandez
 */
public class CambiarDatosUsuario extends AbstractCommandController{

    GestionBodegas gesBodegas;
    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
    
        GestionUsuarios gesUsuarios;
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        int idusuario=userSesion.getId();
        ModelAndView _modelAndView;
    
     Usuario user = (Usuario) o;
    try{
        
       
        
        gesUsuarios = new GestionUsuarios();
        gesUsuarios.modificarDatosUsuario(user,idusuario);
       
        user = gesUsuarios.getUsuarioPorId(idusuario);
        
        session.setAttribute("usuarioEnSession",user);
        gesBodegas=new GestionBodegas();
        List<Bodega> listabodegas=gesBodegas.listarBodegasUsuario(idusuario);
        
                Map model = new HashMap();
                model.put("error","");
                model.put("exito", "¡Bien, Datos actualizados con exito!");
                model.put("errorpassword","");
                model.put("exitopassword","");
                model.put("listabodegas",listabodegas);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",user);
                model.put("nuevaPassword",new Password());
               // model.put("nuevaBodega",bodega);
               // model.put("bodega",new Bodega());
                _modelAndView = new ModelAndView("perfilUsuario", model);
        
        
        
    
    }
    catch(HibernateException he){
        gesBodegas=new GestionBodegas();
        List<Bodega> listabodegas=gesBodegas.listarBodegasUsuario(idusuario);
                Map model = new HashMap();
                model.put("error",he.getLocalizedMessage());
                model.put("exito","");
                model.put("errorpassword","");
                model.put("exitopassword","");
                model.put("listabodegas", listabodegas);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",user);
                model.put("nuevaPassword",new Password());
               // model.put("nuevaBodega",bodega);
               // model.put("bodega",new Bodega());
                _modelAndView = new ModelAndView("perfilUsuario", model);
        
        
    
    }
        
    
    return _modelAndView;
    
    
    
    }
    
}
