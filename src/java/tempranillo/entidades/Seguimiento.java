/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.util.Date;

/**
 *
 * @author Jose Adame
 */
public class Seguimiento {
    
    
    private int idseguimiento;
    private int idusuario;
    private int idamigo;
    private Date fecha;
    
    public Seguimiento(){}

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

    public int getIdseguimiento() {
        return idseguimiento;
    }

    private void setIdseguimiento(int idseguimiento) {
        this.idseguimiento = idseguimiento;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
    
    
}
