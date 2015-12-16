/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.webservice.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.Checkin;
import tempranillo.modelo.GestionCheckins;
import tempranillo.modelo.GestionVinos;

/**
 *
 * @author Oscar
 */
@Controller
@RequestMapping(value="/vino/checkin")
public class checkinController {
    
    /**
     * Obtener el número de checkins total para un vino.
     * @param idVino
     * @return numero de Checkins para ese vino
     */
    @RequestMapping(value="/checkins/total/{idVino}",produces = "application/json")
    public @ResponseBody int obtenerNumeroCheckins(@PathVariable int idVino) {
        int numero=0;
         GestionVinos gestorVinos = new GestionVinos();
         numero= gestorVinos.numeroCheckins(idVino);
        return numero;
    }
    
    /**
     * Crea un nuevo checkin para un identificador de vino.
     * @param checkin
     * @return 
     */
    @RequestMapping(value="/{idVino}", method= RequestMethod.PUT,  produces = "application/json")
    public @ResponseBody int createCheckin(@RequestBody  Checkin checkin) {
        Date fechaactual = new Date();
        checkin.setFecha(fechaactual);
        GestionCheckins gestorCheckins = new GestionCheckins();
        return  gestorCheckins.nuevoCheckin(checkin, checkin.getIdvino());               
    }   
    
    /**
     * Obtiene los checkins asociados a un vino.
     * @param idVino
     * @return 
     */
    @RequestMapping(value="/{idVino}", method= RequestMethod.GET,  produces = "application/json")
    public @ResponseBody List<Checkin> SearchCheckin(@RequestBody  int idVino) {
        GestionCheckins gestorCheckins = new GestionCheckins();
        return gestorCheckins.getCheckinsVino(idVino);
    }
}
