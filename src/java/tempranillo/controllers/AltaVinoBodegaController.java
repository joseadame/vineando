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
import tempranillo.entidades.AltaVinoBodega;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author joseadamefernandez
 */
public class AltaVinoBodegaController extends AbstractCommandController{
    
    private GestionVinos gestorvinos;
    private GestionBodegas gestorbodegas;
    private ModelAndView _modelAndView;
    private Bodega bodega;
    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        AltaVinoBodega bodegaaux = (AltaVinoBodega)o;
        
        String idvino=hsr.getParameter("idvino");
        gestorvinos= new GestionVinos();
        gestorbodegas = new GestionBodegas();
       
        try{ 
       
        gestorbodegas.altaVinoEnBodega(bodegaaux.getIdbodega(), Integer.parseInt(idvino));//damos de alta el vino en la bodega.
        bodega= gestorbodegas.buscarBodega(bodegaaux.getIdbodega());
       
       }
       catch (HibernateException e)
       {
            Map model = new HashMap();
            model.put("error", e.getMessage().toString());
            model.put("busquedavino",new BusquedaVino());
            List<Bodega> listabodegas = new ArrayList<Bodega>();
             listabodegas= gestorbodegas.listarBodegasUsuario(userSesion.getId());
           
            model.put("listabodegas",listabodegas);
            //model.put("vino", vino);
            _modelAndView = new ModelAndView("infoVino", model);
           
       
       }
       finally{
           
           Map model = new HashMap();
           
            model.put("busquedavino",new BusquedaVino());
            List<Vino> listavinosbodegas = new ArrayList<Vino>();
            listavinosbodegas= gestorbodegas.recuperarVinosLista(bodegaaux.getIdbodega());   //listarVinosBodega(bodegaaux.getIdbodega());
            List<Bodega> listabodegas = new ArrayList<Bodega>();
            listabodegas= gestorbodegas.listarBodegasUsuario(userSesion.getId());
            model.put("listadovinos",listavinosbodegas);
            model.put("listadobodegas", listabodegas);
            model.put("bodega",bodega);
            model.put("usuario", userSesion);
            model.put("aliasusuario", userSesion.getAlias());
            model.put("error","");
            model.put("exito","¡Bien, ha añadido con exito un vino a su lista!");
            _modelAndView = new ModelAndView("bodegaUsuario", model);
           
       
       
       
       }
        
        
        
        
        return _modelAndView;
        
        
    }
    
    
    
    
    
    
    
    
    
}
