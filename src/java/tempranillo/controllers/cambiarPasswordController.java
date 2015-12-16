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
 * @author Jose Adame
 */
public class cambiarPasswordController extends AbstractCommandController{
    
    GestionUsuarios gesUsuarios;
    GestionBodegas gesBodegas;
    @Override
    protected org.springframework.web.servlet.ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        ModelAndView _modelAndView = null;
        Password pass = (Password) o;
        gesUsuarios = new GestionUsuarios();
        gesBodegas = new GestionBodegas();
       try{  
        //verificamos que el password actual que ha puesto el usuario coincide.
       if (userSesion.getPassword().equals(pass.getPasswordold()))
       {
           if (pass.getNewpassword().equals(pass.getNewpassword2()))
           {
                gesUsuarios.cambiarPassword(userSesion,pass.getNewpassword());
                
              Usuario  user = gesUsuarios.getUsuarioPorId(userSesion.getId());
             List<Bodega> listabodegas = gesBodegas.listarBodegasUsuario(userSesion.getId());
                Map model = new HashMap();
                model.put("error","");
                model.put("exito","");
                model.put("errorpassword","");
                model.put("exitopassword", "¡Bien, Contraseña actualizada con exito!");
                model.put("listabodegas", listabodegas);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",user);
                model.put("nuevaPassword",new Password());
               // model.put("nuevaBodega",bodega);
               // model.put("bodega",new Bodega());
                
                _modelAndView = new ModelAndView("perfilUsuario", model);
        
           }
           else
           {
               List<Bodega> listabodegas = gesBodegas.listarBodegasUsuario(userSesion.getId());
               Map model = new HashMap();
               model.put("exito","");
               model.put("error","");
                model.put("errorpassword","Los passwords no coinciden");
                model.put("exitopassword","");
                model.put("listabodegas", listabodegas);
               model.put("busquedavino",new BusquedaVino());
                model.put("usuario",userSesion);
               // model.put("nuevaBodega",bodega);
               // model.put("bodega",new Bodega());
                model.put("nuevaPassword",new Password());
                _modelAndView = new ModelAndView("perfilUsuario", model);
               
           
               
           }
       
       
       }
       else{
              List<Bodega> listabodegas = gesBodegas.listarBodegasUsuario(userSesion.getId());
               Map model = new HashMap();
               model.put("exito","");
               model.put("error","");
                model.put("errorpassword","Password actual incorrecto, debe introducir el password actual");
                model.put("exitopassword","");
               model.put("listabodegas",listabodegas);
               model.put("busquedavino",new BusquedaVino());
                model.put("usuario",userSesion);
               // model.put("nuevaBodega",bodega);
               // model.put("bodega",new Bodega());
                model.put("nuevaPassword",new Password());
                _modelAndView = new ModelAndView("perfilUsuario", model);
         
       
       
       }
       }
       catch(HibernateException e)
       {
           
                List<Bodega> listabodegas = gesBodegas.listarBodegasUsuario(userSesion.getId());
                Map model = new HashMap();
                model.put("error",e.getLocalizedMessage());
                model.put("exito","");
                model.put("listabodegas",listabodegas);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",userSesion);
               // model.put("nuevaBodega",bodega);
               // model.put("bodega",new Bodega());
                _modelAndView = new ModelAndView("perfilUsuario", model);
       
       }
        
       return _modelAndView;
    }

   
    
}
