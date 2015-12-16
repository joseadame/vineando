/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Adame
 * Esta clase se encarga de guardar los datos del usuario tanto para la consulta en la BD
 * como para la insercción y visualización en las páginas.
 */
@XmlRootElement()
@XmlAccessorType(XmlAccessType.NONE)
public class Usuario implements Serializable {

    private String alias;
    private String password;
    private String email;
    private String nombre;
    private String apellidos;
    
    @XmlElement()
    private int id;
    private int puntuacion;
    private String rango;
    private String rutaAvatar;
    private String tipoperfil;
    private Date lastlogin;
    private Date fecharegistro;
    
    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    
    

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getTipoperfil() {
        return tipoperfil;
    }

    public void setTipoperfil(String tipoperfil) {
        this.tipoperfil = tipoperfil;
    }

    public String getRutaAvatar() {
        
       
        return rutaAvatar;
    }

    public void setRutaAvatar(String rutaAvatar) {
        this.rutaAvatar = rutaAvatar;
    }
    
    
    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
    

    //esto es obligatorio para hibernate
    public Usuario() {
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;


    }

    public String getNombre() {

        return nombre;

    }

    public void setApellidos(String Ape) {

        this.apellidos = Ape;


    }

    public String getApellidos() {


        return apellidos;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAlias(String Alias) {
        this.alias = Alias;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {

        return (this.alias + this.password + this.email);

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }
}
