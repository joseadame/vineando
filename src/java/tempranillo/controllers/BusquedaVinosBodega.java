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
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author joseadamefernandez
 */
public class BusquedaVinosBodega extends AbstractController{
    
    private GestionBodegas gesbodegas;
    private GestionUsuarios gesusuarios;
    private ModelAndView vista;
    
    

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
     
        String idbodega=hsr.getParameter("idbodega");
        String idusuario=hsr.getParameter("idusuario");
        gesbodegas=new GestionBodegas();
        gesusuarios= new GestionUsuarios();
        Usuario usuarioenlazado;
        
        try{
           
           
           //List<Vino> listavinos = gesbodegas.listarVinosBodega(Integer.parseInt(idbodega));
           List<Vino> listavinos = gesbodegas.recuperarVinosLista(Integer.parseInt(idbodega));
           HttpSession session = hsr.getSession();
           Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
           
          
           Bodega bodega = gesbodegas.buscarBodega(Integer.parseInt(idbodega));
           List<Bodega> listabodegas = gesbodegas.listarBodegasUsuario(userSesion.getId());     
           Map model = new HashMap();
           //habra que meter tambien la busqueda de vino.
          
           model.put("listadovinos",listavinos);
           model.put("busquedavino",new BusquedaVino());
           
           //comparamos si el usuario sobre el que queremos consultar la bodega es el mismo que el logado.
            if (userSesion.getId()!=Integer.parseInt(idusuario))
           {
               usuarioenlazado = new Usuario();
               
               usuarioenlazado= gesusuarios.getUsuarioPorId(Integer.parseInt(idusuario));
               model.put("aliasusuario",usuarioenlazado.getAlias());
               model.put("idusuario",usuarioenlazado.getId());
           }
            else{
                model.put("aliasusuario",userSesion.getAlias());
                model.put("idusuario",userSesion.getId());
                
            }
           
           model.put("usuario",userSesion);
           model.put("listadobodegas",listabodegas);
           model.put("bodega",bodega);
           model.put("exito","");
           model.put("error","");
           
           vista = new ModelAndView("bodegaUsuario", model);
           return vista;
        
        
        
        }
        catch (HibernateException e)
        {
        
            Map model = new HashMap();
            List<Vino> listavinos = gesbodegas.listarVinosBodega(Integer.parseInt(idbodega));
           
           HttpSession session = hsr.getSession();
           Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
           List<Bodega> listabodegas = gesbodegas.listarBodegasUsuario(userSesion.getId());
            model.put("error", e.getMessage().toString());
            model.put("exito","");
            model.put("listadovinos",listavinos);
          // model.put("PorcentajePerfil", porcentajePerfil);
            model.put("busquedavino",new BusquedaVino());
            model.put("usuario",userSesion);
            model.put("listadobodegas",listabodegas);
            vista = new ModelAndView("listadobodegas", model);
            return vista;
        
        
        
        }
        
        
        
        
        
    }
    
    
    
    
    
}
