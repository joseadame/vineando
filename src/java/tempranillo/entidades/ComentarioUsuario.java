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
public class ComentarioUsuario {
    
    String comentario;
    int puntuacion;
    String Usuario;
    String fecha;
    int idcomentario;
    int idusuario;
    int numerovotaciones;
    private String rutaAvatar;

    public int getNumerovotaciones() {
        return numerovotaciones;
    }

    public void setNumerovotaciones(int numerovotaciones) {
        this.numerovotaciones = numerovotaciones;
    }
    
    

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }

    
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setrutaAvatar(String rutaAvatar) {
        this.rutaAvatar=rutaAvatar;
    }
    
    public String getrutaAvatar(){
    
    return this.rutaAvatar;
    }

    
    
    
}
