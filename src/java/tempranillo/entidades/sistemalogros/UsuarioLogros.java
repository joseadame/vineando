/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades.sistemalogros;

import java.util.Date;

/**
 *
 * @author Jose Adame
 * Esta clase implementa la relacion n-m entres los usuarios y los logros
 */
public class UsuarioLogros {;
    
    public int id;
    public int idusuario;
    public int idlogro;
    Date fecha;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdlogro() {
        return idlogro;
    }

    public void setIdlogro(int idlogro) {
        this.idlogro = idlogro;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
    
    
}
