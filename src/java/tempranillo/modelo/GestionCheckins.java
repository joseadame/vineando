/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import tempranillo.entidades.Checkin;
import tempranillo.entidades.Vino;

/**
 *
 * @author Jose Adame
 */
public class GestionCheckins extends tempranillo.utils.SessionManagement {

    public int nuevoCheckin(Checkin checkin, int idvino) {
        int id = 0; //recuperaremos el id con el que se ha dado de alta el usurio
        try {
            iniciarTransaccion();
            //  Vino vino = (Vino) sesion.load(Vino.class, idvino);
            sesion.save(checkin);//guardamos el checkin en la tabla
            //  vino.addCheckin(checkin);
            trx.commit();
            return checkin.getIdcheckin();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            cerrarSesion();
        }
        return 0;
    }

    //devuelve los checkins de un vino.
    public List<Checkin> getCheckinsVino(int idvino) {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("FROM Checkin c WHERE c.idvino=:idvino");
            query.setParameter("idvino", idvino);
            //query.setParameter("cadena", vino.getCadenabusqueda());
            List checkins = query.list();
            return checkins;
        } catch (HibernateException e) {
            manejaExcepcion(e);
        } finally {
            cerrarSesion();
        }
        return null;
    }

    public List<String> buscarCiudad(String cadena) {
        try {
            iniciarTransaccion();
            String temp = "%" + cadena.toUpperCase() + "%";
            Query query = sesion.createQuery("SELECT c.ciudad FROM Checkin c WHERE UPPER(c.ciudad) like ?");

            query.setString(0, temp);
            //query.setParameter("cadena", vino.getCadenabusqueda());
            List ciudades = query.list();
            return ciudades;
        } catch (HibernateException e) {
            manejaExcepcion(e);
        } finally {
            cerrarSesion();
        }
        return null;
    }

    //calcula el numero de checkins por dia de un usuario sobre un determiando vino
    public int checkinVinoPorDia(int idvino, int idusuario, Date fecha) {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("From Checkin c where c.idvino=:idvino and c.idusuario=:idusuario and c.fecha=:fecha");
            query.setParameter("idvino", idvino);
            query.setParameter("idusuario", idusuario);
            query.setParameter("fecha", fecha);
            //query.setParameter("cadena", vino.getCadenabusqueda());
            List checkins = new ArrayList();

            checkins = query.list();

            if (checkins.isEmpty()) {
                return 0;
            } else {
                return 1;
            }
        } catch (HibernateException e) {

            manejaExcepcion(e);
        } finally {
            cerrarSesion();
        }
        return 0;
    }
    
    /**
     * Obtiene el idUsuario con más checkins del ultimo més para un vino.
     * @param idVino Identificador del vino.
     * @return idUsuario con más checkins.
     */
    public Integer getCheckinsLastMonth(int idVino)
    {
        try {
            //Consultamos el id de usuario con mayor número de checkins para un vino.                      
            iniciarTransaccion();
            Query queryNumCheckins = sesion.createSQLQuery("select tcheckins.idusuario \n" +
                                                "from public.tcheckins \n" +
                                                "where  tcheckins.idvino=:idVino and (fecha > now() - interval '1 month')\n" +
                                                "group by  tcheckins.idusuario\n" +
                                                "having (count(tcheckins.idusuario) > 5)\n" +
                                                "order by count(tcheckins.idusuario) desc limit 1;");
            queryNumCheckins.setParameter("idVino", idVino);
            Integer idUsuario = (Integer)queryNumCheckins.uniqueResult();            
            return idUsuario;
        } catch (HibernateException he) {            
            manejaExcepcion(he);
            return null;
        } finally {
            cerrarSesion();
        }        
    }
}
