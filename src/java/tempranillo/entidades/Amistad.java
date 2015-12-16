/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.util.Date;

/**
 *
 * @author Jose Adame
 * 
 * Esta clase implementa la amistad entre 2 usuarios, equivale a un registro de la tabla Tamistad.
 */
public class Amistad {
    private int idamistad;
    private int idusuario;
    private int idamigo;
    private Date fecha;
    private int estado;
    private String mensaje;
    
    
    public Amistad(){}
    

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdamigo() {
        return idamigo;
    }

    public void setIdamigo(int idamigo) {
        this.idamigo = idamigo;
    }

    public int getIdamistad() {
        return idamistad;
    }

    private void setIdamistad(int idamistad) {
        this.idamistad = idamistad;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
    
}
