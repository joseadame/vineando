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
import org.hibernate.HibernateException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaUsuario;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionNotificacion;
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author Jose Adame
 * Controlador para la busqueda de usuarios
 * 
 */
public class BusquedaUsuarioController extends AbstractController {

    private GestionUsuarios gusuarios;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        String idusuario = hsr.getParameter("idusuario");
        List<Bodega> listabodegas;
        GestionBodegas gesbodegas;
        GestionNotificacion gestorNotificaciones = new GestionNotificacion();
        gusuarios = new GestionUsuarios(); //esto se puede sustituir por inyeccion directa de spring
        gesbodegas = new GestionBodegas();
        
        if (userSesion!=null)
        {
        
            try{
            

            Usuario user = gusuarios.getUsuarioPorId(Integer.parseInt(idusuario)) ;
            listabodegas = gesbodegas.listarBodegasUsuario(userSesion.getId()); 
            
            //hay que retornar la vista
            
            if (user.getId()==userSesion.getId()){
                int vinosprobados = gusuarios.numeroCheckinsUsuario(userSesion);
                Long numerobodegas= gusuarios.numeroBodegasUsuario(userSesion);
                Long vinoswhishlits = gesbodegas.numeroVinosEnWishList(userSesion.getId());
                List listasolicitud = gusuarios.solicitudesAmistad(userSesion.getId());
               // List listaamigos = gusuarios.listarAmigos(userSesion.getId());
                List listaamigos = gusuarios.listaSeguimiento(userSesion.getId());
                List listasugeridos = gusuarios.sugerirUsuarios(userSesion.getId());
                String urlxslt =String.format("%s%ssources%sXmlNotificacion.xslt", hsr.getRealPath(""),File.separator,File.separator);
                List notificaciones = gestorNotificaciones.obtenerNotificaciones(user.getId(),urlxslt,10,1); 
                Map model = new HashMap();
                
                model.put("listabodegas", listabodegas);
                model.put("UsuarioSesion",userSesion);
                model.put("usuario",userSesion);
                model.put("idusuario", userSesion.getId());
                model.put("busquedavino", new BusquedaVino());
                model.put("probados",vinosprobados);
                model.put("numerobodegas",numerobodegas);
                model.put("wishlist",vinoswhishlits);
                model.put("listasolicitudes",listasolicitud);
                model.put("listaamigossugeridos",listasugeridos);
                model.put("listaamigos",listaamigos);
                model.put("listanotificaciones",notificaciones);
              
                return new ModelAndView("indexUsuario", model);
                
            
            }
            else{
            Map model = new HashMap();
           // Usuario usuenlace = gusuarios.getUsuario(user.getId());
            int vinosprobados=gusuarios.numeroCheckinsUsuario(user);
            Long numerobodegas=gusuarios.numeroBodegasUsuario(user);
            Long vinoswhishlits = gesbodegas.numeroVinosEnWishList(user.getId());
            //List listaamigos = gusuarios.listarAmigos(user.getId());
            List listaamigos = gusuarios.listaSeguimiento(user.getId());
            List listascompartidas = gesbodegas.listarListasCompartidas(user.getId());
            String esamigo=gusuarios.verificarAmistad(userSesion.getId(),user.getId());//comprobamos si este su usuario es amigo del usuario logado.
            String urlxslt =String.format("%s%ssources%sXmlNotificacion.xslt", hsr.getRealPath(""),File.separator,File.separator);
            List notificaciones = gestorNotificaciones.obtenerNotificaciones(user.getId(),urlxslt,10,1); 
            
            
            model.put("busquedavino",new BusquedaVino());
            model.put("listabodegas",listabodegas);
            model.put("UsuarioSesion",userSesion);
            model.put("Usuario", user);
            model.put("titulos", "Informacion del usuario");
            model.put("error","");
            model.put("probados",vinosprobados);
            model.put("numerobodegas",numerobodegas);
            model.put("wishlist",vinoswhishlits);
            model.put("listaamigos",listaamigos);
            model.put("listascompartidas",listascompartidas);
            model.put("esamigo",esamigo);
            model.put("listanotificaciones",notificaciones);
            return new ModelAndView("infoUsuario", model);
            }
            
            }
            catch (HibernateException e){
            
            Map model = new HashMap();
            
            listabodegas = gesbodegas.listarBodegasUsuario(Integer.parseInt(idusuario));
            model.put("busquedavino",new BusquedaVino());
            model.put("listabodegas",listabodegas);
            model.put("Usuario", new Usuario());
            model.put("titulos", "Informacion del usuario");
            model.put("error",e.getMessage());
            return new ModelAndView("infoUsuario",model);
            
                
                
            
            }
        }
        else
        {
            Map model = new HashMap();
            model.put("login",new Usuario());
            model.put("loginUsuario",new BusquedaVino());
            model.put("error","");
            return new  ModelAndView("loginUsuario",model);
            
        
        
        }
            
            

    }

   

    
}
