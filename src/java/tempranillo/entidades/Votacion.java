/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.util.Date;

/**
 *
 * @author joseadamefernandez
 */
public class Votacion {
    
    int idvotacion;
    int idcomentario;
    int idusuario;
    Date fecha;
    String tipovoto;

    public String getTipovoto() {
        return tipovoto;
    }

    public void setTipovoto(String tipovoto) {
        this.tipovoto = tipovoto;
    }
    
    
    public Votacion()
    {
    
    
    }

    public Date getFecha() {
        return fecha;
    }

    public int getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

   

    public int getIdvotacion() {
        return idvotacion;
    }

    private void setIdvotacion(int idvotacion) {
        this.idvotacion = idvotacion;
    }
    
    
    
    
}
