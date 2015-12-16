/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.ArrayList;
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
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;


/**
 *
 * @author Jose Adame
 * Este controller se encarga de cambiar el nombre a una lista/bodega cuando el
 * usuario lo ha solicitado.
 * 
 */
public class cambioNombreBodegaController extends AbstractCommandController {
    
    private GestionBodegas gestionbodegas;

    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
        
        ModelAndView _modelAndView = null;
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        Bodega bodegaAux = (Bodega)o;
        String idbodega=hsr.getParameter("idbodega");
        
        gestionbodegas= new GestionBodegas();
        List<Bodega> bodegasusuario= new ArrayList();
        
        try{
        gestionbodegas.cambiarNombreBodega(Integer.parseInt(idbodega),bodegaAux.getDescripcion());
        bodegasusuario = gestionbodegas.listarBodegasUsuario(userSesion.getId());
        }
        catch (HibernateException e){
            Map model = new HashMap();
            model.put("error", e.getMessage().toString());
            model.put("busquedavino",new BusquedaVino());
            model.put("usuario",userSesion);
            model.put("exito","");
            model.put("listabodegas", bodegasusuario);
            model.put("nuevaBodega", bodegaAux);
            model.put("bodega",new Bodega());
            _modelAndView = new ModelAndView("listadobodegasusuario", model);
        
        
        }
        finally{
                Map model = new HashMap();
                model.put("exito","¡Bien, lista renombrada con exito!");
                model.put("error","");
                model.put("titulos", "Bodega con exito");
                model.put("listabodegas", bodegasusuario);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",userSesion);
                model.put("nuevaBodega",bodegaAux);
                model.put("bodega",new Bodega());
                _modelAndView = new ModelAndView("listadobodegasusuario", model);
            
        
        
        
        }
        
        
        
         return _modelAndView;
        
        
        
        
    }

    
    
}
