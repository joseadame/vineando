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
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author Jose Adame
 */
public class buscarTipoVinoController extends AbstractController {
     private GestionVinos gesVinos;
    private GestionBodegas gesBodegas;
    private ModelAndView vista;
    private List<Vino> vinoencontrado;
    private List<Bodega> listabodegas;
    private List listazonas;
    private List listatipos;
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1)throws Exception {
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        String tipo = hsr.getParameter("tipo"); //cogemos la zona.
        
        
        try{
           gesVinos=new GestionVinos();
           gesBodegas= new GestionBodegas();
           if (userSesion!=null){
           listabodegas=gesBodegas.listarBodegasUsuario(userSesion.getId());
           }
           listazonas=gesVinos.listaZonasVino();
           listatipos=gesVinos.listaTiposVino();
           vinoencontrado=gesVinos.buscarVinoPorTipo(tipo);
           Map model = new HashMap();
           model.put("busquedavino",new BusquedaVino());
           model.put("vinos",vinoencontrado);
           model.put("listabodegas",listabodegas);
           model.put("usuario",userSesion);
           model.put("listazonasvino",listazonas);
           model.put("listadotipos",listatipos);
           
           vista = new ModelAndView("resultadosVino", model);
           
           return vista;
           
        
        
        }
        catch(HibernateException e) {
           
            gesBodegas= new GestionBodegas();
            listabodegas=gesBodegas.listarBodegasUsuario(userSesion.getId());
        
            Map model = new HashMap();
            model.put("error", e.getMessage().toString());
            model.put("listabodegas",listabodegas);
            model.put("usuario",userSesion);
            model.put("busquedavino", new BusquedaVino());
            vista = new ModelAndView("resultadosVino", model);
            return vista;
        
         }
       
        
        
       
        
        
        
    }
    
    
}
