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
public class WishList {
    int idwishlist;
    int idusuario;
    int idvino;
    Date fecha;
    
    public WishList(){}

    public Date getFecha() {
        return fecha;
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

    public int getIdvino() {
        return idvino;
    }

    public void setIdvino(int idvino) {
        this.idvino = idvino;
    }

    public int getIdwishlist() {
        return idwishlist;
    }

    private void setIdwishlist(int idwishlist) {
        this.idwishlist = idwishlist;
    }
    
    
    
    
    
    
    
}
