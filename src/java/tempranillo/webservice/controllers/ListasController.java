/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.webservice.controllers;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.InfoLista;
import tempranillo.entidades.ResultadoWS;
import tempranillo.modelo.GestionBodegas;


/**
 *
 * @author Jose
 * 
 * Servicio de la API que se encarga de gestionar las listas de los usuarios para poder eliminarlas o eliminar vinos de una determinada lista.
 * 
 */



@Controller
@RequestMapping(value="/lista")
public class ListasController {
    
    GestionBodegas gestionbodegas;
    ResultadoWS resultado;
    @RequestMapping(value="/borrar", method= RequestMethod.PUT,  produces = "application/json")
    @ResponseBody ResultadoWS borrarLista(@RequestBody InfoLista info)
    {
    
        gestionbodegas = new GestionBodegas();
        
        try{
        gestionbodegas.borrarBodega(info.getIdlista());
        resultado= new ResultadoWS();
        resultado.setStatus("OK");
       
        return resultado;
        }
        catch(HibernateException e)
        {
            resultado = new ResultadoWS();
            resultado.setStatus("ERROR");
            resultado.setMensajeerror(e.getLocalizedMessage());
        return resultado;
        
        }
        
        
        
    
    }  
    
    @RequestMapping(value="/borrarvino", method= RequestMethod.PUT,  produces = "application/json")
    @ResponseBody ResultadoWS borrarVinoDeLista(@RequestBody InfoLista info)
    {
    
        gestionbodegas = new GestionBodegas();
        
        try{
        gestionbodegas.borrarVinoDeBodega(info.getIdlista(),info.getIdvino());
        resultado= new ResultadoWS();
        resultado.setStatus("OK");
       
        return resultado;
        }
        catch(HibernateException e)
        {
            resultado = new ResultadoWS();
            resultado.setStatus("ERROR");
            resultado.setMensajeerror(e.getLocalizedMessage());
        return resultado;
        
        }
        
        
        
    
    }  
    
    
    
    
    
    
    
}
