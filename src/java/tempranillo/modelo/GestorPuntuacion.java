/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import tempranillo.entidades.sistemanotificaciones.NotificacionDB;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.entidades.sistemalogros.RangoNiveles;
import tempranillo.entidades.sistemalogros.TipoAccion;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.sistemanotificaciones.Notificacion;

/**
 * Clase orientada a la gestión de la puntuación del usuario.
 * @author Oscar CN
 */
public class GestorPuntuacion extends tempranillo.utils.SessionManagement {
     //suma la puntuacion correspondiente a la accion que se ha ejecutado.
    public int sumarPuntuacion(TipoAccion accion, int idusuario) {
        try {
            iniciarTransaccion();
            //Obtenemos el usuario.
            Usuario auser = (Usuario) sesion.load(Usuario.class, idusuario);
            
            int nuevaPuntuacion = accion.getPuntuacion() + auser.getPuntuacion();
            auser.setPuntuacion(nuevaPuntuacion);
            trx.commit();
            cerrarSesion();
            establecerNivelUsuario(nuevaPuntuacion, idusuario);
            return accion.getPuntuacion();
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        return 0;
    }
    
    /**
     * Metodo que establece el Rango del Usuario en función de su puntuación.
     *
     * @param nuevaPuntuacion
     * @param idusuario
     */
    private String establecerNivelUsuario(int nuevaPuntuacion, int idusuario) {
        RangoNiveles nivel = RangoNiveles.getRango(nuevaPuntuacion);
        iniciarTransaccion();
        Usuario auser = (Usuario) sesion.load(Usuario.class, idusuario);
        String rangoactual= auser.getRango();
       // String nuevorango=nivel.name();
       
        auser.setRango(nivel.name());
        trx.commit();
        cerrarSesion();
        if (!rangoactual.equals(nivel.name()))
        {
            //como el rango ha cambiado esto se notifica.
            GestionNotificacion gestorNotificacion = new GestionNotificacion();
            gestorNotificacion.createNotificacion(
                    new Notificacion(Notificacion.TipoNotificacion.RANGOALCANZADO, auser));
            return nivel.name();
        }
        return null;
    }

    String sumarPuntuacion(List<Logro> _listaAcciones, int idUsuario) {
        try {
            iniciarTransaccion();
            //Obtenemos el usuario.
            Usuario auser = (Usuario) sesion.load(Usuario.class, idUsuario);
            
            int nuevaPuntuacion =auser.getPuntuacion();
            for (Logro l: _listaAcciones){
                 nuevaPuntuacion+=l.getPuntuacion();
             }
            auser.setPuntuacion(nuevaPuntuacion);
            trx.commit();
            cerrarSesion();
          return  establecerNivelUsuario(nuevaPuntuacion, idUsuario);           
        } catch (HibernateException e) {
            manejaExcepcion(e);
        } 
        return null;
    }
}
