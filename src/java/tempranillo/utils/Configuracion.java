/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import tempranillo.modelo.GestionEventos;

/**
 *
 * @author Jose
 */
public class Configuracion {  
    
    /**
     * Obtiene la url de la aplicación en servidor.
     * @return Url del servidor.
     */
    public String getURL(){    
     Properties prop = new Properties();            
        InputStream is = this.getClass().getResourceAsStream("configuracion.properties");               
        try {
            prop.load(is);
        } catch (IOException ex) {
            Logger.getLogger(GestionEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prop.getProperty("urlservidor");    
    }
    
    /**
     * Obtiene la cadena de la imagen por defecto del vino
     * @return 
     */ 
    public String getCadenaImagenVino()
    {
    Properties prop = new Properties();            
        InputStream is = this.getClass().getResourceAsStream("configuracion.properties");               
        try {
            prop.load(is);
        } catch (IOException ex) {
            Logger.getLogger(GestionEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prop.getProperty("cadenaImagenVinoDefecto");         
    }
}