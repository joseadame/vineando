/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import tempranillo.entidades.AltaResult;
import tempranillo.entidades.Amistad;
import tempranillo.entidades.BusquedaUsuario;
import tempranillo.entidades.Seguimiento;
import tempranillo.entidades.Usuario;

/**
 *
 * @author Jose Adame
 *
 * Esta clase se encarga de gestionar el alta y la busqueda de usuarios.
 *
 */
public class GestionUsuarios extends tempranillo.utils.SessionManagement {

    public int altaUsuario(Usuario user) {
        int id = 0; //recuperaremos el id con el que se ha dado de alta el usurio
        try {
            Usuario useraux = getUsuarioPorEmail(user.getEmail());//buscamos si existe el usuario antes de daro de alta.
            if (useraux == null) {
                iniciarTransaccion();
                sesion.save(user);//guardamos el usuario en la tabla TUsuarios
                trx.commit();
                return user.getId();
            } else {
                throw new HibernateException("El usuario ya esta dado de alta con este email");
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            cerrarSesion();
        }
        return 0;
    }

    //Servicio de alta de usuario para el webservice REST.
    public AltaResult altaUsuarioREST(Usuario user) {
        try {
            Usuario useraux = getUsuarioPorEmail(user.getEmail());//buscamos si existe el usuario antes de daro de alta.
            if (useraux == null) {
                iniciarTransaccion();
                sesion.save(user);//guardamos el usuario en la tabla TUsuarios
                trx.commit();
                cerrarSesion();
                AltaResult result = new AltaResult();
                result.setStatus("OK");
                result.setUser(user);
                return result;
            } else {
                AltaResult result = new AltaResult();
                result.setStatus("Ya hay un usuario registrado con ese email");
                return result;
            }
        } catch (HibernateException he) {
            AltaResult result = new AltaResult();
            result.setStatus("Error en la operación, intentelo mas tarde");
            return result;
        }
    }

    public List busquedaUsuarioPorAlias(String Alias) {
        Usuario usuario;
        try {
            iniciarTransaccion();
            String temp = "%" + Alias.toUpperCase() + "%";
            Query query = sesion.createQuery("FROM Usuario u WHERE UPPER(u.alias) like ?");
            query.setString(0, temp);
            query.setMaxResults(5); //limitamos a 5 los resultados.
            return query.list();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            cerrarSesion();
        }
        return null;
    }

    public Usuario busquedaUsuario(BusquedaUsuario user) {
        Usuario usuario;
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("FROM Usuario u WHERE u.alias = :Alias");
            query.setParameter("Alias", user.getAlias());
            cerrarSesion();
            return usuario = (Usuario) query.uniqueResult();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public String loginUsuario(String mail) {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("SELECT u.password FROM Usuario u WHERE u.email = :Email");
            query.setParameter("Email", mail);
            String password = (String) query.uniqueResult();
            cerrarSesion();
            if (password == null) {
                throw new HibernateException("No se encuentra el password o bien el usuario no existe");
            } else {
                return password;
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
            return null;
        }
    }

    //Servicio de busqueda de password de usuario para los servicios REST
    public String loginUsuarioREST(String mail) {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("SELECT u.password FROM Usuario u WHERE u.email = :Email");
            query.setParameter("Email", mail);
            String password = (String) query.uniqueResult();
            cerrarSesion();
            if (password == null) {
                return null;
            } else {
                return password;
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public Usuario getUsuarioPorEmail(String email) {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("FROM Usuario u WHERE u.email = :Email");
            query.setParameter("Email", email);
            Usuario user = (Usuario) query.uniqueResult();
            cerrarSesion();
            return user;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public Usuario getUsuarioPorId(int idusuario) {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("FROM Usuario u WHERE u.id = :idusuario");
            query.setParameter("idusuario", idusuario);
            Usuario user = (Usuario) query.uniqueResult();
            cerrarSesion();
            return user;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public void modificarDatosUsuario(Usuario user, int idusuario) {
        try {
            iniciarTransaccion();
            Usuario auser = (Usuario) sesion.load(Usuario.class, idusuario);
            auser.setAlias(user.getAlias());
            auser.setNombre(user.getNombre());
            auser.setApellidos(user.getApellidos());
            auser.setTipoperfil(user.getTipoperfil());
            trx.commit();
        } catch (HibernateException e) {
            manejaExcepcion(e);
        } finally {
            cerrarSesion();
        }
    }

    public void modificarAvatarUsuario(int idusuario, String rutaAvatar) {
        try {
            iniciarTransaccion();
            Usuario auser = (Usuario) sesion.load(Usuario.class, idusuario);
            auser.setRutaAvatar(rutaAvatar);
            trx.commit();
        } catch (HibernateException e) {
            manejaExcepcion(e);

        } finally {
            cerrarSesion();
        }
    }

    public void cambiarPassword(Usuario userSesion, String newpassword) {

        try {
            iniciarTransaccion();
            Usuario auser = (Usuario) sesion.load(Usuario.class, userSesion.getId());

            auser.setPassword(newpassword);


            trx.commit();
        } catch (HibernateException e) {
            manejaExcepcion(e);

        } finally {
            cerrarSesion();
        }


    }
    //calcula el numero de vinos probados por un usuario.

    public int numeroCheckinsUsuario(Usuario userSesion) {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("SELECT COUNT(*) FROM Checkin c WHERE c.idusuario = :idusuario GROUP BY c.idvino");
            query.setParameter("idusuario", userSesion.getId());
            List checkins;
            checkins = query.list();
            cerrarSesion();
            return checkins.size();
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }

        return 0;

    }

    public Long numeroBodegasUsuario(Usuario user) {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("SELECT COUNT(*) FROM Bodega b WHERE b.idusuario = :idusuario");
            query.setParameter("idusuario", user.getId());

            Long checkins = (Long) query.uniqueResult();
            cerrarSesion();
            return checkins;
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }

        return null;



    }

public void enviarSolicitudAmigo(int idusuario, int idamigo, String mensaje) {


        if (!amistadreciproca(idamigo, idusuario) && !amistadignorada(idamigo, idusuario)) {
            try {

                Amistad nuevaAmistad = new Amistad();
                nuevaAmistad.setEstado(1);
                nuevaAmistad.setIdamigo(idamigo);
                nuevaAmistad.setIdusuario(idusuario);
                Date fechahoy = new Date();

                nuevaAmistad.setFecha(fechahoy);
                nuevaAmistad.setMensaje(mensaje);
                iniciarTransaccion();

                //tenemos que comprobar si antes ha habido otra peticion por parte del otro usuario en ese caso la peticion amistad
                //queda establecida automaticamente.


                sesion.save(nuevaAmistad);



                trx.commit();
                cerrarSesion();
            } catch (HibernateException e) {
                manejaExcepcion(e);
            }
        } else {
            try {
                iniciarTransaccion();

                Query query = sesion.createQuery("Select a FROM  Amistad a where a.idusuario=:idsolicitante and a.idamigo=:idamigo");
                query.setParameter("idsolicitante", idamigo);
                query.setParameter("idamigo", idusuario);
                Amistad amistad = (Amistad) query.uniqueResult();

                //amistad.setEstado(2);
                sesion.delete(amistad);
                trx.commit();
                cerrarSesion();
                Seguimiento seg = new Seguimiento();
                seg.setFecha(new Date());
                seg.setIdusuario(idusuario);
                seg.setIdamigo(idamigo);
                addSeguimiento(seg);

                Seguimiento seg2 = new Seguimiento();
                seg2.setIdamigo(idusuario);
                seg2.setIdusuario(idamigo);
                seg2.setFecha(new Date());
                addSeguimiento(seg2);


            } catch (HibernateException e) {
                manejaExcepcion(e);

            }

        }
    
   
}
    


    public boolean amistadreciproca(int idamigo, int idusuario) {
        iniciarTransaccion();

        Query query = sesion.createQuery("Select a FROM  Amistad a where a.idusuario=:idsolicitante and a.idamigo=:idamigo and a.estado='1'");
        query.setParameter("idsolicitante", idamigo);
        query.setParameter("idamigo", idusuario);
        Amistad amistad = (Amistad) query.uniqueResult();
        cerrarSesion();
        if (amistad == null) {
            return false;
        } else {
            return true;
        }



    }

// Devuelve el las solicitudes de amistad de un usuario.
    public List solicitudesAmistad(int id) {

        try {
            List listasolicitudes;
            iniciarTransaccion();
            Query query = sesion.createQuery("Select u,a.mensaje FROM Usuario u, Amistad a where u.id=a.idusuario and a.idamigo=:idusuario and a.estado='1'");
            query.setParameter("idusuario", id);

            listasolicitudes = query.list();
            cerrarSesion();
            return listasolicitudes;

        } catch (HibernateException e) {

            manejaExcepcion(e);

        }
        return null;

    }

    public List listaSeguimiento(int id) {

        try {
            List listaseguimiento;
            iniciarTransaccion();
            Query query = sesion.createQuery("Select u FROM Usuario u, Seguimiento a where u.id=a.idamigo and a.idusuario=:idusuario ");
            query.setParameter("idusuario", id);

            listaseguimiento = query.list();
            cerrarSesion();
            return listaseguimiento;

        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

        return null;

    }

    public List listarAmigos(int id) {

        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select u FROM Usuario u, Amistad a where u.id=a.idusuario and a.idamigo=:idusuario and a.estado='2'");
            query.setParameter("idusuario", id);
            List aux1 = query.list();
            Query query2 = sesion.createQuery("Select u FROM Usuario u, Amistad a where u.id=a.idamigo and a.idusuario=:idusuario and a.estado='2'");
            query2.setParameter("idusuario", id);
            List aux2 = query2.list();
            cerrarSesion();
            List amigos = new ArrayList();
            Usuario usu;
            //juntamos las dos listas en una sola de amigos

            Iterator<Usuario> iterador = aux1.iterator();

            while (iterador.hasNext()) {
                usu = iterador.next();
                amigos.add(usu);



            }

            iterador = aux2.iterator();
            while (iterador.hasNext()) {

                usu = iterador.next();
                amigos.add(usu);



            }

            return amigos;

        } catch (HibernateException e) {

            manejaExcepcion(e);

        }
        return null;




    }
    
    
    //comprueba si ya existe el seguimiento, para no poder lanzarlos otra vez.
    
    public int comprobarSeguimiento(int idusuario, int idamigo){
    
    try{
        iniciarTransaccion();
        Query query = sesion.createSQLQuery("select count(*) from tseguimiento where idusuario:=usuario and idamigo:=amigo");
         query.setParameter("usuario", idusuario);
         query.setParameter("amigo", idamigo);
         int encontrado=query.executeUpdate();
         cerrarSesion();
         return encontrado;
       }
    catch(HibernateException e)
    {
       manejaExcepcion(e);
    }
   
    
    return 0;
    
    }
    
    
    

    public void aceptarSolicitudAmistad(int idsolicitante, int idamigo) {

        try {

            iniciarTransaccion();
            Query query = sesion.createQuery("Select a FROM  Amistad a where a.idusuario=:idsolicitante and a.idamigo=:idamigo");
            query.setParameter("idsolicitante", idsolicitante);
            query.setParameter("idamigo", idamigo);
            Amistad amistad = (Amistad) query.uniqueResult();

            sesion.delete(amistad);

            Seguimiento seg = new Seguimiento();
            seg.setFecha(new Date());
            seg.setIdamigo(idamigo);
            seg.setIdusuario(idsolicitante);
            sesion.save(seg);
            trx.commit();

            cerrarSesion();



        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
    }

    public void ignorarSolicitudAmistad(int idsolicitante, int idamigo) {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select a FROM  Amistad a where a.idusuario=:idsolicitante and a.idamigo=:idamigo");
            query.setParameter("idsolicitante", idsolicitante);
            query.setParameter("idamigo", idamigo);
            Amistad amistad = (Amistad) query.uniqueResult();
            sesion.delete(amistad);
           // amistad.setEstado(3);

            trx.commit();
            cerrarSesion();
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
    }

    private boolean amistadignorada(int idamigo, int idusuario) {
        iniciarTransaccion();

        Query query = sesion.createQuery("Select a FROM  Amistad a where a.idusuario=:idsolicitante and a.idamigo=:idamigo and a.estado='3'");
        query.setParameter("idsolicitante", idamigo);
        query.setParameter("idamigo", idusuario);
        Amistad amistad = (Amistad) query.uniqueResult();
        cerrarSesion();
        if (amistad == null) {
            return false;
        } else {
            return true;
        }

    }

    public List obtenerCatasUsuario(int idusuario) {
        List listacatasUsuario;
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select v,c FROM Vino v, Checkin c where v.idvino=c.idvino  and c.idusuario=:idusuario  order by c.fecha DESC");
            query.setParameter("idusuario", idusuario);


            listacatasUsuario = query.list();
            cerrarSesion();
            return listacatasUsuario;

        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        return null;

    }

    public List obtenerWishListUsuario(int idusuario) {
        List wishlist;
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select v FROM Vino v, WishList w where v.idvino=w.idvino  and w.idusuario=:idusuario");
            query.setParameter("idusuario", idusuario);


            wishlist = query.list();
            cerrarSesion();
            return wishlist;

        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        return null;
    }

    public void modificarFechaLogin(int id) {

        try {
            iniciarTransaccion();
            Usuario auser = (Usuario) sesion.load(Usuario.class, id);
            auser.setLastlogin(new Date());
            trx.commit();
            cerrarSesion();
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
    }

    public void addSeguimiento(Seguimiento seg) {
        try {
            iniciarTransaccion();
            Usuario user = (Usuario) sesion.load(Usuario.class, seg.getIdusuario());
            sesion.save(seg);
            trx.commit();
            cerrarSesion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
    }

    public String verificarAmistad(int idusuario, int id) {
        try {

            iniciarTransaccion();
            Query query = sesion.createQuery("From Seguimiento a where a.idusuario=:iduser and a.idamigo=:idamigo");
            query.setParameter("idamigo", id);
            query.setParameter("iduser", idusuario);
            Seguimiento user = (Seguimiento) query.uniqueResult();
            cerrarSesion();
            if (user == null) {
                return "noamigo";
            } else {
                return "esamigo";
            }

        } catch (HibernateException he) {
            manejaExcepcion(he);
        }


        return null;

    }

    public void dejarSeguirUsuario(int id, int idamigo) {

        try {

            iniciarTransaccion();
            Query query = sesion.createQuery("From Seguimiento a where a.idusuario=:iduser and a.idamigo=:idamigo");
            query.setParameter("iduser", id);
            query.setParameter("idamigo", idamigo);
            Seguimiento seg = (Seguimiento) query.uniqueResult();
            sesion.delete(seg);
            trx.commit();
            cerrarSesion();

        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

    }

    public List listaTopUserCheckins() {

        try {

            iniciarTransaccion();
            Query query = sesion.createSQLQuery("SELECT COUNT(public.tcheckins.idvino) AS contador,  public.tusuarios.idusuario,public.tusuarios.alias,public.tusuarios.rango,public.tusuarios.rutaavatar FROM public.tcheckins INNER JOIN public.tusuarios ON (public.tcheckins.idusuario = public.tusuarios.idusuario) GROUP BY public.tusuarios.idusuario,public.tusuarios.password,public.tusuarios.alias,public.tusuarios.email,public.tusuarios.nombre,public.tusuarios.apellidos,public.tusuarios.puntuacion,public.tusuarios.rango,public.tusuarios.rutaavatar ORDER BY contador DESC LIMIT 5");
            List lista = query.list();
            trx.commit();
            cerrarSesion();
            return lista;


        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    /* Método que lista los ultimos usuarios dados de alta.
     *
     */
    public List<Usuario> ultimosUsuariosAlta() {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("From Usuario u order by u.fecharegistro DESC");
            query.setMaxResults(5);
            List listavinos = query.list(); //lista ultimos usuarios dados de alta.
            cerrarSesion();
            return listavinos;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public List<Usuario> obtenerTopPuntuacionUsuarios() {
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("From Usuario u order by u.puntuacion DESC");
            query.setMaxResults(5);
            List listausuarios = query.list();
            cerrarSesion();
            return listausuarios;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public List sugerirUsuarios(int idusuario) {
        try {
            iniciarTransaccion();
            // Query query = sesion.createSQLQuery("SELECT COUNT(public.tcheckins.idvino) AS contador,  public.tusuarios.idusuario,public.tusuarios.alias,public.tusuarios.rango,public.tusuarios.rutaavatar FROM public.tcheckins INNER JOIN public.tusuarios ON (public.tcheckins.idusuario = public.tusuarios.idusuario) GROUP BY public.tusuarios.idusuario,public.tusuarios.password,public.tusuarios.alias,public.tusuarios.email,public.tusuarios.nombre,public.tusuarios.apellidos,public.tusuarios.puntuacion,public.tusuarios.rango,public.tusuarios.rutaavatar ORDER BY contador DESC LIMIT 5");
            Query query = sesion.createSQLQuery("SELECT tusuarios.alias, tusuarios.idusuario,tusuarios.rutaavatar, COUNT (tcheckins.idvino) AS contador FROM public.tcheckins, public.tusuarios WHERE tusuarios.idusuario = tcheckins.idusuario AND tusuarios.idusuario !=:idusuario GROUP BY tusuarios.alias,tusuarios.idusuario ORDER BY contador DESC;");
            query.setParameter("idusuario", idusuario);
            List lista = query.list();
            trx.commit();
            cerrarSesion();
            int size = lista.size();
            return lista;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }


    /*Devuelve el numero de logros de un usuario.
     *
     */
    public BigInteger numeroLogrosUsuario(int idusuario) {
        try {
            iniciarTransaccion();
            Query query = sesion.createSQLQuery("SELECT  count(*) FROM public.tusuarioslogros WHERE tusuarioslogros.idusuario =:idusuario ;");
            query.setParameter("idusuario", idusuario);
            BigInteger numeroLogros = (BigInteger) query.uniqueResult();
            cerrarSesion();
            return numeroLogros;
        } catch (HibernateException e) {
            return null;
        }
    } 
    
    public Usuario getSommelier(int idVino) {
        try {            
            //Consultamos el id de usuario con mayor número de checkins para un vino.                      
            iniciarTransaccion();
            Query queryIdUsuario = sesion.createSQLQuery("select tcheckins.idusuario from public.tcheckins  group by tcheckins.idusuario order by tcheckins.idusuario limit 1;");
           // queryIdUsuario.setParameter("idvino", String.valueOf(idVino));            
            Object result  =  queryIdUsuario.uniqueResult();
            Integer idUsuario = 0;
            if (result != null)
            {
                idUsuario = (Integer)result;
                //Una vez tenemos el id de Usuario obtenemos la entidad completa.
                Query query = sesion.createQuery("select u FROM Usuario u where u.id = :idUsuario");
                query.setParameter("idUsuario", idUsuario);
                query.setMaxResults(1);
                return (Usuario) query.uniqueResult();
            }
            else 
                return null;
        } catch (HibernateException he) {            
            manejaExcepcion(he);
        } finally {
            cerrarSesion();
        }
        return null;
    }
}
