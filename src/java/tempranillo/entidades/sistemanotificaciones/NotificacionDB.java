/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades.sistemanotificaciones;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Oscar CN
 */
public class NotificacionDB implements Serializable {

    public NotificacionDB() {
    }

    public NotificacionDB(int idusuario, 
                            Date fecha, 
                            String notificacion) {
        this.idusuario = idusuario;
        this.fecha = fecha;
        this.notificacion = notificacion;
    }
    
     public NotificacionDB(int idusuario, 
                            Date fecha, 
                            String notificacion, 
                            String xmlnotificacion) {
        this.idusuario = idusuario;
        this.fecha = fecha;
        this.notificacion = notificacion;
        this.xmlnotificacion = xmlnotificacion;
    }
    
    
    private int idnotificacion;

    /**
     * Get the value of idnotificacion
     *
     * @return the value of idnotificacion
     */
    public int getIdnotificacion() {
        return idnotificacion;
    }

    /**
     * Set the value of idnotificacion
     *
     * @param idnotificacion new value of idnotificacion
     */
    private void setIdnotificacion(int idnotificacion) {
        this.idnotificacion = idnotificacion;
    }
    private int idusuario;

    /**
     * Get the value of idusuario
     *
     * @return the value of idusuario
     */
    public int getIdusuario() {
        return idusuario;
    }

    /**
     * Set the value of idusuario
     *
     * @param idusuario new value of idusuario
     */
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    private Date fecha;

    /**
     * Get the value of fecha
     *
     * @return the value of fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Set the value of fecha
     *
     * @param fecha new value of fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    private String notificacion;

    /**
     * Get the value of notificacion
     *
     * @return the value of notificacion
     */
    public String getNotificacion() {
        return notificacion;
    }

    /**
     * Set the value of notificacion
     *
     * @param notificacion new value of notificacion
     */
    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }
    
    private String xmlnotificacion;

    /**
     * Get the value of xmlnotificacion
     *
     * @return the value of xmlnotificacion
     */
    public String getXmlnotificacion() {
        return xmlnotificacion;
    }

    /**
     * Set the value of xmlnotificacion
     *
     * @param xmlnotificacion new value of xmlnotificacion
     */
    public void setXmlnotificacion(String xmlnotificacion) {
        this.xmlnotificacion = xmlnotificacion;
    }

}
