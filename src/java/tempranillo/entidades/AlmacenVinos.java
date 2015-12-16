/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;



/**
 *
 * @author joseadamefernandez
 * 
 * Se encarga de almacenar las insercciones en la tabla Talmacenvinos
 */

public class AlmacenVinos  {
    
    
    AlmacenVinosPK claveprimaria;

    public AlmacenVinosPK getClaveprimaria() {
        return claveprimaria;
    }
    
    public int getidvino(){
    return this.claveprimaria.idvino;
    }

    public void setClaveprimaria(AlmacenVinosPK claveprimaria) {
        this.claveprimaria = claveprimaria;
    }
    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
   
    
    
}
