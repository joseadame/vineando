/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionBodegas;

import tempranillo.modelo.GestionEventEstbl;

import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author one_t_000
 */
public class mostrarEventosCiudadController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {

        GestionBodegas gesbodegas = new GestionBodegas();
        GestionEventEstbl geseventos = new GestionEventEstbl();
        GestionUsuarios gestorUsuarios = new GestionUsuarios();

        HttpSession session = hsr.getSession();
        Usuario user = (Usuario) session.getAttribute("usuarioEnSession");
        Map model = new HashMap();
        try {

            if (user != null) {
                List<Bodega> listadobodegas = gesbodegas.listarBodegasUsuario(user.getId());
                model.put("listabodegas", listadobodegas);
                model.put("UsuarioSesion", user);
            }

            List listaeventos = geseventos.getEventos();
            model.put("busquedavino", new BusquedaVino());
            model.put("error", "");
            model.put("listaevtpatrocinados", null); //en un futuro habria que realizar esta consulta.
            model.put("listaeventos", listaeventos);
            ModelAndView vista = new ModelAndView("eventos", model);

            return vista;

        } catch (HibernateException e) {
            model.put("busquedavino", new BusquedaVino());
            model.put("error", "Se ha producido un error al recuperar los eventos");
            ModelAndView vista = new ModelAndView("eventos", model);
            return vista;
        }
    }
}
