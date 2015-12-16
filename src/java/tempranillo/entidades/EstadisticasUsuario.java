/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.math.BigInteger;

/**
 *
 * @author Jose
 * Clase que almacena las estadisticas del usuario para mostrarlas en la pantalla del perfil de las aplicaciones moviles.
 */
public class EstadisticasUsuario {
    
    private int numeroCheckins;
    private long numeroVinosWishList;
    private BigInteger numeroLogros;

    public int getNumeroCheckins() {
        return numeroCheckins;
    }

    public void setNumeroCheckins(int numeroCheckins) {
        this.numeroCheckins = numeroCheckins;
    }

    public long getNumeroVinosWishList() {
        return numeroVinosWishList;
    }

    public void setNumeroVinosWishList(long numeroVinosWishList) {
        this.numeroVinosWishList = numeroVinosWishList;
    }

    public BigInteger getNumeroLogros() {
        return numeroLogros;
    }

    public void setNumeroLogros(BigInteger numeroLogros) {
        this.numeroLogros = numeroLogros;
    }
    
    
    
    
}
