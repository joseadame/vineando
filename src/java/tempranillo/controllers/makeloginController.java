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
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionNotificacion;
import tempranillo.modelo.GestionUsuarios;



/**
 *
 * @author joseadamefernandez
 */
public class makeloginController extends AbstractCommandController {
    
    private GestionUsuarios gestorUsuarios;
    private GestionBodegas gestorBodegas;
    private GestionNotificacion gestorNotificaciones;
    private String urxslt;
    
    public makeloginController(){

    setCommandClass(tempranillo.entidades.login.class);
    setCommandName("login");

}

    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
        
        
        Usuario loginuser= (Usuario)o;
        gestorUsuarios= new GestionUsuarios();
        gestorBodegas = new GestionBodegas();
        gestorNotificaciones = new GestionNotificacion();
        ModelAndView vista=null;
        Map model;
        
        //recuperamos el password del usuario de la BD
        try{
        String password = gestorUsuarios.loginUsuario(loginuser.getEmail());
        
        
        if (password.equals(loginuser.getPassword())){
            
               //Creamos la variable de sesion del usuario.
            Usuario user = gestorUsuarios.getUsuarioPorEmail(loginuser.getEmail());
            gestorUsuarios.modificarFechaLogin(user.getId());
            
            HttpSession session = hsr.getSession();
            session.setAttribute("usuarioEnSession",user);
             List<Bodega> listadobodegas=gestorBodegas.listarBodegasUsuario(user.getId());
             int probados=gestorUsuarios.numeroCheckinsUsuario(user);
             Long numerobodegas = gestorUsuarios.numeroBodegasUsuario(user);
             Long wishlist = gestorBodegas.numeroVinosEnWishList(user.getId());
             List listasolicitudes = gestorUsuarios.solicitudesAmistad(user.getId());
            // List listaamigos = gestorUsuarios.listarAmigos(user.getId());
             List listaamigos = gestorUsuarios.listaSeguimiento(user.getId());
             String urlxslt =String.format("%s%ssources%sXmlNotificacion.xslt", hsr.getRealPath(""),File.separator,File.separator);
             List notificaciones = gestorNotificaciones.obtenerNotificaciones(user.getId(),urlxslt,10,1); 
             //List listasugeridos = gestorUsuarios.listaTopUserCheckins();// elegimos como amigos sugeridos la lista de usuarios con mas checkins.
             model= new HashMap();
             model.put("listabodegas",listadobodegas);
             model.put("busquedavino", new BusquedaVino());
             model.put("UsuarioSesion",user);
             model.put("probados",probados);
             model.put("numerobodegas",numerobodegas);
             model.put("wishlist",wishlist);
             model.put("listasolicitudes",listasolicitudes);
             model.put("listaamigos",listaamigos);
             model.put("listanotificaciones",notificaciones);
           //  model.put("listaamigossugeridos",listasugeridos);
             vista=new ModelAndView("indexUsuario",model);
             return vista;
        }
        else
        {
            model= new HashMap();
            model.put("error", "El password no es correcto");
            model.put("login", loginuser);
            vista = new ModelAndView("loginUsuario",model);
            return vista;
            
        
        }
       }
        catch(HibernateException e){
        
            model = new HashMap();
            model.put("error", "Usuario no encontrado");
            model.put("login", loginuser);
            vista= new ModelAndView("loginUsuario",model);
            return vista;
        }
        

       
        }
        
        
        
        
        
        
        
        
        
        
}