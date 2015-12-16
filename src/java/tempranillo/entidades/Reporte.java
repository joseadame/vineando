/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

/**
 *
 * @author Jose Adame
 */
public class Reporte {
    
   private int idreporte;
   private int idvino;
   private int idusuario;
   private String motivo;
   private String descripcion;
   
   
   public Reporte(){};

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdreporte() {
        return idreporte;
    }

    private void setIdreporte(int idreporte) {
        this.idreporte = idreporte;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
   
   
    
    
}
