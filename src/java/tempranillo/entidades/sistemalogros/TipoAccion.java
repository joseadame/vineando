/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades.sistemalogros;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import tempranillo.modelo.GestionEventos;

/**
 * *
 * checkin=3 altavino=10 comentario=3 votopositivo=3 Seguir=5
 */
public enum TipoAccion{
    CHECKIN(),
    ALTAVINO(),
    COMENTARIOVINO(),
    VOTOPOSITIVO(),
    SEGUIMIENTO(),
    LISTA();
    
    
    private int _puntuacion;

    /**
     * Constructor del Enumerado. Este construtor obtiene la puntuación
     * informada en el fichero de propieades y la asigna al atributo privado
     * _puntuación que tendra asignado el Tipo.
     */
    private TipoAccion() {
        //Se busca la puntuacion en el properties de puntuaciones dependiendo de la accion de entrada.
        Properties prop = new Properties();
        InputStream is = this.getClass().getResourceAsStream("puntuacion.properties");
        try {
            prop.load(is);
        } catch (IOException ex) {
            Logger.getLogger(GestionEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
        _puntuacion = Integer.parseInt(prop.getProperty(this.name()));

    }

    public int getPuntuacion() {
        return _puntuacion;
    }
}
