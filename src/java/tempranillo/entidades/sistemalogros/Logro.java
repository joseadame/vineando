/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades.sistemalogros;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import tempranillo.utils.SessionManagement;

/**
 *
 * @author Jose Adame
 * Esta clase implementa un logro de la red social.
 */
@XmlRootElement(name = "Logro")
@XmlAccessorType(XmlAccessType.NONE)
public class Logro extends SessionManagement {
    
    private int idlogro;
    private String nombre;
    private String descripcion;
    private int puntuacion;

    public Logro(){
    }
    
    public Logro(int idLogro){
        this.idlogro = idLogro;
    }
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlElement(required=true, name="IdLogro")
    public int getIdlogro() {
        return idlogro;
    }

    private void setIdlogro(int idlogro) {
        this.idlogro = idlogro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    public static Logro getLogroFromBD(int idLogro){
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("FROM Logro v WHERE v.idlogro=:idlogro");
            query.setParameter("idlogro", idLogro);
            return ((Logro) query.uniqueResult());
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
   
    
    }
    
    /* Devuelve la lista de logros conseguidos por un usuario
     * Parametro de entrada: id del usuario
     * Retorna: Lista de logros
     */
    public static List<Logro> getLogrosUsuario(int idusuario){
    
    try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select u.fecha, l From Logro as  l, UsuarioLogros as u where l.idlogro=u.idlogro and u.idusuario=:idusuario ORDER BY u.fecha");
            query.setParameter("idusuario", idusuario);
            return  query.list();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
    finally{
        cerrarSesion();
    }
        return null;
    
    
    }
    
    
    /* Devuelve la lista de logros conseguidos por un usuario adaptados para la api.
     * Parametro de entrada: id del usuario
     * Retorna: Lista de logros
     */
    public static List<Logro> getLogrosUsuarioAPI(int idusuario){
    
    try {
            iniciarTransaccion();
            Query query = sesion.createQuery("Select l From Logro l, UsuarioLogros u where l.idlogro=u.idlogro and u.idusuario=:idusuario ORDER BY u.fecha");
            query.setParameter("idusuario", idusuario);
            return  query.list();
        } catch (HibernateException he) {
            return null;
        }
        finally{
    
        cerrarSesion();
        
    }
    
    
    }
    
    
    
   
    
    
}
