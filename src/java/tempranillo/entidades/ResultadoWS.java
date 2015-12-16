/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.util.List;

/**
 *
 * @author Jose
 * Devuelve el estado de la ejecución del servicio webservice.
 * 
 */
public class ResultadoWS {
    
    private String status;
    private String mensajeerror;
    private List notificaciones;
    private String mensajeResultado;

    public List getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List notificaciones) {
        this.notificaciones = notificaciones;
    }
    
    
    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensajeerror() {
        return mensajeerror;
    }

    public void setMensajeerror(String mensajeerror) {
        this.mensajeerror = mensajeerror;
    }

    public String getMensajeResultado() {
        return mensajeResultado;
    }

    public void setMensajeResultado(String mensajeResultado) {
        this.mensajeResultado = mensajeResultado;
    }
    
    
    
    
}
