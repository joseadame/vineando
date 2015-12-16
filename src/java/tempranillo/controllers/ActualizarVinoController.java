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
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author Jose Adame
 */
public class ActualizarVinoController extends AbstractController{

    private GestionBodegas gestionbodegas;
    private GestionVinos gestionvinos;
    

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
      ModelAndView _modelAndView = null;
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        String idvino = hsr.getParameter("idvino");
        String uva= hsr.getParameter("uvas");
        String zona= hsr.getParameter("zona");
        String pais= hsr.getParameter("pais");
        String barcode= hsr.getParameter("barcode");
        String dato= hsr.getParameter("opcion");
        gestionvinos= new GestionVinos();
        gestionbodegas = new GestionBodegas();
        List<Bodega> bodegasusuario= new ArrayList();
        
        try{
            
        
        gestionvinos.actualizarDatosVino(uva,zona,pais,barcode,Integer.parseInt(idvino),dato);
        
        bodegasusuario = gestionbodegas.listarBodegasUsuario(userSesion.getId());
        }
        catch (HibernateException e){
            Map model = new HashMap();
            model.put("error", e.getMessage().toString());
            
            model.put("usuario",userSesion);
            model.put("exito","");
           
          //  _modelAndView = new ModelAndView("infoVino", model);
        
        
        }
        finally{
                Map model = new HashMap();
                
                model.put("listabodegas", bodegasusuario);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",userSesion);
                _modelAndView = new ModelAndView("infoVino", model);
            
        
        
        
        }
        
        
        
         return _modelAndView;
        
        
    }
    
}
