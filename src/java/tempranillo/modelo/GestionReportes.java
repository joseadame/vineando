/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import org.hibernate.HibernateException;
import tempranillo.entidades.Reporte;

/**
 *
 * @author Jose Adame
 */
public class GestionReportes extends tempranillo.utils.SessionManagement {

    public void reportarError(Reporte repor) {
        try {
            iniciarTransaccion();
            sesion.save(repor);//guardamos el usuario en la tabla TUsuarios
            trx.commit();

        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
    }
}
