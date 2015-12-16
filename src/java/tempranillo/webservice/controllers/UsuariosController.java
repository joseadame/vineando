/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.webservice.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.AltaResult;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaUsuario;
import tempranillo.entidades.EstadisticasUsuario;
import tempranillo.entidades.InfoFollow;
import tempranillo.entidades.InfoLista;
import tempranillo.entidades.NuevaListaResult;
import tempranillo.entidades.ResultEstadisticas;
import tempranillo.entidades.ResultadoWS;
import tempranillo.entidades.Seguimiento;
import tempranillo.entidades.SolicitudesWS;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.entidades.sistemalogros.TipoAccion;
import tempranillo.entidades.sistemanotificaciones.Notificacion;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionEventos;
import tempranillo.modelo.GestionNotificacion;
import tempranillo.modelo.GestionUsuarios;
import tempranillo.modelo.GestorLogros;



/**
 *
 * @author Redbaron
 */

@Controller
@RequestMapping(value="/usuario")
public class UsuariosController {
    
    
    @RequestMapping(value="/alta", method= RequestMethod.PUT,  produces = "application/json")
    @ResponseBody AltaResult altaUsuario(@RequestBody Usuario usuario)
    {
        
        AltaResult result;
        GestionUsuarios gestorUsuarios = new GestionUsuarios();
        GestionBodegas gestorBodegas = new GestionBodegas();
            usuario.setRango("Aprendiz");
            usuario.setTipoperfil("privado"); //el perfil de un nuevo usuario se establece a privado por defecto.
            usuario.setPuntuacion(0);
            usuario.setRutaAvatar("images/avatar.png");
            usuario.setLastlogin(new Date());//metemos la fecha de creacion como la fecha de ultimo login.
            usuario.setFecharegistro(new Date()); //metemos la fecha de creacion del usuario.
            
            result=gestorUsuarios.altaUsuarioREST(usuario);
            
            if (result.getStatus().equalsIgnoreCase("OK"))
            {
            Bodega bodegapordefecto = new Bodega();
            bodegapordefecto.setDescripcion("Mis vinos");
            bodegapordefecto.setIdusuario(result.getUser().getId());
            bodegapordefecto.setCompartida("Si"); //la bodega por defecto es compartida.
            bodegapordefecto.setNotamedia(0.0);
            gestorBodegas.altaBodegaPorDefecto(bodegapordefecto);//damos de alta la bodega por defecto para el usuario que se acaba de registrar.
        
            }
            
            return result;
        
    
    }
    
    
    
    
    
    
    @RequestMapping(value="/{idusuario}")
    @ResponseBody Usuario get(@PathVariable int idusuario){
    
        GestionUsuarios gesusuarios = new GestionUsuarios();
        Usuario user = gesusuarios.getUsuarioPorId(idusuario);
        return user;
    
    
    
    } 
    
    @RequestMapping(value="/buscar/{nombreUsuario}")
    @ResponseBody Usuario buscarPorNombre (@PathVariable String nombreUsuario){
    
        GestionUsuarios gesusuarios = new GestionUsuarios();
        BusquedaUsuario busqueda = new BusquedaUsuario();
        busqueda.setAlias(nombreUsuario);
        Usuario user = gesusuarios.busquedaUsuario(busqueda);
        return user;
    
    
    }
    
    //Devuelve el número de vinos probados por un usuario.
    
    @RequestMapping(value="/checkins/total/{idUsuario}")
    @ResponseBody int NumeroVinosProbados (@PathVariable int idUsuario){
    
        GestionUsuarios gesusuarios = new GestionUsuarios();
        Usuario user = new Usuario();
        user.setId(idUsuario);
        return gesusuarios.numeroCheckinsUsuario(user);
        
    
    
    }
    
    //Devuelve todos los vinos y su catas que ha realizado un usuario un usuario.
    @RequestMapping(value="/checkins/{idUsuario}")
    @ResponseBody List VinosProbados (@PathVariable int idUsuario){
    
        GestionUsuarios gesusuarios = new GestionUsuarios();
       
        return gesusuarios.obtenerCatasUsuario(idUsuario);
        
    
    
    }
    
     //Obtiene la wishlist del usuario
    @RequestMapping(value="/wishlist/{idUsuario}")
    @ResponseBody List<Vino> obtenerWishListUsuario (@PathVariable int idUsuario){
    
        GestionUsuarios gesusuarios = new GestionUsuarios();
       
        return gesusuarios.obtenerWishListUsuario(idUsuario);
        
    
    
    }
    
    //Devuelve la listas de vinos de un usuario
    @RequestMapping(value="/{idUsuario}/listas")
    @ResponseBody List<Bodega> obtenerListasUsuario (@PathVariable int idUsuario){
    
        GestionBodegas geslistas = new GestionBodegas();
       
        return geslistas.listarBodegasUsuario(idUsuario);
        
    
    
    }
    
    
    
    
    
    
    
   //Devuelve los vinos de una determinada lista de un usuario
    
    @RequestMapping(value="/listas/{idLista}")
    @ResponseBody List<Vino> obtenerVinosLista (@PathVariable int idLista){
    
        GestionBodegas geslistas = new GestionBodegas();
       
        return geslistas.recuperarVinosLista(idLista);
        
    
    
    }
    
    
    
    
    //Crea una lista para el usuario
    
    @RequestMapping(value="/listas/nuevalista", method= RequestMethod.PUT,  produces = "application/json")
    @ResponseBody NuevaListaResult altaLista(@RequestBody Bodega lista){
    
           GestionBodegas gestorBodegas = new GestionBodegas();
        
           Date fechaactual = new Date();
           lista.setFecha(fechaactual);
           lista.setNotamedia(0.0);
           
           try{
           int idBodega = gestorBodegas.altaBodega(lista);
           GestionEventos ev = new GestionEventos();
           List _listaacciones = ev.comprobarEvento(TipoAccion.LISTA, lista.getIdusuario());
           
           NuevaListaResult resultado = new NuevaListaResult();
           resultado.setStatus("OK");
           resultado.setListanotificaciones(_listaacciones);
           return resultado;
           }
           catch (Exception e)
           {
           
               NuevaListaResult resultado = new NuevaListaResult();
               resultado.setStatus("Error");
               return resultado;                   
           }
    }
    
    
    /* Devuelve las estadisticas del usuario para ser mostradas en la pantalla de perfil de usuario
     * de las aplicaciones moviles.
     */    
    @RequestMapping(value="/{idUsuario}/estadisticas")
    @ResponseBody ResultEstadisticas estadisticas (@PathVariable int idUsuario){
    
        ResultEstadisticas resultado= new ResultEstadisticas();
        GestionUsuarios gesusuarios = new GestionUsuarios();
        GestionBodegas gesbodegas= new GestionBodegas();
        Usuario user = new Usuario();
        user.setId(idUsuario);
        try{
            int numerocheckins=gesusuarios.numeroCheckinsUsuario(user);
            long numerovinoswishlist=gesbodegas.numeroVinosEnWishList(idUsuario);
            BigInteger numeroLogros=gesusuarios.numeroLogrosUsuario(idUsuario);
            resultado.setStatus("OK");
            EstadisticasUsuario estadisticas = new EstadisticasUsuario();
            estadisticas.setNumeroCheckins(numerocheckins);
            estadisticas.setNumeroLogros(numeroLogros);
            estadisticas.setNumeroVinosWishList(numerovinoswishlist);
            resultado.setEstadisticas(estadisticas);
            return resultado;
        }
        catch(HibernateException e)
        {
            
            resultado.setStatus("Error");
            return resultado;
        
        
        }
    
    
    }
    
    //Devuelve la listas de vinos de un usuario
    @RequestMapping(value="/{idUsuario}/logros")
    @ResponseBody List<Logro> obtenerLogrosUsuario (@PathVariable int idUsuario){
    
        GestorLogros geslogros = new GestorLogros();
       
        try{
            List<Logro> listalogros = Logro.getLogrosUsuarioAPI(idUsuario);
            return listalogros;
        }
        catch(HibernateException e)
        {
        
            List listavacia = null;
            return listavacia;
        
        }
    
    
    }
    
    /* Servicio que añade un vino a la wishlist del usuario
     * Los parametros de la url son el id del usuario y el id del vino que hay que introducir.
     */
    
    @RequestMapping(value="/altaWishlist/{idusuario}/{idvino}", method= RequestMethod.PUT,  produces = "application/json")
     @ResponseBody ResultadoWS addVinoWishlist (@PathVariable int idusuario,@PathVariable int idvino){
    
        GestionBodegas gesbodegas = new GestionBodegas();
       
        try{
            
           if (gesbodegas.altaVinoEnWishList(idusuario, idvino)){
               ResultadoWS resultado = new ResultadoWS();
               resultado.setStatus("OK");
               resultado.setNotificaciones(new ArrayList());
               return resultado;
           
           }
           else
           {
           
               ResultadoWS resultado = new ResultadoWS();
               resultado.setStatus("ERROR");
               return resultado;
           }
                   
            
        }
        catch(HibernateException e)
        {
         
            ResultadoWS resultado = new ResultadoWS();
            resultado.setStatus("ERROR");
            resultado.setMensajeerror(e.getLocalizedMessage());
            return resultado;
        
        }
    
    
    }
    
    
    @RequestMapping(value="/borrarVinoWhislist", method= RequestMethod.PUT,  produces = "application/json")
    @ResponseBody ResultadoWS borrarVinoDeWishList(@RequestBody InfoLista info)
    {
    
     GestionBodegas   gestionbodegas = new GestionBodegas();
        
        try{
            //en este caso idLista llevara el id del usuario.
        gestionbodegas.borrarVinoWishList(info.getIdlista(),info.getIdvino());
        ResultadoWS resultado= new ResultadoWS();
        resultado.setStatus("OK");
       
        return resultado;
        }
        catch(HibernateException e)
        {
            ResultadoWS resultado = new ResultadoWS();
            resultado.setStatus("ERROR");
            resultado.setMensajeerror(e.getLocalizedMessage());
        return resultado;
        
        }
        
        
        
    
    }  
    
    
    //Nos permite enviar una solicitud de seguimiento a un usuario determinado.
    
    
    @RequestMapping(value="/followuser", method= RequestMethod.PUT,  produces = "application/json")
    @ResponseBody ResultadoWS followUser(@RequestBody InfoFollow info)
    {
    
   GestionUsuarios gestorusuarios = new GestionUsuarios();
   GestionEventos  gestorEventos = new GestionEventos();
   int esprivado=0; //partimos de que no es privado.
           
           try{
               
               Usuario useramigo= gestorusuarios.getUsuarioPorId(info.getIdusuarioFollow());
               Usuario userSesion = gestorusuarios.getUsuarioPorId(info.getIdusuario());
               if (useramigo.getTipoperfil().equals("privado"))
               {
                   if (gestorusuarios.comprobarSeguimiento(userSesion.getId(),useramigo.getId())==0){
                   gestorusuarios.enviarSolicitudAmigo(info.getIdusuario(),info.getIdusuarioFollow(), "");
                   esprivado=1;
                   }
                   else
                   {
                                 ResultadoWS resultado = new ResultadoWS();
                                 resultado.setStatus("ERROR");
                                 resultado.setMensajeResultado("Ya sigues a este usuario");
                                 return resultado;
                   
                   
                   }
               }
               else
               {
                   Seguimiento seg = new Seguimiento();
                   seg.setFecha(new Date());
                   seg.setIdamigo(info.getIdusuarioFollow());
                   seg.setIdusuario(info.getIdusuario());
                   gestorusuarios.addSeguimiento(seg);
                   gestorEventos.comprobarEvento(TipoAccion.SEGUIMIENTO, info.getIdusuario());
                   GestionNotificacion    gestorNotificacion= new GestionNotificacion();
                   gestorNotificacion.createNotificacion(
                           new Notificacion(Notificacion.TipoNotificacion.ACEPTARSEGUIMIENTO, userSesion, useramigo)
                           );
                   //TODO: gestorEventos.comprobarEvento(TipoAccion.SEGUIMIENTO,Integer.parseInt(idamigo), new MutableInteger());
               }
               
                            if (esprivado==1)
                            {

                                 ResultadoWS resultado = new ResultadoWS();
                                 resultado.setStatus("OK");
                                 resultado.setMensajeResultado("Solicitud enviada a "+useramigo.getAlias());
                                 return resultado;
                            }
                            else
                            {
                                 ResultadoWS resultado = new ResultadoWS();
                                 resultado.setStatus("OK");
                                 resultado.setMensajeResultado("Ahora sigues a "+useramigo.getAlias());
                                 return resultado;




                            }   
               
               
           }
           catch(HibernateException e)
           {
               ResultadoWS resultado = new ResultadoWS();
               resultado.setStatus("ERROR");
               resultado.setMensajeerror(e.getLocalizedMessage());
               return resultado;
           }
        
        
        
    
    }  
    
    
    /*
     * Permite recuperar las peticiones de seguimiento de un usuario determinado.
     * devolviendo un objeto con el estado de la peticion y la lista de solicitudes.  
    */
    
  
    @RequestMapping(value="/{idUsuario}/peticiones")
    @ResponseBody SolicitudesWS obtenerPeticionesSeguimiento (@PathVariable int idUsuario){
    
        GestionUsuarios gesusuario= new GestionUsuarios();
        SolicitudesWS result;
       
        try{
            List listasolicitudes = gesusuario.solicitudesAmistad(idUsuario);
            
            result= new SolicitudesWS();
            result.setStatus("OK");
            result.setListaSolicitudes(listasolicitudes);
            
            return result;
            
            
        }
        catch(HibernateException e)
        {
            result = new SolicitudesWS();
            result.setStatus("ERROR");
            result.setMensajeError(e.getLocalizedMessage());
            
            return result;
        
        }
    
    
    }
    
    
    
    
    
    
    
}
