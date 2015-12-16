/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

/**
 *
 * @author Jose
 * Encapsula la respuesta del servico que recupera las estadisticas del usuario.
 */
public class ResultEstadisticas {
    
    private String status;
    private EstadisticasUsuario estadisticas;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EstadisticasUsuario getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(EstadisticasUsuario estadisticas) {
        this.estadisticas = estadisticas;
    }
    
    
    
    
    
}
