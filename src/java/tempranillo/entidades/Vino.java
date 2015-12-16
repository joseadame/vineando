/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.io.Serializable;
import java.util.Date;

import java.util.HashSet;

import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Identifica un objeto del tip vino
 * @author Jose Adame
 */

@XmlRootElement()
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Vino implements Serializable {

    private String nombre;
    private String tipovino;
    private String anio;
    private String variedad;
    private String zona;
    private String pais;
    private String barcode;
    private String usuario;
    private int idvino;
    private int idusuario;
    private Double notamedia;
    private int numerovotaciones;
    private Date fechaalta;
    private String bodega;
    private String status;
    private String rutaimagen;
    private String comentariocata;

    public String getComentariocata() {
        return comentariocata;
    }

    public void setComentariocata(String comentariocata) {
        this.comentariocata = comentariocata;
    }

    public String getRutaimagen() {
        return rutaimagen;
    }

    public void setRutaimagen(String rutaimagen) {
        this.rutaimagen = rutaimagen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
  
    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }
    
    
    
    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }
    
   

    public int getNumerovotaciones() {
        return numerovotaciones;
    }

    public void setNumerovotaciones(int numerovotaciones) {
        this.numerovotaciones = numerovotaciones;
    }

    
     
    public Double getNotamedia() {
        return notamedia;
    }

    public void setNotamedia(Double notamedia) {
        this.notamedia = notamedia;
    }
    
    
   
   
   
    
    public Vino()
    {
    };
    
    public Vino(int idVino)
    {
        this.idvino=idVino;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    @XmlElement()
    public int getIdvino() {
        return idvino;
    }

    private void setIdvino(int idvino) {
        this.idvino = idvino;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipovino() {
        return tipovino;
    }

    public void setTipovino(String tipovino) {
        this.tipovino = tipovino;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
   
}
