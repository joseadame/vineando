/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author joseadamefernandez
 * 
 * Tempranillo 2011
 * 
 * Esta clase almacena una entidad de bodega de un usuario.
 */
public class Bodega {
    
    private int idbodega;
    private String descripcion;
    private Date fecha;
    private int idusuario;
   // private Set listavinos = new HashSet(); //conjunto de vinos de la bodega, no pueden repetirse por eso hemos optado por un set.
    private Double notamedia;
    private String compartida;

    public String getCompartida() {
        return compartida;
    }

    public void setCompartida(String compartida) {
        this.compartida = compartida;
    }

    
    
    public Double getNotamedia() {
        return notamedia;
    }

    public void setNotamedia(Double notamedia) {
        this.notamedia = notamedia;
    }
   
    
   public Bodega(){
    
        //inicializamos el atributo fecha con la fecha del servidor.
        Date fechaactual = new Date();
        
        
        
        
        
        this.setFecha(fechaactual);
    
    }
   
  

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdbodega() {
        return idbodega;
    }

    private void setIdbodega(int idbodega) {
        this.idbodega = idbodega;
    }
    
  
    
}
