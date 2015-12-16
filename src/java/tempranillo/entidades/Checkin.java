/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Adame En esta clase almacenaremos los checkins de los usuarios.
 */
@XmlRootElement()
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Checkin implements Serializable {

    private int idcheckin;
    private int idusuario;
    private int idvino;
    private Date fecha;
    private String comentario;
    private Double nota;
    private String relacion;
    private String ciudad;
    private String establecimiento;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Checkin() {
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

    public int getIdcheckin() {
        return idcheckin;
    }

    private void setIdcheckin(int idcheckin) {
        this.idcheckin = idcheckin;
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
}
