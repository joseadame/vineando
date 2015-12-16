/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.webservice.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Checkin;
import tempranillo.entidades.Comentario;
import tempranillo.entidades.ComentarioWS;
import tempranillo.entidades.ResultadoWS;
import tempranillo.entidades.Vino;
import tempranillo.entidades.sistemalogros.TipoAccion;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionCheckins;
import tempranillo.modelo.GestionComentarios;
import tempranillo.modelo.GestionEventos;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author Oscar
 */
@Controller
@RequestMapping(value = "/vino")
public class vinoscontroller {

    @RequestMapping(value = "/{idVino}", produces = "application/json")
    public @ResponseBody
    Vino get(@PathVariable int idVino) {
        GestionVinos gestorVinos = new GestionVinos();
        Vino v = gestorVinos.buscarVinoID(idVino);
        return v;
    }

    @RequestMapping(value = "/buscar/{nombreVino}", produces = "application/json")
    public @ResponseBody
    List<Vino> get(@PathVariable String nombreVino) {
        GestionVinos gestorVinos = new GestionVinos();
        nombreVino=nombreVino.replace("+", " ");
        BusquedaVino bv = new BusquedaVino();
        bv.setCadenabusqueda(nombreVino);
        List<Vino> listaVino = gestorVinos.buscarVino(bv);
        return listaVino;
    }

    @RequestMapping(value = "/wishlist/total/{idVino}", produces = "application/json")
    public @ResponseBody
    long obtenerNumeroAparicionesWishList(@PathVariable int idVino) {
        long numero = 0;
        GestionBodegas gestorBodegas = new GestionBodegas();
        numero = gestorBodegas.numeroVecesEnWishlist(idVino);
        return numero;
    }

    /**
     * Obtiene los vinos con más checkins de la red social.
     * @param numVinos Número de vinos a obtener.
     * @return Lista de vinos con más checkins.
     */
    @RequestMapping(value = "/topvinos/{numVinos}", method= RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Vino> obtenerTopCheckinsVinos(@PathVariable int numVinos) {

        GestionVinos gestorvinos = new GestionVinos();
        return gestorvinos.listaTopVinosCheckins(numVinos);
    }


    /**
     * Creación de un nuevo comentario de un vino asociado a un usuario.
     *
     * @param idVino Identificador del vino
     * @param idusuario Identificador del usuario
     * @param comentario Comentario
     * @return 'true' si la creación ha sido correcta, 'false' en caso
     * Devuelve la lista de notificaciones asociadas al evento.
     * contrario.
     */
    @RequestMapping(value = "/comentarios/{idVino}", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody
    ResultadoWS crearComentario(@PathVariable int idVino, @RequestBody ComentarioWS comentario) {
        
        //toDo hay que devolver uan estructura con el resultado de añadir el comentario.
        ResultadoWS resultado;
        GestionComentarios gestorComentarios = new GestionComentarios();
        if (gestorComentarios.addComentarioVino(idVino, comentario.getIdusuario(), comentario.getComentario())){
           
            GestionEventos ev = new GestionEventos();
            resultado= new ResultadoWS();
            resultado.setNotificaciones(ev.comprobarEvento(TipoAccion.COMENTARIOVINO, comentario.getIdusuario()));
            resultado.setStatus("OK");
            return resultado;
        
        }else
            
        {
            resultado= new ResultadoWS();
            resultado.setStatus("ERROR");
            resultado.setMensajeerror("Ha habido un fallo al enviar el comentario");
            
            return resultado;
        
        }
        
        
        
    }
    
    @RequestMapping(value = "/comentarios/{idVino}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Comentario> obtenerComentarios(@PathVariable int idVino) {
        GestionComentarios gestorComentarios = new GestionComentarios();
        List<Comentario> listacomentarios = gestorComentarios.listarComentariosVinoPorFecha(idVino);
        return listacomentarios;
    }
    
    
    @RequestMapping(value = "/cata/{idVino}/{idusuario}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String obtenerFechaUltimaCata(@PathVariable int idVino,@PathVariable int idusuario) {
        
        GestionVinos gestorVinos = new GestionVinos();
        String fecha = gestorVinos.fechaUltimaCata(idusuario, idVino);
        String comentario=null;
        
        if (fecha.equals("")){
            comentario = "Nunca ha catado este vino, animate y cuentanos que te parece";
        
        }
        else
        {
            comentario="Lo cataste por ultima vez "+fecha;
        
        }
        
        
        return comentario;
    
    
    }
    
    
    

}
