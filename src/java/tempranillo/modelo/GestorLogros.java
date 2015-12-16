/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.entidades.sistemanotificaciones.NotificacionDB;
import tempranillo.entidades.sistemalogros.UsuarioLogros;
import tempranillo.entidades.sistemanotificaciones.Notificacion;

/**
 * Clase privada exclusiva para la gestión de logros. 
 * @author Oscar CN
 */
public class GestorLogros extends tempranillo.utils.SessionManagement {
    
    public List obtenerListaLogrosUsuario(int idusuario) {
        iniciarTransaccion();
        Query query = GestorLogros.sesion.createQuery("SELECT l.idlogro FROM UsuarioLogros l where l.idusuario=:idusuario");
        query.setParameter("idusuario", idusuario);
        trx.commit();
        List listalogros;
        listalogros= query.list();
        cerrarSesion();
        return listalogros;
    }
    
        //metodo que comprueba si se ha conseguido el logro "Mi primer Checkin"
    public boolean comprobarMiPrimerCheckin(int idusuario) {
        //comprobamos que el usario tiene el logro de primer checkin si no lo tiene se le otorga.
        try {
                EstablecerLogro(idusuario, 2);
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        return true;
    }

    /**
     * Comprueba si se ha conseguido el logro "Mi primer Comentario".
     *
     * @param idusuario
     */
    public boolean comprobarMiPrimerComentario(int idusuario) {
        try {
                EstablecerLogro(idusuario, 4);
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        return true;
    }

    /**
     * Comprueba si se ha conseguido el logro "Mi primer Alta".
     *
     * @param idusuario
     */
    public boolean comprobarMiPrimerAlta(int idusuario) {
        try {
            
           EstablecerLogro(idusuario, 3);
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        return true;
    }
    
    boolean comprobarMiPrimeraLista(int idUsuario) {
    try {
           EstablecerLogro(idUsuario, 8);
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
    return true;
    }
    
     /**
     * Comprueba si un usuario dispone de un logro.
     *
     * @param idUsuario
     * @param idLogro
     */
    @Deprecated
    private boolean ComprobarLogroPorUsuario(int idUsuario, int idLogro) {
        iniciarTransaccion();
        Query query = sesion.createQuery("SELECT l.fecha FROM UsuarioLogros l where l.idusuario=:idusuario and l.idlogro=:idlogro");
        query.setParameter("idusuario", idUsuario);
        query.setParameter("idlogro", idLogro);
        Date fecha = (Date) query.uniqueResult();
        trx.commit();
        cerrarSesion();
        return (fecha != null);
    }
    
    /**
     * Establece en base de datos un logro para un usuario.
     *
     * @param idUsuario
     * @param idLogro
     */
    private void EstablecerLogro(int idUsuario, int idLogro) {
        try {
            GestionNotificacion gestorNotificacion = new GestionNotificacion();
            iniciarTransaccion();
            UsuarioLogros logro = new UsuarioLogros();
            logro.setFecha(new Date());
            logro.setIdusuario(idUsuario);
            logro.setIdlogro(idLogro);
            sesion.save(logro);
            trx.commit();
            cerrarSesion();
            gestorNotificacion.createNotificacion(new Notificacion(Notificacion.TipoNotificacion.LOGROCONSEGUIDO, 
                    Logro.getLogroFromBD(idLogro), 
                    (new GestionUsuarios()).getUsuarioPorId(idUsuario)));
            
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
    }

    /**
     *
     * @param idUsuario the value of idUsuario.
     * @param numeroCheckins Numero de checkins a comprobar.
     */
    boolean comprobarCheckinMultiple(int idUsuario,int numeroCheckins) {
        try {
            iniciarTransaccion();
            Query query = sesion.createSQLQuery("SELECT COUNT(public.tcheckins.idvino) AS contador FROM public.tcheckins WHERE public.tcheckins.idusuario=:idusuario");
            query.setParameter("idusuario", idUsuario);
            Object result = (Object) query.uniqueResult();
            trx.commit();
            cerrarSesion();
            
            BigInteger bigInt = (BigInteger)result; 
            if (bigInt.intValue()==numeroCheckins){
                if (numeroCheckins==100){
                   EstablecerLogro(idUsuario,5);
                }
                if (numeroCheckins==250){
                   EstablecerLogro(idUsuario,6);
                }
                if (numeroCheckins==500){
                   EstablecerLogro(idUsuario,7);
                }
                
                return true;
            }            
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }    
        return false;
    }

    boolean comprobarMiPrimerSeguidor(int idUsuario) {
       try {
           EstablecerLogro(idUsuario, 9);
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
    return true;
    }

    boolean comprobarAltaMultiple(int idUsuario, int numeroAltas) {
        try {
            iniciarTransaccion();
            Query query = sesion.createSQLQuery("SELECT COUNT(public.tvinos.idvino) AS contador FROM public.tvinos WHERE public.tvinos.idusuario=:idusuario");
            query.setParameter("idusuario", idUsuario);
            Object result = (Object) query.uniqueResult();
            trx.commit();
            cerrarSesion();
            BigInteger bigInt = (BigInteger)result; 
            if (bigInt.intValue()==numeroAltas){
                if (numeroAltas==50){
                   EstablecerLogro(idUsuario,10);
                }
                if (numeroAltas==100){
                   EstablecerLogro(idUsuario,11);
                }
                if (numeroAltas==150){
                   EstablecerLogro(idUsuario,12);
                }
                
                return true;
            }            
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }    
        return false;
    }

           
    /**
     * Este metodo comprueba si el usuario se ha convertido en Sommelier de un vino, estableciendo el logro asociado.
     * @param idUsuario
     * @param idVino
     * @return 
     */
    boolean comprobarSommeliers(int idUsuario, int idVino) {
         try {
             Integer idUsuarioTopCheckins = (new GestionCheckins()).getCheckinsLastMonth(idVino);
             if ((idUsuarioTopCheckins != null)&&(idUsuarioTopCheckins.equals(idUsuario))){
                 EstablecerLogro(idUsuario, 20);
                return true;
             }else{
                 return false;
             }            
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }    
        return false;
    }
    

}
