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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.PruebaVino;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionUsuarios;
import tempranillo.modelo.GestionVinos;

/**
 * Controlador que se encarga de mostrar la pagina de checkins para los vinos.
 *
 * @author Jose Adame
 */
public class checkinVinoController extends AbstractController {

    private GestionBodegas gestorBodegas;
    private GestionUsuarios gestorusuarios;
    private GestionVinos gestorvinos;
    private ModelAndView vista;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {


        HttpSession session = hsr.getSession();
        Usuario user = (Usuario) session.getAttribute("usuarioEnSession");
        String idvino = hsr.getParameter("idvino");
        try {

            //sacamos los datos que necesitamos para mostrar la pagina.

            gestorBodegas = new GestionBodegas();
            gestorusuarios = new GestionUsuarios();
            gestorvinos = new GestionVinos();
            Vino vinoencontrado = gestorvinos.buscarVinoID(Integer.parseInt(idvino));
            List<Bodega> listadobodegas = gestorBodegas.listarBodegasUsuario(user.getId());
            List listaTopUserCheckins = gestorusuarios.listaTopUserCheckins();
            List listaTopVinosCheckins = gestorvinos.listaTopVinosCheckins(5);
            String fechaultimacata = gestorvinos.fechaUltimaCata(user.getId(), Integer.parseInt(idvino));
            Map model = new HashMap();
            model.put("fechaultimacata", fechaultimacata);
            model.put("pruebavino", new PruebaVino());
            model.put("listabodegas", listadobodegas);
            model.put("busquedavino", new BusquedaVino());
            model.put("UsuarioSesion", user);
            model.put("usuario", user);
            model.put("idvino", idvino);
            model.put("listatopVinosCheckins", listaTopVinosCheckins);
            model.put("listatopUserCheckins", listaTopUserCheckins);
            model.put("vino", vinoencontrado);
            vista = new ModelAndView("checkinVino", model);
            return vista;

        } catch (HibernateException he) {
            gestorBodegas = new GestionBodegas();
            gestorusuarios = new GestionUsuarios();
            List<Bodega> listadobodegas = gestorBodegas.listarBodegasUsuario(user.getId());
            Map model = new HashMap();
            model.put("pruebavino", new PruebaVino());
            model.put("listabodegas", listadobodegas);
            model.put("busquedavino", new BusquedaVino());
            model.put("UsuarioSesion", user);
            model.put("usuario", user);
            vista = new ModelAndView("infoVino", model);
            return vista;

        }




    }
}
