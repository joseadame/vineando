/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades.sistemanotificaciones;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.entidades.sistemalogros.Logro;

/**
 *
 * @author Oscar CN
 */
@XmlRootElement(name = "Notificacion")
@XmlSeeAlso({Vino.class, Usuario.class, Logro.class, Notificacion.TipoNotificacion.class})
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Notificacion implements Serializable {

// <editor-fold defaultstate="collapsed" desc="Tipo enumerado de Notificación">
    public enum TipoNotificacion implements Serializable {

        LOGROCONSEGUIDO,
        RANGOALCANZADO,
        ALTAVINO,
        ACEPTARSEGUIMIENTO,
        SEGUIMIENTOAMIGO,
        CHECKIN;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Constructores">
    public Notificacion() {
    }

    public Notificacion(TipoNotificacion tipoNotificacion, Usuario usuarioOriginal) {
        this.tipoNotificacion = tipoNotificacion;
        this.usuarioOriginal = usuarioOriginal;
    }

    public Notificacion(TipoNotificacion tipoNotificacion, Logro logro, Usuario usuarioOriginal) {
        this.tipoNotificacion = tipoNotificacion;
        this.logro = logro;
        this.usuarioOriginal = usuarioOriginal;
    }

    public Notificacion(TipoNotificacion tipoNotificacion, Vino vino, Usuario usuarioOriginal) {
        this.tipoNotificacion = tipoNotificacion;
        this.vino = vino;
        this.usuarioOriginal = usuarioOriginal;
    }

    public Notificacion(TipoNotificacion tipoNotificacion, Usuario usuarioOriginal, Usuario usuarioDestino) {
        this.tipoNotificacion = tipoNotificacion;
        this.usuarioOriginal = usuarioOriginal;
        this.usuarioDestino = usuarioDestino;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Propiedades">
    private TipoNotificacion tipoNotificacion;
    private Logro logro;
    private Vino vino;
    private Usuario usuarioOriginal;
    private Usuario usuarioDestino;

    /**
     * Get the value of tipoNotificacion
     *
     * @return the value of tipoNotificacion
     */
    @XmlElement()
    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    /**
     * Set the value of tipoNotificacion
     *
     * @param tipoNotificacion new value of tipoNotificacion
     */
    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    /**
     * Get the value of logro
     *
     * @return the value of logro
     */
    @XmlElementRef(name = "logrox")
    public Logro getLogro() {
        return logro;
    }

    /**
     * Set the value of logro
     *
     * @param logro new value of logro
     */
    public void setLogro(Logro logro) {
        this.logro = logro;
    }

    /**
     * Get the value of vino
     *
     * @return the value of vino
     */
    @XmlElementRef()
    public Vino getVino() {
        return vino;
    }

    /**
     * Set the value of vino
     *
     * @param vino new value of vino
     */
    public void setVino(Vino vino) {
        this.vino = vino;
    }

    /**
     * Get the value of usuarioOriginal
     *
     * @return the value of usuarioOriginal
     */
    @XmlElement()
    public Usuario getUsuarioOriginal() {
        return usuarioOriginal;
    }

    /**
     * Set the value of usuarioOriginal
     *
     * @param usuarioOriginal new value of usuarioOriginal
     */
    public void setUsuarioOriginal(Usuario usuarioOriginal) {
        this.usuarioOriginal = usuarioOriginal;
    }

    /**
     * Get the value of usuarioDestino
     *
     * @return the value of usuarioDestino
     */
    @XmlElement()
    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    /**
     * Set the value of usuarioDestino
     *
     * @param usuarioDestino new value of usuarioDestino
     */
    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Metodos">
    public String SerializeToXml() {
        return SerializeToXml(true);
    }

    public String SerializeToXml(boolean idented) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Notificacion.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, idented);

            OutputStream output = new OutputStream() {
                private StringBuilder string = new StringBuilder();

                @Override
                public void write(int b) throws IOException {
                    this.string.append((char) b);
                }

                @Override
                public String toString() {
                    return this.string.toString();
                }
            };
            marshaller.marshal(this, output);
            return output.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(Notificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
