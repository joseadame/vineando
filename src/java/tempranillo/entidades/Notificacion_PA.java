/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.io.Serializable;
import java.util.Date;
import tempranillo.utils.SQLXMLType;

/**
 *
 * @author Jose Adame
 */
public class Notificacion_PA implements Serializable{
    
    
    private Date fecha;
    private String notificacioncolumn;
    private SQLXMLType xmlcolumn;
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNotificacioncolumn() {
        return notificacioncolumn;
    }

    public void setNotificacioncolumn(String notificacioncolumn) {
        this.notificacioncolumn = notificacioncolumn;
    }

    public SQLXMLType getXmlcolumn() {
        return xmlcolumn;
    }

    public void setXmlcolumn(SQLXMLType xmlcolumn) {
        this.xmlcolumn = xmlcolumn;
    }
    
    
    
    
    
    
    
    
    
    
}
