/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Adame
 */
@XmlRootElement()
@XmlAccessorType(XmlAccessType.NONE)
public class Comentario {
    int idcomentario;
    int idvino;
    int idusuario;
    Date fecha;
    String comentario;
    int puntuacion;
    private Set listaVotaciones = new HashSet();

    public Set getListaVotaciones() {
        return listaVotaciones;
    }

    public void setListaVotaciones(Set listaVotaciones) {
        this.listaVotaciones = listaVotaciones;
    }

    public Comentario(){};
    
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

   
    public void addVotacion(Votacion vot)
    {
    
    this.listaVotaciones.add(vot);
        
    }
    
    
    
    
    

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdcomentario() {
        return idcomentario;
    }

    private void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }

    
    public int getIdvino() {
        return idvino;
    }

    public void setIdvino(int idvino) {
        this.idvino = idvino;
    }
    
    
    
    
}
