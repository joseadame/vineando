/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.ComentarioUsuario;
import tempranillo.entidades.Media;
import tempranillo.entidades.PruebaVino;
import tempranillo.entidades.Reporte;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionComentarios;
import tempranillo.modelo.GestionUsuarios;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author joseadamefernandez
 */
public class BusquedaVinoIDController extends AbstractController {

    private GestionVinos gesvinos;
    private GestionBodegas gesbodegas;
    private Vino vino;
    private Set listamedia;
    private ModelAndView vista;
    private GestionComentarios gestorComentarios;
    private GestionUsuarios gestorUsuarios;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        Integer idvino = Integer.parseInt(hsr.getParameter("idvino"));
        gesvinos = new GestionVinos();
        gesbodegas = new GestionBodegas();
        gestorComentarios = new GestionComentarios();
        gestorUsuarios = new GestionUsuarios();
        
        HttpSession session = hsr.getSession();
        Usuario user = (Usuario) session.getAttribute("usuarioEnSession");
        Map model = new HashMap();
        try {

            vino = gesvinos.buscarVinoID(idvino);
            vino = gesvinos.buscarVinoID(idvino);
            //listamedia = vino.getListaMedia();
            if (user != null) {
                List<Bodega> listadobodegas = gesbodegas.listarBodegasUsuario(user.getId());
                model.put("listabodegas", listadobodegas);
                model.put("usuario", user);
            }
            Usuario usuarioSommelier = gestorUsuarios.getSommelier(idvino);
            if (usuarioSommelier != null)
                model.put("usuarioSommelier", usuarioSommelier);
                        
            // List listacomentarios= gestorComentarios.listarComentariosVino(idvino);
            List listacomentarios = gestorComentarios.listarComentariosVinoPorFecha(idvino);
            List listacomentariosvotados = gestorComentarios.listarComentariosMasVotados(idvino);
            int checkins = gesvinos.numeroCheckins(idvino);
            Long almacenado = gesbodegas.numeroVecesEnWishlist(idvino);
            List listadocheckinsUsuarios = gesvinos.listarUsuariosHanHechoCheckins(idvino);

            model.put("bodega", new Bodega());
            model.put("Vino", vino);

            model.put("porcentajeCompletado", gesvinos.calcularPorcentaje(vino));
            model.put("updatevino", new Vino());
            model.put("busquedavino", new BusquedaVino());
            model.put("pruebavino", new PruebaVino());
            model.put("checkin", "");
            model.put("error", "");
            model.put("comentario", new ComentarioUsuario());
            model.put("listacomentarios", listacomentarios);
            model.put("listacomentariosvotados", listacomentariosvotados);
            model.put("checkins", checkins);
            model.put("almacenado", almacenado);
            model.put("reporteerror", new Reporte());
            model.put("listadocheckinusuarios", listadocheckinsUsuarios);
            // model.put("listaMedia",listamedia);

            vista = new ModelAndView("infoVino", model);
            return vista;
        } catch (HibernateException e) {
            List<Bodega> listadobodegas = gesbodegas.listarBodegasUsuario(user.getId());

            if (user != null) {
                // List<Bodega> listadobodegas = gesbodegas.listarBodegasUsuario(user.getId());
                model.put("listabodegas", listadobodegas);
                model.put("usuario", user);
            }
            // List listacomentarios= gestorComentarios.listarComentariosVino(idvino);
            List listacomentarios = gestorComentarios.listarComentariosVinoPorFecha(idvino);
            List listacomentariosvotados = gestorComentarios.listarComentariosMasVotados(idvino);
            int checkins = gesvinos.numeroCheckins(idvino);
            Long almacenado = gesbodegas.numeroVecesEnWishlist(idvino);
            List listadocheckinsUsuarios = gesvinos.listarUsuariosHanHechoCheckins(idvino);

            model.put("bodega", new Bodega());
            model.put("Vino", vino);

            model.put("porcentajeCompletado", gesvinos.calcularPorcentaje(vino));
            model.put("updatevino", new Vino());
            model.put("busquedavino", new BusquedaVino());
            model.put("pruebavino", new PruebaVino());
            model.put("checkin", "");
            model.put("error", "");
            model.put("comentario", new ComentarioUsuario());
            model.put("listacomentarios", listacomentarios);
            model.put("listacomentariosvotados", listacomentariosvotados);
            model.put("checkins", checkins);
            model.put("almacenado", almacenado);
            model.put("reporteerror", new Reporte());
            model.put("listadocheckinusuarios", listadocheckinsUsuarios);
            model.put("listaMedia", listamedia);

            vista = new ModelAndView("infoVino", model);
            return vista;
        }
    }
}
