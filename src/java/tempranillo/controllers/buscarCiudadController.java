/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.modelo.GestionCheckins;

/**
 *
 * @author Jose Adame
 */

@Controller
public class buscarCiudadController {
    
    
    @RequestMapping("buscarciudad.htm")
     public @ResponseBody List<String> buscarCiudad(HttpServletRequest hsr, HttpServletResponse hsr1)
    {
        List ciudades= null;
        
        String clave = hsr.getParameter("term");
        
        GestionCheckins gestorcheckins = new GestionCheckins();
        
        ciudades=gestorcheckins.buscarCiudad(clave);
        
        return ciudades;
    
    }
    
}
