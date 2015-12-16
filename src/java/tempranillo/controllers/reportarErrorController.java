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
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionComentarios;
import tempranillo.modelo.GestionReportes;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author Jose Adame
 */
public class reportarErrorController extends AbstractCommandController{
    
    private GestionReportes gesreportes;
    private GestionBodegas gesbodegas;
    private GestionComentarios gestorComentarios;
    private GestionVinos gesvinos;
    private ModelAndView vista;

    @Override
    protected ModelAndView handle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
        
        HttpSession session = hsr.getSession();//obtenemos el usuario logueado.
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        
        String idvino = hsr.getParameter("idvino");
        
        Reporte repor = (Reporte)o;
        Map model = new HashMap();
        Vino vino;
        try{
           gesreportes= new GestionReportes();
           
           //llamamos al servicio que reporta el error del usuario.
           repor.setIdusuario(userSesion.getId());
           repor.setIdvino(Integer.parseInt(idvino));
           
           gesreportes.reportarError(repor);
           
           
           gesvinos=new GestionVinos();
           gesbodegas= new GestionBodegas();
           gestorComentarios = new GestionComentarios();
           vino=gesvinos.buscarVinoID(Integer.parseInt(idvino));
           List<Bodega> listadobodegas = gesbodegas.listarBodegasUsuario(userSesion.getId());  
           model.put("listabodegas",listadobodegas);
           model.put("usuario",userSesion);
           List listacomentarios= gestorComentarios.listarComentariosVinoPorFecha(Integer.parseInt(idvino));
           List listacomentariosvotados =gestorComentarios.listarComentariosMasVotados(Integer.parseInt(idvino));
           int checkins = gesvinos.numeroCheckins(Integer.parseInt(idvino));
           Long almacenado= gesbodegas.numeroVecesEnWishlist(Integer.parseInt(idvino));
           List listadocheckinsUsuarios = gesvinos.listarUsuariosHanHechoCheckins(Integer.parseInt(idvino));
           
           model.put("bodega", new Bodega());
           model.put("Vino",vino);
           
           model.put("porcentajeCompletado", gesvinos.calcularPorcentaje(vino));
           model.put("updatevino",new Vino());
           model.put("busquedavino", new BusquedaVino());
           model.put("pruebavino", new PruebaVino());
           model.put("checkin","");
           model.put("error","");
           model.put("comentario", new ComentarioUsuario());
           model.put("listacomentarios",listacomentarios);
           model.put("listacomentariosvotados",listacomentariosvotados);
           model.put("checkins",checkins);
           model.put("reporteerror", new Reporte());
           model.put("almacenado", almacenado);
          
           model.put("listadocheckinusuarios",listadocheckinsUsuarios);
           
           vista = new ModelAndView("infoVino", model);
           return vista;
            
        
        }
        catch (HibernateException he)
        {
        
            vino=gesvinos.buscarVinoID(Integer.parseInt(idvino));
           List<Bodega> listadobodegas = gesbodegas.listarBodegasUsuario(userSesion.getId());  
           model.put("listabodegas",listadobodegas);
           model.put("usuario",userSesion);
           List listacomentarios= gestorComentarios.listarComentariosVinoPorFecha(Integer.parseInt(idvino));
           List listacomentariosvotados =gestorComentarios.listarComentariosMasVotados(Integer.parseInt(idvino));
           int checkins = gesvinos.numeroCheckins(Integer.parseInt(idvino));
           Long almacenado= gesbodegas.numeroVecesEnWishlist(Integer.parseInt(idvino));
           List listadocheckinsUsuarios = gesvinos.listarUsuariosHanHechoCheckins(Integer.parseInt(idvino));
           
           model.put("bodega", new Bodega());
           model.put("Vino",vino);
           model.put("error",he.getMessage());
           model.put("porcentajeCompletado", gesvinos.calcularPorcentaje(vino));
           model.put("updatevino",new Vino());
           model.put("busquedavino", new BusquedaVino());
           model.put("pruebavino", new PruebaVino());
           model.put("checkin","");
           model.put("error","");
           model.put("comentario", new ComentarioUsuario());
           model.put("listacomentarios",listacomentarios);
           model.put("listacomentariosvotados",listacomentariosvotados);
           model.put("checkins",checkins);
           model.put("almacenado", almacenado);
           model.put("reporteerror", new Reporte());
           model.put("listadocheckinusuarios",listadocheckinsUsuarios);
           
           vista = new ModelAndView("infoVino", model);
           return vista;
        
        
        }
        
        
        
        
    }
    
    
}
