package tempranillo.webservice.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.modelo.GestionUsuarios;
import tempranillo.modelo.GestionVinos;



/**
 *
 * @author Jose Adame
 * Implementa todos los servicios de la API referentes a rankings de usuarios y vinos.
 */

@Controller
@RequestMapping(value="/ranking")
public class RankingController {
    
    
    
    /**
     * 
     * @return Devuelve una lista de los usuarios con mas checkins 
     */
    @RequestMapping(value="/topUsuarios")
    @ResponseBody List topUsuarios(){
    
        GestionUsuarios gesusuarios = new GestionUsuarios();
        return gesusuarios.listaTopUserCheckins();
        
        
    
    
    
    } 
    
    
    /**
     * 
     * @return Devuelve los 5 vinos con mas checkins.
     */
    
    @RequestMapping(value="/topCheckinsVinos")
    @ResponseBody List topVinos (){
    
        GestionVinos gesvinos= new GestionVinos();
     return    gesvinos.listaTopVinosCheckins(5);
    
    
    }
    
    
    /**
     * 
     *
     * @return Devuelve los vinos con mayor puntuacion 
     */
    
    
    @RequestMapping(value="/topPuntuacionVinos")
    @ResponseBody List topPuntuacionVinos (){
    
        GestionVinos gesvinos= new GestionVinos();
     return    gesvinos.buscarVinosMayorPuntuacion();
    
    
    }
    
    
    /**
     * 
     * 
     * @return Devuelve los usuarios con mayor puntuacion 
     */
    
    
    @RequestMapping(value="/topPuntuacionUsuarios")
    @ResponseBody List topPuntuacionUsuarios (){
    
       GestionUsuarios gesusuarios = new GestionUsuarios();
     return    gesusuarios.obtenerTopPuntuacionUsuarios();
    
    
    }
    
    
    
}
