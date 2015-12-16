/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Administrador
 */
@Entity
public class Establecimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idestablecimiento;
    private String nombre;
    private String direccion;
    private String web;
    private String email;
    private String telefono;
    private String posiciongps;
    private String tipo;

    public int getIdestablecimiento() {
        return idestablecimiento;
    }

    public void setIdestablecimiento(int idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPosiciongps() {
        return posiciongps;
    }

    public void setPosiciongps(String  posiciongps) {
        this.posiciongps = posiciongps;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
  
    @Override
    public String toString() {
        return "tempranillo.entidades.Establecimiento[ id=" + idestablecimiento + " ]";
    }
    
}
