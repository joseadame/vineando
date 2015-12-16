/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.util.List;

/**
 *
 * @author Jose
 */
public class NuevaListaResult {
    
    private String status;
    
    //Contiene la lista de las notificaciones que pueden producirse al dar de alta una lista.
    private List listanotificaciones;

    public List getListanotificaciones() {
        return listanotificaciones;
    }

    public void setListanotificaciones(List listanotificaciones) {
        this.listanotificaciones = listanotificaciones;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    
    
    
    
}
