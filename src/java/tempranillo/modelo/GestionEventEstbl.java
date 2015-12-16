/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import tempranillo.entidades.Evento;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tempranillo.BD.GestionBD;

/**
 *
 * @author Jose
 *
 * Clase que implementa todo lo necesario para la gestion de los eventos creados
 * por los establecimientos.
 *
 *
 *
 *
 *
 */
public class GestionEventEstbl extends tempranillo.utils.SessionManagement {

    //Devuelve la lista de eventos cuya fecha de fin es mayor que la fecha actual.
    public List<Evento> getEventos() {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select e , t From Evento e , Establecimiento t where e.idestablecimiento=t.idestablecimiento  and e.fechafin > now()");
            List listaEventos = query.list(); //lista de eventos no finalizados
            cerrarSesion();
            return listaEventos;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;

    }

    //Devuelve la informacion de un determinado evento.
    public List getEvento(int idevento) {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select e , t From Evento e , Establecimiento t where e.idestablecimiento=t.idestablecimiento  and e.idevento=:id");
            query.setParameter("id", idevento);
            List listaEventos = query.list(); //lista de eventos no finalizados
            cerrarSesion();
            return listaEventos;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    //busca eventos en una ciudad determinada
    public List<Evento> buscarPorCiudad(String filtroCiudad) {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select e From Evento e , Establecimiento t where e.idestablecimiento=t.idestablecimiento "
                    + " and lower(e.direccion) LIKE lower(%:filtroCiudad%)");
            query.setParameter("filtroCiudad", filtroCiudad);
            List listaEventos = query.list(); //lista de eventos no finalizados
            cerrarSesion();
            return listaEventos;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }
}
