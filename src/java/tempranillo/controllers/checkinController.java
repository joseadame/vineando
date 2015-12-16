        /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import tempranillo.entidades.*;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.entidades.sistemalogros.TipoAccion;
import tempranillo.entidades.sistemanotificaciones.Notificacion;
import tempranillo.modelo.*;

/**
 *
 * @author Jose Adame
 */
public class checkinController extends AbstractCommandController {

    private GestionCheckins gestorCheckins;
    private GestionBodegas gestorBodegas;
    private GestionVinos gestorVinos;
    private GestionComentarios gestorComentarios;
    private GestionEventos gestorEventos;
    private GestionNotificacion gestorNotificacion;

    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {

        List<Logro> _listaAccion;
        HttpSession session = hsr.getSession();//obtenemos el usuario logueado.
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        Integer idvino = Integer.parseInt(hsr.getParameter("idvino"));
        PruebaVino prueba = (PruebaVino) o; //recogemos el checkin del usuario.


        Map model = new HashMap();
        ModelAndView vista;
        gestorCheckins = new GestionCheckins();
        gestorBodegas = new GestionBodegas();
        gestorVinos = new GestionVinos();
        gestorComentarios = new GestionComentarios();
        gestorEventos = new GestionEventos();
        gestorNotificacion = new GestionNotificacion();


        try {


            //comprobamos que no se hecho mas de un checkin ese dia para otorgar los puntos
            if (gestorCheckins.checkinVinoPorDia(idvino, userSesion.getId(), new Date()) == 0) {

                //creamos el checkin para meterlo en la BD.
                Checkin checkin = new Checkin();
                checkin.setIdusuario(userSesion.getId());
                checkin.setIdvino(idvino);
                checkin.setComentario(prueba.getComentario());
                if (prueba.getNota() != null) {
                    checkin.setNota(prueba.getNota());
                } else {
                    checkin.setNota(0.0);
                }
                checkin.setRelacion(prueba.getRelacion_calidad());
                checkin.setCiudad(prueba.getCiudad());
                checkin.setEstablecimiento(prueba.getEstablecimiento());
                Date fechaactual = new Date();
                checkin.setFecha(fechaactual);
                gestorCheckins.nuevoCheckin(checkin, idvino);
                //TODO: En lugar de transferir el idVino, ¿no podriamos pasar la entidad Vino?
                Notificacion n =  new Notificacion(Notificacion.TipoNotificacion.CHECKIN, gestorVinos.buscarVinoID(idvino), userSesion);
                gestorNotificacion.createNotificacion(n);
                
                //calculamos la puntuacion del vino.
                gestorVinos.calcularPuntacion(idvino);
                //Incrementamos la puntuacion del usuario
                //y comprobamos sus logros a traves del evento.                
                //Comprobamos el evento desencadenado, para ello informamos el tipo de evento, 
                //y el identificador del usuario.
                _listaAccion = gestorEventos.comprobarEvento(TipoAccion.CHECKIN, userSesion.getId(),idvino);
                model.put("puntos", _listaAccion);
                model.put("listaacciones", _listaAccion);
                
                if (!prueba.getComentario().equals("")) {
                    gestorComentarios.addComentarioVino(idvino, userSesion.getId(), prueba.getComentario());
                }
                model.put("checkin", "ok");
            } else {
                model.put("checkin", "limitealcanzado");
            }
            List<Bodega> listadobodegas = gestorBodegas.listarBodegasUsuario(userSesion.getId());
            Vino vino = gestorVinos.buscarVinoID(idvino);
            List listacomentarios = gestorComentarios.listarComentariosVinoPorFecha(idvino);
            List listacomentariosvotados = gestorComentarios.listarComentariosMasVotados(idvino);
            int checkins = gestorVinos.numeroCheckins(idvino);
            Long almacenado = gestorBodegas.numeroVecesEnWishlist(idvino);
            List listadocheckinsUsuarios = gestorVinos.listarUsuariosHanHechoCheckins(idvino);
            model.put("bodega", new Bodega());
            model.put("Vino", vino);
            model.put("listabodegas", listadobodegas);
            model.put("porcentajeCompletado", gestorVinos.calcularPorcentaje(vino));
            model.put("updatevino", new Vino());
            model.put("busquedavino", new BusquedaVino());
            model.put("pruebavino", new PruebaVino());
            // model.put("checkin","ok");
            model.put("error", "");
            model.put("listacomentarios", listacomentarios);
            model.put("comentario", new ComentarioUsuario());
            model.put("listacomentariosvotados", listacomentariosvotados);
            model.put("checkins", checkins);
            model.put("almacenado", almacenado);
            model.put("usuario", userSesion);
            model.put("listadocheckinusuarios", listadocheckinsUsuarios);
            model.put("reporteerror", new Reporte());
            model.put("accion", "¡Buen checkin!");

            vista = new ModelAndView("infoVino", model);
            return vista;



        } catch (HibernateException e) {
            Vino vino = gestorVinos.buscarVinoID(idvino);
            List<Bodega> listadobodegas = gestorBodegas.listarBodegasUsuario(userSesion.getId());
            List listacomentariosvotados = gestorComentarios.listarComentariosMasVotados(idvino);
            int checkins = gestorVinos.numeroCheckins(idvino);
            Long almacenado = gestorBodegas.numeroVecesEnWishlist(idvino);
            List listadocheckinsUsuarios = gestorVinos.listarUsuariosHanHechoCheckins(idvino);
            model.put("checkin", "");
            model.put("porcentajeCompletado", gestorVinos.calcularPorcentaje(vino));
            model.put("Vino", vino);
            model.put("error", e.getMessage());
            model.put("listadobodegas", listadobodegas);
            model.put("busquedavino", new BusquedaVino());
            model.put("pruebavino", new PruebaVino());
            model.put("updatevino", new Vino());
            model.put("bodega", new Bodega());
            model.put("comentario", new ComentarioUsuario());
            model.put("listacomentariosvotados", listacomentariosvotados);
            model.put("checkins", checkins);
            model.put("almacenado", almacenado);
            model.put("usuario", userSesion);
            model.put("listadocheckinusuarios", listadocheckinsUsuarios);
            model.put("reporteerror", new Reporte());
            vista = new ModelAndView("infoVino", model);
            return vista;
        }
    }
}
