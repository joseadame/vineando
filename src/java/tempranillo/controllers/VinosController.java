/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.io.File;
import tempranillo.entidades.sistemanotificaciones.NotificacionDB;
import tempranillo.entidades.sistemalogros.TipoAccion;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.SimpleFormController;
import tempranillo.entidades.*;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.entidades.sistemanotificaciones.Notificacion;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionComentarios;
import tempranillo.modelo.GestionEventos;
import tempranillo.modelo.GestionNotificacion;
import tempranillo.modelo.GestionVinos;
import tempranillo.utils.Configuracion;

/**
 *
 * @author Jose Adame
 */
public class VinosController extends SimpleFormController {

    private GestionVinos gestorVinos;
    private GestionBodegas gesbodegas;
    private GestionComentarios gestorComentarios;
    private GestionEventos gestorEventos;
    private GestionNotificacion gestorNotificacion;
    private Configuracion configuracion;
    

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws IOException {
        HttpSession session = request.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        ModelAndView _modelAndView = null;
        Map model = new HashMap();
        List variedades = new ArrayList();
        List<Bodega> listadobodegas = new ArrayList<Bodega>();
        List<Logro> _listaAccion= new ArrayList<Logro>();
        configuracion = new Configuracion();
        
        Vino vinoformulario = (Vino) command;
        vinoformulario.setNotamedia(0.0);
        vinoformulario.setNumerovotaciones(0);
        vinoformulario.setStatus("NoVerificado");
        vinoformulario.setRutaimagen(String.format(configuracion.getCadenaImagenVino(), configuracion.getURL(), File.separator, File.separator, File.separator));
        gestorVinos = new GestionVinos();//TODO: esto habria que reemplazarlo por injeccion directa en spring.
        gesbodegas = new GestionBodegas();
        gestorComentarios = new GestionComentarios();
        gestorEventos = new GestionEventos();
        gestorNotificacion = new GestionNotificacion();
        
        Integer idVino = null;
        List listacomentarios = null;
        List listacomentariosvotados = null;
        try {
            //metemos el usuario que ha dado de alta el vino desde la sesion.
            vinoformulario.setIdusuario(userSesion.getId());
            Date fechaactual = new Date();
            vinoformulario.setFechaalta(fechaactual);
            listadobodegas = gesbodegas.listarBodegasUsuario(userSesion.getId());
            idVino = gestorVinos.altaVino(vinoformulario);//devolvemos el id de usuario con el que se ha dado de alta.
            
            //Comprobamos el evento desencadenado, para ello informamos el tipo de evento, 
            //y el identificador del usuario.
            _listaAccion = gestorEventos.comprobarEvento(TipoAccion.ALTAVINO, userSesion.getId()); 
            gestorNotificacion.createNotificacion(
                    new Notificacion(Notificacion.TipoNotificacion.ALTAVINO, vinoformulario, userSesion));
            model.put("puntos", _listaAccion);
            
            Vino vino = gestorVinos.buscarVinoID(idVino);
            listacomentarios = gestorComentarios.listarComentariosVino(vino.getIdvino());
            listacomentariosvotados =gestorComentarios.listarComentariosMasVotados(vino.getIdvino());
        } catch (HibernateException e) {
            //TODO: Realizar log del error.
            List   listaVinosMayorPuntuacion=gestorVinos.buscarVinosMayorPuntuacion();
            List listaUltimosVinos=gestorVinos.ultimosVinosAlta();
            model.put("maximapuntuacion",listaVinosMayorPuntuacion);
            model.put("ultimosvinos",listaUltimosVinos);
            model.put("error", e.getMessage().toString());
            model.put("busquedavino",new BusquedaVino());
            variedades = gestorVinos.listarVariedadesUva();
            model.put("listavariedades", variedades);
            model.put("vino", vinoformulario);
            model.put("usuario",userSesion);
            _modelAndView = new ModelAndView("altaVino", model);
        } finally {
            if (idVino != null) {
               
                model.put("listaacciones",_listaAccion);
                model.put("titulos", "vino con exito");
                model.put("bodega",new Bodega());
                model.put("busquedavino",new BusquedaVino());
                model.put("listabodegas", listadobodegas);
                model.put("Vino", vinoformulario);
                model.put("porcentajeCompletado", gestorVinos.calcularPorcentaje(vinoformulario));
                model.put("comentario", new ComentarioUsuario());
                model.put("listacomentarios",listacomentarios);
                model.put("comentario", new ComentarioUsuario());
                model.put("listacomentariosvotados",listacomentariosvotados);
                model.put("pruebavino", new PruebaVino());
                model.put("checkin","");
                model.put("usuario",userSesion);
                model.put("reporteerror", new Reporte());
                model.put("accion", "¡Buena alta!");
                model.put("checkin", "ok");
               model.put("error","");
                _modelAndView = new ModelAndView(getSuccessView(), model);
            }
        }
        /*} else {
        
        //el usuarion no esta logueado vamos al login.
        _modelAndView = new ModelAndView("login");
        }*/
        return _modelAndView;
    }
    
    //Aqui cargamos los valores para inicializar los datos de la pagina.
    protected Map referenceData(HttpServletRequest request){
        HttpSession session = request.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
    
    List variedades = new ArrayList();
    List listaVinosMayorPuntuacion = new ArrayList();
    List listaUltimosVinos = new ArrayList();
    Map referenceData = new HashMap();
    referenceData.put("busquedavino",new BusquedaVino());
    gestorVinos= new GestionVinos();
    gesbodegas= new GestionBodegas();
   List<Bodega> listabodegas=new ArrayList();
   listabodegas=gesbodegas.listarBodegasUsuario(userSesion.getId());
   listaVinosMayorPuntuacion=gestorVinos.buscarVinosMayorPuntuacion();
   listaUltimosVinos=gestorVinos.ultimosVinosAlta();
    variedades = gestorVinos.listarVariedadesUva();
    String error="";//inicializamos el error.
    //JSONArray jsonList = JSONArray.fromObject(variedades);
    referenceData.put("listavariedades", variedades);
    referenceData.put("listabodegas",listabodegas);
    referenceData.put("error",error);
    referenceData.put("usuario",userSesion);
    referenceData.put("maximapuntuacion",listaVinosMayorPuntuacion);
    referenceData.put("ultimosvinos",listaUltimosVinos);
    return referenceData;
    
    
    
    }
}