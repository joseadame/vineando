/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;
/**
 *
 * @author Oscar CN
 */

public class Media {
       
    private Integer idmedia;
    private String rutaavatar;
    private String tipo;  
    private Integer idvino;
    
    public Media() {
    }

    public Integer getIdvino() {
        return idvino;
    }

    public void setIdvino(Integer idvino) {
        this.idvino = idvino;
    }
    public Integer getIdmedia() {
        return idmedia;
    }

    private void setIdmedia(Integer idmedia) {
        this.idmedia = idmedia;
    }

    public String getRutaavatar() {
        return rutaavatar;
    }

    public void setRutaavatar(String rutaavatar) {
        this.rutaavatar = rutaavatar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    

    

    
    
}
