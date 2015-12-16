/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

/**
 *
 * @author Jose
 * Guarda los datos de un comentario que le llegan al webservice.
 */
public class ComentarioWS {
    
    private int idusuario;
    private String comentario;
    

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
