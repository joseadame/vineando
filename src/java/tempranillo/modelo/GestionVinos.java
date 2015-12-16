/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

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
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Checkin;
import tempranillo.entidades.Media;
import tempranillo.entidades.Variedad;
import tempranillo.entidades.Vino;

/**
 *
 * @author Jose Adame
 *
 * Esta clase es la encargada de realizar toda la gestion correspondiente a los
 * vinos.
 */
public class GestionVinos extends tempranillo.utils.SessionManagement {

    public int altaVino(Vino vino) {
        try {
            iniciarTransaccion();
            sesion.save(vino);//guardamos el usuario en la tabla TUsuarios
            trx.commit();
            return vino.getIdvino();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
        return 0;

    }

    //Recupera la fecha de la ultima cata que ha realizado un usuario sobre un vino.
    //Añadido el 29-12-2012
    public String fechaUltimaCata(int idusuario, int idvino) {



        try {
            iniciarTransaccion();

            Query query = sesion.createSQLQuery("SELECT  public.tcheckins.fecha FROM  public.tcheckins WHERE public.tcheckins.idusuario =:idusuario AND  public.tcheckins.idvino =:idvino order by public.tcheckins.fecha DESC limit 1");
            query.setParameter("idusuario", idusuario);
            query.setParameter("idvino", idvino);
            //query.setParameter("cadena", vino.getCadenabusqueda());
            String fechafinal = "";
            Date fecha = (Date) query.uniqueResult();
            //formateamos la fecha correctamente
            if (fecha != null) {
                SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/yyyy");
                fechafinal = formatofecha.format(fecha);
            }
            cerrarSesion();
            return fechafinal;

        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        return null;






    }

    public List<Vino> buscarVino(BusquedaVino vino) {
        List<Vino> encontrado = null;
        try {
            iniciarTransaccion();
            String temp = "%" + vino.getCadenabusqueda().toUpperCase() + "%";
            Query query = sesion.createQuery("FROM Vino v WHERE UPPER(v.nombre) like ? OR v.barcode=?");
            query.setString(0, temp);
            query.setString(1, vino.getCadenabusqueda());
            //query.setParameter("cadena", vino.getCadenabusqueda());

            encontrado = query.list();
            cerrarSesion();
            return encontrado;

        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        return null;
    }

    public Vino buscarVinoID(int id) {
        Vino encontrado;
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("FROM Vino v WHERE v.idvino=:idvino");
            query.setParameter("idvino", id);
            encontrado = (Vino) query.uniqueResult();
            cerrarSesion();
            return encontrado;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public List<Variedad> listarVariedadesUva() {
        List<Variedad> listaVariedades;
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("FROM Variedad"); //devolvemos toda la lista de variedades de uva.
            listaVariedades = query.list();
            cerrarSesion();
            return listaVariedades;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    /**
     * Función para el calculo del porcentaje completado del perfil.
     *
     * @param vino
     * @return short (0 - 100)
     *
     * Tabla de valores Campos	Obligatorio	Valor usuario	x nombre	x tipovino	x
     * 25 anio	x variedad	20 zona	20 pais	15 barcode	20 100
     *
     */
    public Short calcularPorcentaje(Vino vino) {

        short _porcentaje = 20;
        if ((vino.getVariedad() != null) && (!vino.getVariedad().isEmpty())) {
            _porcentaje = (short) (_porcentaje + 20);
        }

        if ((vino.getZona() != null) && (!vino.getZona().isEmpty())) {
            _porcentaje = (short) (_porcentaje + 20);
        }


        if ((vino.getPais() != null) && (!vino.getPais().isEmpty())) {
            _porcentaje = (short) (_porcentaje + 20);
        }

        if ((vino.getBarcode() != null) && (!vino.getBarcode().isEmpty())) {
            _porcentaje = (short) (_porcentaje + 20);
        }

        return _porcentaje;
    }

    public void actualizarDatosVino(String uvas, String zona, String pais, String barcode, int idvino, String dato) {
        iniciarTransaccion();
        Vino aVino = (Vino) sesion.load(Vino.class, idvino);
        aVino.setVariedad(uvas);
        aVino.setZona(zona);
        aVino.setPais(pais);
        aVino.setBarcode(barcode);
        trx.commit();
        cerrarSesion();
    }

    public void calcularPuntacion(int idvino) {
        Checkin ck;

        // Vino aVino = (Vino) sesion.load(Vino.class,idvino);

        GestionCheckins gesCheckins = new GestionCheckins();
        List<Checkin> listacheckins = gesCheckins.getCheckinsVino(idvino);

        Iterator<Checkin> iterador = listacheckins.iterator();
        double sumapuntos = 0;
        int votaciones = 0;
        while (iterador.hasNext()) {
            ck = iterador.next();
            sumapuntos = sumapuntos + ck.getNota();
            if (ck.getNota() != 0) {
                votaciones++;
            }

        }

        double notamedia = sumapuntos / votaciones;
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.0", simbolo);
        String x = formateador.format(notamedia);
        iniciarTransaccion();
        Vino aVino = (Vino) sesion.load(Vino.class, idvino);
        aVino.setNotamedia(Double.parseDouble(x));
        aVino.setNumerovotaciones(votaciones);


        trx.commit();
        cerrarSesion();


    }

    //calcula el numero de veces que se ha probado un vino.
    public int numeroCheckins(int idvino) {
        //iniciarTransaccion();

        // Vino aVino = (Vino) sesion.load(Vino.class,idvino);
        GestionCheckins gesCheckins = new GestionCheckins();
        List numeroCheckins = gesCheckins.getCheckinsVino(idvino);
        int numero = numeroCheckins.size();
        //cerrarSesion();
        return numero;





    }

    public Long numeroAparicionesenListas(int idvino) {        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("SELECT COUNT(*) FROM AlmacenVinos a WHERE a.claveprimaria.idvino = :idvino");
            query.setParameter("idvino", idvino);
            Long almacenado;
            almacenado = (Long) query.uniqueResult();
            cerrarSesion();
            return almacenado;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
   }

    public List listarUsuariosHanHechoCheckins(int idvino) {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select  distinct  u From Usuario u,Checkin c where u.id=c.idusuario and c.idvino=:idvino");
            query.setParameter("idvino", idvino);
            List listausuarios; //lista de usuarios que han hecho checkin.
            listausuarios = query.list();
            cerrarSesion();
            return listausuarios;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;




    }

    public List listaZonasVino() {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select  distinct  v.zona,count(v.zona) From Vino v where v.zona<> '' group by v.zona");
            List listazonas = query.list(); //lista de zonas del vino.
            cerrarSesion();
            return listazonas;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;



    }

    public List listaTiposVino() {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select  distinct  v.tipovino,count(v.tipovino) From Vino v group by v.tipovino");

            List listatipos = query.list(); //lista de zonas del vino.
            cerrarSesion();
            for (int i = 0; i < listatipos.size(); i++) {

                if (listatipos.get(i).equals("")) {
                    listatipos.remove(i);
                }
            }



            return listatipos;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;




    }

    public List<Vino> buscarVinoPorZona(String zona) {

        try {
            iniciarTransaccion();
            String temp = "%" + zona.toUpperCase() + "%";
            Query query = sesion.createQuery("From Vino v where UPPER(v.zona) like ?");
            query.setString(0, temp);
            List listavinos = query.list(); //lista de zonas del vino
            cerrarSesion();
            return listavinos;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;



    }

    public List<Vino> buscarVinoPorTipo(String tipo) {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("From Vino v where v.tipovino=:tipo");
            query.setParameter("tipo", tipo);
            List listavinos = query.list(); //lista de zonas del vino
            cerrarSesion();
            return listavinos;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public List<Vino> buscarVinosMayorPuntuacion() {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("From Vino v order by v.notamedia DESC");
            query.setMaxResults(5);
            List listavinos = query.list(); //lista de zonas del vino
            cerrarSesion();
            return listavinos;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;



    }

    public List<Vino> ultimosVinosAlta() {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("From Vino v order by v.fechaalta DESC");
            query.setMaxResults(5);
            List listavinos = query.list(); //lista de zonas del vino
            cerrarSesion();
            return listavinos;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;




    }

    //devuele los 5 vinos con mas checkins de la red social.
    /**
     * Devuelve los vinos com más checkins de la red social.
     * @param numVinos Numero de vinos a obtener.
     * @return Lista de vinos con más checkins de la red social.
     */
    public List listaTopVinosCheckins(int numVinos) {
        numVinos = (numVinos>0)?numVinos:5;        
        try {
            iniciarTransaccion();
            Query query = sesion.createSQLQuery("SELECT COUNT(public.tcheckins.idvino) AS contador,public.tcheckins.idvino,public.tvinos.nombre,public.tvinos.tipovino,public.tvinos.anio AS fecha,public.tvinos.variedad,public.tvinos.zona,public.tvinos.pais,public.tvinos.barcode,public.tvinos.idusuario,public.tvinos.notamedia,public.tvinos.numerovotaciones,public.tvinos.fechaalta,public.tvinos.rutaimagen FROM public.tcheckins INNER JOIN public.tvinos ON (public.tcheckins.idvino = public.tvinos.idvino) GROUP BY public.tcheckins.idvino,public.tvinos.nombre,public.tvinos.tipovino,public.tvinos.anio,public.tvinos.variedad,public.tvinos.zona,public.tvinos.pais,public.tvinos.barcode,public.tvinos.idusuario,public.tvinos.notamedia,public.tvinos.numerovotaciones,public.tvinos.fechaalta,public.tvinos.rutaimagen ORDER BY contador DESC LIMIT " + Integer.toString(numVinos));

            List lista = query.list(); //lista de zonas del vino
            cerrarSesion();
            return lista;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public List<Media> listaMedia(int idVino) {
        try {

            Query query = sesion.createQuery("From Media m order by m.idmedia DESC");
            query.setMaxResults(5);
            List listMedia = query.list(); //lista de zonas del vino
            cerrarSesion();

            return listMedia;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }
}
