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
import tempranillo.entidades.Vino;
import tempranillo.modelo.GestionBodegas;

/**
 *
 * @author joseadamefernandez
 */
public class BorrarVinoDeBodegaController extends AbstractController{
    
     
    private GestionBodegas gestionbodegas;
    private ModelAndView _modelAndView;
    private List<Vino> listavinos;
    private List<Bodega> listabodegas;
    private Bodega bodega;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
         HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        String idvino=hsr.getParameter("idvino");
        String idbodega = hsr.getParameter("idbodega");
        gestionbodegas= new GestionBodegas();
        
        try{
        
            
            gestionbodegas.borrarVinoDeBodega(Integer.parseInt(idbodega), Integer.parseInt(idvino));
            listavinos = gestionbodegas.recuperarVinosLista(Integer.parseInt(idbodega));//listarVinosBodega(Integer.parseInt(idbodega));//obtenemos la lista actualizada de los vinos de la bodega.
            listabodegas = gestionbodegas.listarBodegasUsuario(userSesion.getId());
            bodega=gestionbodegas.buscarBodega(Integer.parseInt(idbodega));
            Map model = new HashMap();
            
            model.put("busquedavino",new BusquedaVino());
            model.put("listadobodegas",listabodegas);
            model.put("listadovinos",listavinos);
            model.put("usuario",userSesion);
            model.put("bodega",bodega);
            model.put("exito","¡Has eliminado con exito el vino!");
            model.put("error","");
           
            //model.put("vino", vino);
          return  _modelAndView = new ModelAndView("bodegaUsuario", model);
            
            
            
        }
        catch(HibernateException he){
            Map model = new HashMap();
            listabodegas = gestionbodegas.listarBodegasUsuario(userSesion.getId());
            listavinos = gestionbodegas.recuperarVinosLista(Integer.parseInt(idbodega));//obtenemos la lista actualizada de los vinos de la bodega.
            model.put("error", he.getMessage().toString());
            model.put("busquedavino",new BusquedaVino());
            model.put("listabodegas",listabodegas);
            model.put("listadovinos", listavinos);
            model.put("usuario",userSesion);
            model.put("exito","");
            
            
            return  _modelAndView = new ModelAndView("bodegaUsuario", model);     
        
        }
        
        
        
        
        
    }
    
}
