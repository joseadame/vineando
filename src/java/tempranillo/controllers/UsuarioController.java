/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import tempranillo.BD.GestionBD;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.GestionUsuarios;

/**
 *
 * @author Jose Adame
 */
public class UsuarioController extends SimpleFormController {

    private GestionUsuarios gestorUsuarios;

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
        ModelAndView _modelAndView = null;
        Integer idUsuario = null;
        Usuario usuarioFormulario = (Usuario) command;
        gestorUsuarios = new GestionUsuarios();//esto habria que reemplazarlo por injeccion directa en spring.
        try {
            idUsuario = gestorUsuarios.altaUsuario(usuarioFormulario);//devolvemos el id de usuario con el que se ha dado de alta.
        } catch (HibernateException e) {
            //TODO: Realizar log del error.
            Map model = new HashMap();
            model.put("error", e.getMessage().toString());
            model.put(getCommandName(), usuarioFormulario);
            _modelAndView = new ModelAndView(getFormView(), model);
        } finally {
            if (idUsuario != null) {
                Map model = new HashMap();
                model.put("titulos", "Alta con exito");
                model.put("idusuario", idUsuario);
                model.put(getCommandName(), usuarioFormulario);
                _modelAndView = new ModelAndView(getSuccessView(), model);
            }
        }

        //Creamos la variable de sesion del usuario.
        HttpSession session = request.getSession();
        session.setAttribute("usuarioEnSession", usuarioFormulario);

        return _modelAndView;

    }
}
