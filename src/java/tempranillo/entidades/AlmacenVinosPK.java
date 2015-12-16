/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.io.Serializable;

/**
 *
 * @author joseadamefernandez
 */
public class AlmacenVinosPK implements Serializable{
    
    public int idbodega;
    public int idvino;
    
    //hay que sobreescribir los metodos equals...

    public int getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(int idbodega) {
        this.idbodega = idbodega;
    }

    public int getIdvino() {
        return idvino;
    }

    public void setIdvino(int idvino) {
        this.idvino = idvino;
    }
    
    
    @Override
    public boolean equals(Object other){
    
     if ( !(other instanceof AlmacenVinosPK) ) return false;
        
    
    
    
    return true;
    
    
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.idbodega;
        hash = 83 * hash + this.idvino;
        return hash;
    }
    
}
