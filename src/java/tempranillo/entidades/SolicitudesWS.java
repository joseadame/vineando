/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.util.List;

/**
 *
 * @author Jose
 * Almacena el resultado de obtener las solicitudes de amistad mediante el webservice
 * 
 * 
 */
public class SolicitudesWS {
    
    private String Status;
    private List listaSolicitudes;
    private String mensajeError;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public List getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
    
    
    
    
    
    
    
    
}
