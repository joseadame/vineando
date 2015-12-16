/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import tempranillo.entidades.sistemanotificaciones.Notificacion;
import tempranillo.entidades.sistemanotificaciones.NotificacionDB;
import tempranillo.entidades.Usuario;
import java.io.*;
import java.nio.charset.Charset;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

/**
 *
 * @author Oscar CN
 */
public class GestionNotificacion extends tempranillo.utils.SessionManagement {

    public int createNotificacion(Notificacion notificacion) {
        NotificacionDB notificaciondb = null;
        //  Logger.getLogger(Notificacion.class.getName()).log(Level.SEVERE, null, notificacion.SerializeToXml());

        switch (notificacion.getTipoNotificacion()) {
            case ALTAVINO:
                notificaciondb = new NotificacionDB(notificacion.getUsuarioOriginal().getId(),
                        new Date(),
                        String.format("%s ha dado de Alta el Vino %s %s.",
                        notificacion.getUsuarioOriginal().getAlias(),
                        notificacion.getVino().getNombre(),
                        notificacion.getVino().getAnio()),
                        notificacion.SerializeToXml());
                break;
            case ACEPTARSEGUIMIENTO:
                notificaciondb = new NotificacionDB(notificacion.getUsuarioOriginal().getId(),
                        new Date(),
                        String.format("%s ha comenzado a seguir a %s.",
                        notificacion.getUsuarioOriginal().getAlias(),
                        notificacion.getUsuarioDestino().getAlias()),
                        notificacion.SerializeToXml());
                break;

            case CHECKIN:

                notificaciondb = new NotificacionDB(notificacion.getUsuarioOriginal().getId(),
                        new Date(),
                        String.format("%s ha catado el Vino %s %s.",
                        notificacion.getUsuarioOriginal().getAlias(),
                        notificacion.getVino().getNombre(),
                        notificacion.getVino().getAnio()),
                        notificacion.SerializeToXml());

                break;
            case LOGROCONSEGUIDO:
                notificaciondb = new NotificacionDB(notificacion.getUsuarioOriginal().getId(),
                        new Date(),
                        String.format("%s ha conseguido el Logro %s.",
                        notificacion.getUsuarioOriginal().getAlias(),
                        notificacion.getLogro().getNombre()),
                        notificacion.SerializeToXml());
                break;
            case RANGOALCANZADO:
                notificaciondb = new NotificacionDB(notificacion.getUsuarioOriginal().getId(),
                        new Date(),
                        String.format("%s ha alcanzado el Rango %s.",
                        notificacion.getUsuarioOriginal().getAlias(),
                        notificacion.getUsuarioOriginal().getRango()),
                        notificacion.SerializeToXml());
                break;
            case SEGUIMIENTOAMIGO:
                notificaciondb = new NotificacionDB(notificacion.getUsuarioOriginal().getId(),
                        new Date(),
                        String.format("%s ha empezado a seguir a %s.",
                        notificacion.getUsuarioOriginal().getAlias(),
                        notificacion.getUsuarioDestino().getAlias()),
                        notificacion.SerializeToXml());
                break;
        }

        try {
            iniciarTransaccion();
            sesion.save(notificaciondb);//guardamos el checkin en la tabla
            trx.commit();

            return notificaciondb.getIdnotificacion();

        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            cerrarSesion();
        }
        return 0;
    }

    //Devuelve la lista de notificaciones del usuario para rellenar su muro.
    //Metodo deprecado, se puede eliminar en la siguiente version.
    public List obtenerNotificacionesUsuarios(int idusuario) {
        GestionUsuarios gesUsuarios;
        List listaamigos;
        //primero recuperamos la lista de gente a la que sigue el usuario activo.
        gesUsuarios = new GestionUsuarios();
        listaamigos = gesUsuarios.listarAmigos(idusuario);
        //hay que recorrer esta lista obteniendo las notificaciones de la ultima semana.
        Iterator<Usuario> iterador = listaamigos.iterator();
        List listanotificaciones = new ArrayList();
        //vamos obteniedo la lista de notificaciones para cada usuario.
        while (iterador.hasNext()) {
            // listanotificaciones.addAll(obtenerNotificaciones(iterador.next().getId()));
        }
        return listanotificaciones;
    }

    public List obtenerNotificaciones(int idusuario, String urlxslt, int pageSize, int pageNumber) {

        iniciarTransaccion();
        try {
            Query query = sesion.createSQLQuery(" SELECT    "
                    + " cast(xmlelement (name notificaciones ,xmlelement (name notificacion, xmlattributes(INFONOT.fecha, INFONOT.xmlTipoNotificacion as \"tipoNotificacion\"), "
                    + " xmlelement (name infovino, "
                    + " xmlforest( "
                    + " tvinos.idvino, "
                    + " tvinos.nombre, "
                    + " tvinos.tipovino, "
                    + " tvinos.anio, "
                    + " tvinos.variedad, "
                    + " tvinos.zona, "
                    + " tvinos.pais, "
                    + " tvinos.rutaimagen) "
                    + " ), "
                    + " xmlelement (name infousuario, "
                    + " xmlforest( "
                    + " tusuarios.idusuario, "
                    + " tusuarios.ALIAS) "
                    + " ), "
                    + " xmlelement (name infousuariodestino, "
                    + " xmlforest( "
                    + " usuarioDes.idusuario, "
                    + " usuarioDes.ALIAS) "
                    + " ), "
                    + " xmlelement (name infologro, "
                    + " xmlforest( "
                    + " tlogros.idlogro, "
                    + " tlogros.nombrelogro, "
                    + " tlogros.descripcion "
                    + " ) "
                    + " ) "
                    + " )) as varchar )  "
                    + " FROM "
                    + " public.tusuarios "
                    + " INNER JOIN  "
                    + " (SELECT DISTINCT(cast(cast(((xpath('//Notificacion/usuarioOriginal/id/text()',xmlnotificacion))[1]) as varchar) as int4)) as xmlIsUsuarioOrig,  "
                    + " cast(cast(((xpath('//Notificacion/usuarioDestino/id/text()',xmlnotificacion))[1]) as varchar) as int4) as xmlIsUsuarioDest,  "
                    + " cast(cast(((xpath('//Notificacion/vino/idvino/text()',xmlnotificacion))[1]) as varchar ) as int4) as XMLIDVINO,  "
                    + " cast(((xpath('//Notificacion/tipoNotificacion/text()',xmlnotificacion))[1]) as varchar ) as xmlTipoNotificacion, "
                    + " cast(cast(((xpath('//Notificacion/Logro/IdLogro/text()',xmlnotificacion))[1]) as varchar) as int4) as xmlIdLogro, "
                    + " public.tnotificacion.idnotificacion, "
                    + " public.tnotificacion.fecha, "
                    + " public.tnotificacion.notificacion "
                    + " FROM "
                    + " public.tnotificacion   "
                    + " LEFT OUTER JOIN public.tseguimiento ON (public.tnotificacion.idusuario = public.tseguimiento.idamigo) "
                    + " WHERE   "
                    + " (public.tnotificacion.idusuario = :idusuario OR  public.tseguimiento.idusuario = :idusuario)  "
                    + " AND public.tnotificacion.xmlnotificacion IS NOT NULL) AS INFONOT  "
                    + " ON (public.tusuarios.idusuario = INFONOT.xmlIsUsuarioOrig) "
                    + " LEFT OUTER JOIN public.tvinos  "
                    + " on (public.tvinos.idvino = INFONOT.xmlidvino) "
                    + " LEFT OUTER JOIN public.tlogros "
                    + " on (public.tlogros.idlogro = INFONOT.xmlIdLogro)  "
                    + " LEFT OUTER JOIN public.tusuarios AS usuarioDes  "
                    + " ON  (usuarioDes.idusuario = INFONOT.xmlIsUsuarioDest) "
                    + " ORDER BY  "
                    + " INFONOT.fecha DESC "
                    + " LIMIT :pagesize OFFSET :offset ;  ");

            query.setParameter("idusuario", idusuario);
            //Si el metodo recibe como parametro informado el tamaño de pagina 
            //y el numero de pagina, el desplazamiento se calculca multiplicando el numero de pagina 
            //por el tamaño de la misma. Si uno de los valores es igual a 0 entonces establecemos
            //un máximo número de registros de 1000 y sin desplazamiento.
            if (pageSize > 0 && pageNumber > 0) {
                query.setParameter("pagesize", pageSize);
                query.setParameter("offset", pageSize * (pageNumber - 1));
            } else {
                query.setParameter("pagesize", 1000);
                query.setParameter("offset", 0);
            }

            List listanotificaciones = query.list();

            List listafinal = new ArrayList();
            File xslt = new File(urlxslt);
            if (xslt.canRead()) {



                for (int i = 0; i < listanotificaciones.size(); i++) {
                    String input = (String) listanotificaciones.get(i);
                    try {

                        Charset utf8 = Charset.forName("UTF-8");

                        StringReader reader = new StringReader(input);
                        StringWriter writer = new StringWriter();
                        TransformerFactory tFactory = TransformerFactory.newInstance();
                        Transformer transformer = tFactory.newTransformer(
                                new javax.xml.transform.stream.StreamSource(xslt));
                        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); //para omitir las formalidades de los xml.
                        transformer.transform(
                                new javax.xml.transform.stream.StreamSource(reader),
                                new javax.xml.transform.stream.StreamResult(writer));

                        String result = writer.toString();

                        listafinal.add(result);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            cerrarSesion();
            return listafinal;
        } catch (Exception e) {
            //toDo hay qe hacer algo con las excepcion.
            e.printStackTrace();

        }
        return null;
    }

    public List obtenerActualizaciones(String urlxslt, int pageSize, int pageNumber) {

        iniciarTransaccion();
        try {
            Query query = sesion.createSQLQuery(" SELECT cast(xmlelement (name notificaciones ,xmlelement (name notificacion, xmlattributes(INFONOT.fecha, INFONOT.xmlTipoNotificacion as  \"tipoNotificacion\"),"
                    + " xmlelement (name infovino, xmlforest(tvinos.idvino, "
                    + " tvinos.nombre, "
                    + " tvinos.tipovino, "
                    + " tvinos.anio, "
                    + " tvinos.variedad, "
                    + " tvinos.zona, "
                    + " tvinos.pais, "
                    + " tvinos.rutaimagen)), "
                    + " xmlelement (name infousuario, xmlforest( "
                    + " tusuarios.idusuario, "
                    + " tusuarios.ALIAS) ), "
                    + " xmlelement (name infousuariodestino, xmlforest( usuarioDes.idusuario, usuarioDes.ALIAS) ), "
                    + "  xmlelement (name infologro, xmlforest( tlogros.idlogro, tlogros.nombrelogro, tlogros.descripcion ) ) "
                    + " )) as varchar )  "
                    + "  FROM "
                    + " public.tusuarios "
                    + "  INNER JOIN  "
                    + " (SELECT DISTINCT(cast(cast(((xpath('//Notificacion/usuarioOriginal/id/text()',xmlnotificacion))[1]) as varchar) as int4)) as xmlIsUsuarioOrig,  "
                    + "  cast(cast(((xpath('//Notificacion/usuarioDestino/id/text()',xmlnotificacion))[1]) as varchar) as int4) as xmlIsUsuarioDest,  "
                    + " cast(cast(((xpath('//Notificacion/vino/idvino/text()',xmlnotificacion))[1]) as varchar ) as int4) as XMLIDVINO,  "
                    + "  cast(((xpath('//Notificacion/tipoNotificacion/text()',xmlnotificacion))[1]) as varchar ) as xmlTipoNotificacion, "
                    + " cast(cast(((xpath('//Notificacion/Logro/IdLogro/text()',xmlnotificacion))[1]) as varchar) as int4) as xmlIdLogro, "
                    + "  public.tnotificacion.idnotificacion, "
                    + " public.tnotificacion.fecha, "
                    + "  public.tnotificacion.notificacion "
                    + " FROM "
                    + "  public.tnotificacion                       "
                    + " LEFT OUTER JOIN public.tseguimiento ON (public.tnotificacion.idusuario = public.tseguimiento.idamigo) "
                    + "  WHERE   "
                    + " public.tnotificacion.xmlnotificacion IS NOT NULL)  AS INFONOT  "
                    + " ON ((public.tusuarios.idusuario = INFONOT.xmlIsUsuarioOrig) AND (public.tusuarios.tipoperfil LIKE 'privado')) "
                    + " LEFT OUTER JOIN public.tvinos  "
                    + "  on (public.tvinos.idvino = INFONOT.xmlidvino) "
                    + " LEFT OUTER JOIN public.tlogros "
                    + "  on (public.tlogros.idlogro = INFONOT.xmlIdLogro)  "
                    + " LEFT OUTER JOIN public.tusuarios AS usuarioDes  "
                    + "  ON  (usuarioDes.idusuario = INFONOT.xmlIsUsuarioDest) "
                    + " ORDER BY  "
                    + "  INFONOT.fecha DESC "
                    + " LIMIT :pagesize OFFSET :offset ; ");
            //Si el metodo recibe como parametro informado el tamaño de pagina 
            //y el numero de pagina, el desplazamiento se calculca multiplicando el numero de pagina 
            //por el tamaño de la misma. Si uno de los valores es igual a 0 entonces establecemos
            //un máximo número de registros de 1000 y sin desplazamiento.
            if (pageSize > 0 && pageNumber > 0) {
                query.setParameter("pagesize", pageSize);
                query.setParameter("offset", pageSize * (pageNumber - 1));
            } else {
                query.setParameter("pagesize", 1000);
                query.setParameter("offset", 0);
            }

            List listanotificaciones = query.list();

            List listafinal = new ArrayList();
            File xslt = new File(urlxslt);
            if (xslt.canRead()) {



                for (int i = 0; i < listanotificaciones.size(); i++) {
                    String input = (String) listanotificaciones.get(i);
                    try {

                        Charset utf8 = Charset.forName("UTF-8");

                        StringReader reader = new StringReader(input);
                        StringWriter writer = new StringWriter();
                        TransformerFactory tFactory = TransformerFactory.newInstance();
                        Transformer transformer = tFactory.newTransformer(
                                new javax.xml.transform.stream.StreamSource(xslt));
                        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); //para omitir las formalidades de los xml.
                        transformer.transform(
                                new javax.xml.transform.stream.StreamSource(reader),
                                new javax.xml.transform.stream.StreamResult(writer));

                        String result = writer.toString();

                        listafinal.add(result);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            cerrarSesion();
            return listafinal;
        } catch (Exception e) {
            //toDo hay qe hacer algo con las excepcion.
            e.printStackTrace();

        }
        return null;
    }

    public static String readFromFile(File file, Charset charset) throws IOException {
        InputStream in = new FileInputStream(file);
        Closeable stream = in;
        try {
            Reader reader = new InputStreamReader(in, charset);
            stream = reader;
            StringBuilder inputBuilder = new StringBuilder();
            char[] buffer = new char[1024];
            while (true) {
                int readCount = reader.read(buffer);
                if (readCount < 0) {
                    break;
                }
                inputBuilder.append(buffer, 0, readCount);
            }
            return inputBuilder.toString();
        } finally {
            stream.close();
        }
    }
}
