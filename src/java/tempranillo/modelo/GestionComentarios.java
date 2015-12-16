/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tempranillo.BD.GestionBD;
import tempranillo.entidades.Comentario;
import tempranillo.entidades.ComentarioUsuario;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.entidades.Votacion;

/**
 *
 * @author Jose Adame
 */
public class GestionComentarios extends tempranillo.utils.SessionManagement {

    /**
     * Creación de un nuevo comentario asociado a un vino y usuario.
     * @param idvino identificador del vino.
     * @param idusuario identificador del usuario
     * @param comentario comentario del usuario
     * @return 'true' si la creación ha sido correcta, 'false' en caso contrario.
     */
    public boolean addComentarioVino(int idvino, int idusuario, String comentario) {
        iniciarTransaccion();
        try {
            Comentario comentarioaux = new Comentario();
            comentarioaux.setIdusuario(idusuario);
            comentarioaux.setIdvino(idvino);
            comentarioaux.setComentario(comentario);
            Date fechahoy = new Date();
            comentarioaux.setFecha(fechahoy);
            sesion.save(comentarioaux);//grabamos primero el comentario.
            trx.commit();
            return true;
        } catch (Exception e) {
            trx.rollback();
            return false;
        } finally {
            cerrarSesion();
        }
    }

    public List obtenerComentariosVino(int idvino) {

        iniciarTransaccion();
        Query query = sesion.createQuery("FROM Comentario c WHERE c.idvino = :Idvino ORDER BY c.fecha");
        query.setParameter("Idvino", idvino);
        List comentarios = new ArrayList();
        comentarios = query.list();
        cerrarSesion();
        return comentarios;



    }

    public List listarComentariosVino(int idvino) {


        //Vino vino = (Vino) sesion.load(Vino.class, idvino);

        Comentario comentario;
        List setaux = obtenerComentariosVino(idvino);
        Iterator<Comentario> iterador = setaux.iterator();
        List listacomentarios = new ArrayList();
        while (iterador.hasNext()) {

            comentario = iterador.next();
            iniciarTransaccion();
            Usuario user = (Usuario) sesion.load(Usuario.class, comentario.getIdusuario());

            ComentarioUsuario comentarioaux = new ComentarioUsuario();
            comentarioaux.setComentario(comentario.getComentario());
            comentarioaux.setPuntuacion(comentario.getPuntuacion());
            comentarioaux.setUsuario(user.getAlias());
            comentarioaux.setIdcomentario(comentario.getIdcomentario());
            comentarioaux.setIdusuario(comentario.getIdusuario());
            SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");

            String fechaaux = comentario.getFecha().toString();
            comentarioaux.setFecha(fechaaux);
            listacomentarios.add(comentarioaux);
            cerrarSesion();
        }


        return listacomentarios;



    }

    public boolean aumentarPuntuacion(int idcomentario, int idusuario) {


        //Comentario comentario = (Comentario) sesion.load(Comentario.class, idcomentario);

        if (!usuarioHaVotado(idcomentario, idusuario)) {
            iniciarTransaccion();
            Comentario comentario = (Comentario) sesion.load(Comentario.class, idcomentario);
            int puntosactuales = comentario.getPuntuacion();
            puntosactuales = puntosactuales + 1;
            comentario.setPuntuacion(puntosactuales);
            Votacion vot = new Votacion();

            vot.setFecha(new Date());
            vot.setIdusuario(idusuario);
            vot.setIdcomentario(idcomentario);
            vot.setTipovoto("P"); //es un voto positivo.

            sesion.save(vot);
            comentario.addVotacion(vot);




            trx.commit();
            cerrarSesion();
            return true;
        }

        return false;

    }

    public List listarComentariosMasVotados(int idvino) {



        //Vino vino = (Vino) sesion.load(Vino.class, idvino);
        Comentario comentario;
        List setaux = obtenerComentariosVino(idvino);
        Iterator<Comentario> iterador = setaux.iterator();
        List listacomentariosmasvotados = new ArrayList();
        while (iterador.hasNext()) {

            comentario = iterador.next();
            iniciarTransaccion();
            Usuario user = (Usuario) sesion.load(Usuario.class, comentario.getIdusuario());
            cerrarSesion();
            ComentarioUsuario comentarioaux = new ComentarioUsuario();

            if (comentario.getPuntuacion() > 5) {
                comentarioaux.setComentario(comentario.getComentario());
                comentarioaux.setPuntuacion(comentario.getPuntuacion());
                comentarioaux.setUsuario(user.getAlias());
                comentarioaux.setIdcomentario(comentario.getIdcomentario());
                SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");

                String fechaaux = comentario.getFecha().toString();
                comentarioaux.setFecha(fechaaux);
                listacomentariosmasvotados.add(comentarioaux);
            }

        }


        return listacomentariosmasvotados;




    }

    private boolean usuarioHaVotado(int idcomentario, int idusuario) {
        iniciarTransaccion();
        Votacion vot;
        Comentario comentario = (Comentario) sesion.load(Comentario.class, idcomentario);//sacamos el comentario

        //recorremos todas sus votaciones viendo si el usuario ya habia votado.
        Set setvotaciones = comentario.getListaVotaciones();

        Iterator<Votacion> iterador = setvotaciones.iterator();

        while (iterador.hasNext()) {
            vot = iterador.next();
            if (vot.getIdusuario() == idusuario) {
                return true;
            }


        }
        cerrarSesion();
        return false;
    }

    public List listarComentariosVinoPorFecha(int idvino) {
        Comentario comentario;
        iniciarTransaccion();
        Query query = sesion.createQuery("FROM Comentario c WHERE c.idvino = :Idvino ORDER BY c.fecha DESC");
        query.setParameter("Idvino", idvino);
        List comentarios = new ArrayList();
        comentarios = query.list();

        Iterator<Comentario> iterador = comentarios.iterator();
        List listacomentarios = new ArrayList();
        while (iterador.hasNext()) {

            comentario = iterador.next();
            Usuario user = (Usuario) sesion.load(Usuario.class, comentario.getIdusuario());//obtenemos el  usuario de cada comentario.
            ComentarioUsuario comentarioaux = new ComentarioUsuario();
            comentarioaux.setComentario(comentario.getComentario());
            comentarioaux.setPuntuacion(comentario.getPuntuacion());
            comentarioaux.setUsuario(user.getAlias());
            comentarioaux.setIdcomentario(comentario.getIdcomentario());
            comentarioaux.setIdusuario(comentario.getIdusuario());
            comentarioaux.setrutaAvatar(user.getRutaAvatar());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            //aqui le podemos asignar el size de la lista de votaciones a una variable que recoja este nuemero para
            //luego mostrarlo en la página.

            //int numerovotaciones=comentario.getListaVotaciones().size();


            //String fechaaux = comentario.getFecha().toString();

            comentarioaux.setFecha(format.format(comentario.getFecha()));
            comentarioaux.setNumerovotaciones(comentario.getListaVotaciones().size());
            listacomentarios.add(comentarioaux);
        }

        cerrarSesion();
        return listacomentarios;



    }

    public boolean votonegativo(int idcomentario, int idusuario) {

        //Comentario comentario = (Comentario) sesion.load(Comentario.class, idcomentario);

        if (!usuarioHaVotado(idcomentario, idusuario)) {
            iniciarTransaccion();
            Comentario comentario = (Comentario) sesion.load(Comentario.class, idcomentario);

            int puntosactuales = comentario.getPuntuacion();
            puntosactuales = puntosactuales - 1;
            comentario.setPuntuacion(puntosactuales);
            Votacion vot = new Votacion();

            vot.setFecha(new Date());
            vot.setIdusuario(idusuario);
            vot.setIdcomentario(idcomentario);
            vot.setTipovoto("N"); //es un voto positivo.
            sesion.save(vot);
            comentario.addVotacion(vot);




            trx.commit();
            cerrarSesion();
            return true;
        }

        return false;
    }

    public int recuperarIdUsuarioComentario(int idcomentario) {

        try {
            iniciarTransaccion();
            Comentario comentario = (Comentario) sesion.load(Comentario.class, idcomentario);
            return comentario.getIdusuario();


        } catch (HibernateException he) {
            manejaExcepcion(he);


        } finally {
            cerrarSesion();
        }
        return 0;
    }
}
