/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.sistemalogros.TipoAccion;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionEventos;

/**
 *
 * @author joseadamefernandez
 */
public class AltaBodegaController extends AbstractCommandController{

    
    GestionBodegas gestorBodegas;
    
    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
        
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        ModelAndView _modelAndView = null;
        List bodegasusuario = new ArrayList();
        // if (userSesion != null) {
        Bodega bodega = (Bodega) o;
         List<Logro> _listaacciones=null;
        //esto habria que reemplazarlo por injeccion directa en spring.
        gestorBodegas = new GestionBodegas();
        Integer idBodega = null;
        
        try {
            bodega.setIdusuario(userSesion.getId());
            Date fechaactual = new Date();
            //SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
            //String fecha = formato.format(fechaactual);
            bodega.setFecha(fechaactual);
            bodega.setNotamedia(0.0);
            //devolvemos el id de usuario con el que se ha dado de alta.
            idBodega = gestorBodegas.altaBodega(bodega);
            GestionEventos ev = new GestionEventos();
            _listaacciones = ev.comprobarEvento(TipoAccion.LISTA, userSesion.getId());
            
            bodegasusuario = gestorBodegas.listarBodegasUsuario(userSesion.getId());
        } catch (HibernateException e) {
            //TODO: Realizar log del error.
            Map model = new HashMap();
            model.put("error", e.getMessage().toString());
            model.put("busquedavino",new BusquedaVino());
            model.put("usuario",userSesion);
            model.put("listabodegas", bodegasusuario);
            model.put("nuevaBodega", bodega);
            model.put("bodega",new Bodega());
            _modelAndView = new ModelAndView("listadobodegasusuario", model);
        } finally {
            if (idBodega != null) {
                Map model = new HashMap();
                model.put("error","");
                model.put("exito", "¡Bien, lista dada de alta con exito!");
                model.put("listabodegas", bodegasusuario);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",userSesion);
                model.put("listaacciones",_listaacciones);
                model.put("nuevaBodega",bodega);
                model.put("bodega",new Bodega());
               //TODO: model.put("puntuacion", puntuacionObtenida.get());
                _modelAndView = new ModelAndView("listadobodegasusuario", model);
            }
        }
        /*} else {
        
        //el usuarion no esta logueado vamos al login.
        _modelAndView = new ModelAndView("login");
        }*/
        return _modelAndView;
        
        
    }
    
}
