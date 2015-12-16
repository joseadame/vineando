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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;

/**
 *
 * @author joseadamefernandez
 */
public class BorrarBodegaController extends AbstractController{
    
    
    private GestionBodegas gestionbodegas;
    private ModelAndView _modelAndView;
    private List<Bodega> listabodegas;
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        String idbodega=hsr.getParameter("idbodega");
        
        gestionbodegas= new GestionBodegas();
        
        try{
        
            
            gestionbodegas.borrarBodega(Integer.parseInt(idbodega));
            listabodegas = gestionbodegas.listarBodegasUsuario(userSesion.getId());//devolvemos el listado de las bodegas del usuario actualizadas despues del borrado.
            
            Map model = new HashMap();
            
            model.put("busquedavino",new BusquedaVino());
            model.put("listabodegas",listabodegas);
            model.put("nuevaBodega",new Bodega());
            model.put("bodega",new Bodega());
            model.put("usuario",userSesion);
            model.put("error","");
            model.put("exito","¡Bien, lista borrada con exito!");
            //model.put("vino", vino);
          return  _modelAndView = new ModelAndView("listadobodegasusuario", model);
            
            
            
        }
        catch(HibernateException he){
            Map model = new HashMap();
            listabodegas = gestionbodegas.listarBodegasUsuario(userSesion.getId());
            model.put("error", he.getMessage().toString());
            model.put("exito","");
            model.put("busquedavino",new BusquedaVino());
            model.put("listabodegas",listabodegas);
            model.put("nuevaBodega",new Bodega());
            model.put("usuario",userSesion);
            model.put("bodega",new Bodega());
            return  _modelAndView = new ModelAndView("listadobodegasusuario", model);     
        
        }
        
        
        
    }
    
    
    
    
    
    
}
