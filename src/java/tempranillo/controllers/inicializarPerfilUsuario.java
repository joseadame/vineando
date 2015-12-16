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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Password;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionUsuarios;



 


public class inicializarPerfilUsuario extends AbstractController {
    
    GestionUsuarios gesUsuarios;
     ModelAndView _modelAndView = null;
     List<Bodega> listabodegas;
   GestionBodegas gesBodegas;
    

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        gesUsuarios= new GestionUsuarios();
        gesBodegas = new GestionBodegas();
        _modelAndView = new ModelAndView();
        Usuario user= null;
         HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        int idusuario=userSesion.getId();
        try{
        user = gesUsuarios.getUsuarioPorId(idusuario);
        listabodegas=gesBodegas.listarBodegasUsuario(idusuario);
         Map model = new HashMap();
            model.put("listabodegas",listabodegas);
            model.put("error","");
            model.put("exito","");
            model.put("errorpassword","");
            model.put("exitopassword","");
            model.put("usuario",user);
            model.put("nuevaPassword", new Password());
            model.put("busquedavino",new BusquedaVino());
          
           
           _modelAndView = new ModelAndView("perfilUsuario", model);
        
         return _modelAndView;
        }
        catch(HibernateException he)
        {
            listabodegas=gesBodegas.listarBodegasUsuario(idusuario);
            Map model = new HashMap();
            model.put("listabodegas",listabodegas);
            model.put("usuario",user);
            model.put("errorpassword","");
            model.put("exitopassword","");
            model.put("error", he.getMessage().toString());
            model.put("nuevaPassword", new Password());
            model.put("busquedavino",new BusquedaVino());
            model.put("busquedavino",new BusquedaVino());
           
           _modelAndView = new ModelAndView("perfilUsuario", model);
        
           return _modelAndView;
        
        
        }
        
        
    }
    
    
    
}
