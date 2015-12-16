/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionUsuarios;
import tempranillo.modelo.GestionNotificacion;

/**
 *
 * @author Jose Adame
 */
public class VerPerfilUsuarioController extends AbstractController {
    
    
    GestionBodegas gestorBodegas;
    GestionUsuarios gestorusuarios;
    GestionNotificacion gestorNotificaciones;
    ModelAndView vista;
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
        
        
            //sacamos los datos que necesitamos para mostrar la pagina.
             HttpSession session = hsr.getSession();
             Usuario user = (Usuario)session.getAttribute("usuarioEnSession");
             gestorBodegas = new GestionBodegas();
             gestorusuarios = new GestionUsuarios();
             gestorNotificaciones = new GestionNotificacion();
             List<Bodega> listadobodegas=gestorBodegas.listarBodegasUsuario(user.getId());
             int vinosprobados = gestorusuarios.numeroCheckinsUsuario(user);
             Long numerobodegas = gestorusuarios.numeroBodegasUsuario(user);
             Long wishlist=gestorBodegas.numeroVinosEnWishList(user.getId());
             List listasolicitudes = gestorusuarios.solicitudesAmistad(user.getId());
             Usuario usuarioactivo = gestorusuarios.getUsuarioPorId(user.getId()); 
             //List listaamigos=gestorusuarios.listarAmigos(user.getId());
             List listaamigos=gestorusuarios.listaSeguimiento(user.getId());
             String urlxslt =String.format("%s%ssources%sXmlNotificacion.xslt", hsr.getRealPath(""),File.separator,File.separator);
             List notificaciones = gestorNotificaciones.obtenerNotificaciones(user.getId(),urlxslt,10,1); 
             Map  model= new HashMap();
             model.put("listabodegas",listadobodegas);
             model.put("busquedavino", new BusquedaVino());
             model.put("UsuarioSesion",usuarioactivo);
             model.put("usuario",user);
             model.put("probados",vinosprobados);
             model.put("numerobodegas",numerobodegas);
             model.put("wishlist",wishlist);
             model.put("listasolicitudes", listasolicitudes);
             model.put("listaamigos",listaamigos);
             model.put("listanotificaciones",notificaciones);
             vista=new ModelAndView("indexUsuario",model);
             return vista;
                
    }
    
}
