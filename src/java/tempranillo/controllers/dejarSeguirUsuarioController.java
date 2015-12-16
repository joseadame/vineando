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
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author Jose Adame
 */
public class dejarSeguirUsuarioController extends AbstractController{
    
    private GestionUsuarios gesusuarios;
    private GestionBodegas gesbodegas;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        String id=hsr.getParameter("id");
         Map model = new HashMap();
        
         HttpSession session = hsr.getSession();
         Usuario user= (Usuario)   session.getAttribute("usuarioEnSession");
        try{
           
            gesusuarios=new GestionUsuarios();
            gesbodegas = new GestionBodegas();
            
            gesusuarios.dejarSeguirUsuario(user.getId(),Integer.parseInt(id)); //dejamos de seguir al usuario.
            
            Usuario amigo=gesusuarios.getUsuarioPorId(Integer.parseInt(id));
            
            int vinosprobados=gesusuarios.numeroCheckinsUsuario(amigo);
            Long numerobodegas=gesusuarios.numeroBodegasUsuario(amigo);
            Long vinoswhishlits = gesbodegas.numeroVinosEnWishList(amigo.getId());
            //List listaamigos = gusuarios.listarAmigos(user.getId());
            List listaamigos = gesusuarios.listaSeguimiento(amigo.getId());
            List listascompartidas = gesbodegas.listarListasCompartidas(amigo.getId());
            List listabodegas = gesbodegas.listarBodegasUsuario(amigo.getId());
            String esamigo=gesusuarios.verificarAmistad(user.getId(),amigo.getId());//comprobamos si este su usuario es amigo del usuario logado.
            
            model.put("busquedavino",new BusquedaVino());
            model.put("listabodegas",listabodegas);
            model.put("UsuarioSesion",user);
            model.put("Usuario", amigo);
            model.put("titulos", "Informacion del usuario");
            model.put("error","");
            model.put("probados",vinosprobados);
            model.put("numerobodegas",numerobodegas);
            model.put("wishlist",vinoswhishlits);
            model.put("listaamigos",listaamigos);
            model.put("listascompartidas",listascompartidas);
            model.put("esamigo",esamigo);
            return new ModelAndView("infoUsuario", model);
        
        
        }
        catch(HibernateException he)
        {
            //si falla mostramos la pagina tal cual la teniamos.
            Usuario amigo=gesusuarios.getUsuarioPorId(Integer.parseInt(id));
            int vinosprobados=gesusuarios.numeroCheckinsUsuario(amigo);
            Long numerobodegas=gesusuarios.numeroBodegasUsuario(amigo);
            Long vinoswhishlits = gesbodegas.numeroVinosEnWishList(amigo.getId());
            //List listaamigos = gusuarios.listarAmigos(user.getId());
            List listaamigos = gesusuarios.listaSeguimiento(amigo.getId());
            List listascompartidas = gesbodegas.listarListasCompartidas(amigo.getId());
            List listabodegas = gesbodegas.listarBodegasUsuario(amigo.getId());
            String esamigo=gesusuarios.verificarAmistad(user.getId(),amigo.getId());//comprobamos si este su usuario es amigo del usuario logado.
            
            model.put("busquedavino",new BusquedaVino());
            model.put("listabodegas",listabodegas);
            model.put("UsuarioSesion",user);
            model.put("Usuario", amigo);
            model.put("titulos", "Informacion del usuario");
            model.put("error","");
            model.put("probados",vinosprobados);
            model.put("numerobodegas",numerobodegas);
            model.put("wishlist",vinoswhishlits);
            model.put("listaamigos",listaamigos);
            model.put("listascompartidas",listascompartidas);
            model.put("esamigo",esamigo);
            return new ModelAndView("infoUsuario", model);
            
        
        
        }
    }

    
    
    
}
