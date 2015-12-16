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
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionUsuarios;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author joseadamefernandez
 * Esta clase se encarga de la busqueda de vinos por codigo de barras o nombre
 */
public class BusquedaVinoController extends AbstractCommandController {
    
    private GestionVinos gesVinos;
    private GestionBodegas gesBodegas;
    private GestionUsuarios gesUsuarios;
    private ModelAndView vista;
    private List<Vino> vinoencontrado;
    private List<Bodega> listabodegas;
    private List listazonas;
    private List listatipos;
    private List usuariosencontrados;
    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        
        BusquedaVino vino = (BusquedaVino)o;
        
        try{
           gesVinos=new GestionVinos();
           gesBodegas= new GestionBodegas();
           gesUsuarios = new GestionUsuarios();
           gesVinos = new GestionVinos();
           if(userSesion!=null){
              listabodegas=gesBodegas.listarBodegasUsuario(userSesion.getId());
           }
           listazonas=gesVinos.listaZonasVino();
           listatipos=gesVinos.listaTiposVino();
           vinoencontrado=gesVinos.buscarVino(vino);
           usuariosencontrados = gesUsuarios.busquedaUsuarioPorAlias(vino.getCadenabusqueda());
           Map model = new HashMap();
           model.put("busquedavino",vino);
           model.put("vinos",vinoencontrado);
           model.put("listabodegas",listabodegas);
           model.put("usuario",userSesion);
           model.put("listazonasvino",listazonas);
           model.put("listadotipos",listatipos);
           model.put("usuariosencontrados",usuariosencontrados);
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
            model.put(getCommandName(), vino);
            vista = new ModelAndView("resultadosVino", model);
            return vista;
        
         }
       
        
        
       
        
        
        
    }
    
    
    
    
    
    
}
