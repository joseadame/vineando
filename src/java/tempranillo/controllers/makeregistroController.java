/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.Date;
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
import tempranillo.modelo.GestionUsuarios;
import tempranillo.utils.Configuracion;


/**
 * 
 * @author joseadamefernandez
 * Esta clase se encarga de controlar el registro del usuario en Tempranillo
 */
public class makeregistroController extends AbstractCommandController{

    private GestionUsuarios gestorUsuarios;
    private GestionBodegas gestorBodegas;
    private Configuracion configuracion;
    
    @Override
    protected org.springframework.web.servlet.ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
       ModelAndView _modelAndView = null;
        Integer idUsuario = null;
        Usuario usuarioFormulario = (Usuario) o;
        gestorUsuarios = new GestionUsuarios();//esto habria que reemplazarlo por injeccion directa en spring.
        gestorBodegas = new GestionBodegas();
        configuracion= new Configuracion();
        try {
            //metemos los datos por defecto para inicializar el usuari por primera vez
            usuarioFormulario.setRango("Aprendiz");
            usuarioFormulario.setTipoperfil("privado"); //el perfil de un nuevo usuario se establece a privado por defecto.
            usuarioFormulario.setPuntuacion(0);
            String url=configuracion.getURL();
            usuarioFormulario.setRutaAvatar(url+"/images/avatar.png");
            usuarioFormulario.setLastlogin(new Date());//metemos la fecha de creacion como la fecha de ultimo login.
            usuarioFormulario.setFecharegistro(new Date()); //metemos la fecha de creacion del usuario.
            
            //esto es solo para la beta en produccion hay que quitarlo.
            String[] cadenaaux=usuarioFormulario.getEmail().split("@");
            usuarioFormulario.setAlias(cadenaaux[0]);
            usuarioFormulario.setPassword("beta");
            /////////////////////////////////////////////////
            
            idUsuario = gestorUsuarios.altaUsuario(usuarioFormulario);//devolvemos el id de usuario con el que se ha dado de alta.
            //inicializamos la bodega por defecto.
            Bodega bodegapordefecto = new Bodega();
            bodegapordefecto.setDescripcion("Mis vinos");
            bodegapordefecto.setIdusuario(idUsuario);
            bodegapordefecto.setCompartida("Si"); //la bodega por defecto es compartida.
            bodegapordefecto.setNotamedia(0.0);
            gestorBodegas.altaBodegaPorDefecto(bodegapordefecto);//damos de alta la bodega por defecto para el usuario que se acaba de registrar.
            
            //idUsuario = gestorUsuarios.altaUsuario(usuarioFormulario);//devolvemos el id de usuario con el que se ha dado de alta.
        } catch (HibernateException e) {
            //TODO: Realizar log del error.
            Map model = new HashMap();
            model.put("error", e.getMessage().toString());
            model.put(getCommandName(), usuarioFormulario);
            model.put("busquedavino", new BusquedaVino());
            _modelAndView = new ModelAndView("RegistroUsuario", model);
            
        } finally {
            if (idUsuario != null) {
                Map model = new HashMap();
                List<Bodega> listabodegas=gestorBodegas.listarBodegasUsuario(idUsuario);
                List listasolicitud = gestorUsuarios.solicitudesAmistad(idUsuario);
               /* model.put("listabodegas", listabodegas);
                model.put("UsuarioSesion",usuarioFormulario);
                model.put("idusuario", idUsuario);
                model.put("busquedavino", new BusquedaVino());
                model.put("probados", 0);
                model.put("numerobodegas", 1);
                model.put("wishlist",0);
                model.put("listasolicitudes",listasolicitud);
                model.put(getCommandName(), usuarioFormulario);*/
             
                //solo par la beta
               GestionUsuarios gesUsuarios = new GestionUsuarios();
            List listadousuarios = gesUsuarios.ultimosUsuariosAlta();
            model.put("listadousuarios",listadousuarios);
            model.put("login",new Usuario());
            model.put("busquedavino",new BusquedaVino());
            model.put("mensajebeta","Si");
            model.put("error","");
            /////////////////////////////////////////
                _modelAndView = new ModelAndView("RegistroUsuario", model);
            }
        }

        //Creamos la variable de sesion del usuario.
        //se desactiva para la beta
        //HttpSession session = hsr.getSession();
        //session.setAttribute("usuarioEnSession", usuarioFormulario);

        return _modelAndView;

    }
        
        
    }
