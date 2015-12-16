/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

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
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.ComentarioUsuario;
import tempranillo.entidades.PruebaVino;
import tempranillo.entidades.Reporte;
import tempranillo.entidades.sistemalogros.TipoAccion;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionComentarios;
import tempranillo.modelo.GestionEventos;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author Jose Adame
 */
public class enviarComentarioController extends AbstractCommandController {

    private GestionComentarios gestorComentarios;
    private GestionBodegas gestorBodegas;
    private GestionVinos gestorVinos;
    private GestionEventos gestorEventos;

    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {

        HttpSession session = hsr.getSession();//obtenemos el usuario logueado.
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");

        Integer idvino = Integer.parseInt( hsr.getParameter("idvino"));
       List<Logro> _listaacciones=null;
        ComentarioUsuario comentario = (ComentarioUsuario) o;
        Map model = new HashMap();
        ModelAndView vista;
         if (userSesion!=null)
            {
        try{

            gestorComentarios= new GestionComentarios();
            gestorBodegas= new GestionBodegas();
            gestorVinos= new GestionVinos();
            gestorEventos= new GestionEventos();

                    //añadimos el comentario a la BD.
                    gestorComentarios.addComentarioVino(idvino, userSesion.getId(), comentario.getComentario());
                    Integer puntuacionObtenida = null; 
                   _listaacciones= gestorEventos.comprobarEvento(TipoAccion.COMENTARIOVINO, userSesion.getId());
                    //TODO: Utilizar la puntuación obtenida para informar al usuario.
                    List<Bodega> listadobodegas = gestorBodegas.listarBodegasUsuario(userSesion.getId());
                    Vino vino = gestorVinos.buscarVinoID(idvino);
                    List listacomentarios = gestorComentarios.listarComentariosVinoPorFecha(idvino);
                    List listacomentariosvotados = gestorComentarios.listarComentariosMasVotados(idvino);
                    int checkins = gestorVinos.numeroCheckins(idvino);
                    Long almacenado = gestorBodegas.numeroVecesEnWishlist(idvino);
                    List listadoCheckinsUsuarios = gestorVinos.listarUsuariosHanHechoCheckins(idvino);
                    model.put("bodega", new Bodega());
                    model.put("Vino", vino);
                    model.put("listabodegas", listadobodegas);
                    model.put("porcentajeCompletado", gestorVinos.calcularPorcentaje(vino));
                    model.put("updatevino", new Vino());
                    model.put("busquedavino", new BusquedaVino());
                    model.put("pruebavino", new PruebaVino());
                    model.put("checkin", "");
                    model.put("error", "");
                    model.put("listacomentarios", listacomentarios);
                    model.put("comentario", new ComentarioUsuario());
                    model.put("listacomentariosvotados", listacomentariosvotados);
                    model.put("checkins", checkins);
                    model.put("almacenado", almacenado);
                    model.put("usuario", userSesion);
                    model.put("listadocheckinusuarios", listadoCheckinsUsuarios);
                    model.put("reporteerror", new Reporte());
                    model.put("listaacciones",_listaacciones);
                    vista = new ModelAndView("infoVino", model);

                    return vista;


                } catch (HibernateException e) {

                    List<Bodega> listadobodegas = gestorBodegas.listarBodegasUsuario(userSesion.getId());
                    Vino vino = gestorVinos.buscarVinoID(idvino);
                    List listacomentarios = gestorComentarios.listarComentariosVinoPorFecha(idvino);
                    List listacomentariosvotados = gestorComentarios.listarComentariosMasVotados(idvino);
                    int checkins = gestorVinos.numeroCheckins(idvino);
                    Long almacenado = gestorBodegas.numeroVecesEnWishlist(idvino);
                    model.put("checkin", "");
                    model.put("Vino", vino);
                    model.put("error", e.getMessage());
                    model.put("listadobodegas", listadobodegas);
                    model.put("busquedavino", new BusquedaVino());
                    model.put("pruebavino", new PruebaVino());
                    model.put("updatevino", new Vino());
                    model.put("bodega", new Bodega());
                    model.put("comentario", new ComentarioUsuario());
                    model.put("listacomentarios", listacomentarios);
                    model.put("listacomentariosvotados", listacomentariosvotados);
                    model.put("checkins", checkins);
                    model.put("almacenado", almacenado);
                    model.put("reporteerror", new Reporte());
                    vista = new ModelAndView("infoVino", model);

                    return vista;
             }

            } else {
                model.put("login", new Usuario());
                model.put("loginUsuario", new BusquedaVino());
                model.put("error", "");
                vista = new ModelAndView("loginUsuario", model);
                return vista;



            }
        }
    }
