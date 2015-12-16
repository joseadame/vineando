/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tempranillo.BD.GestionBD;

/**
 *
 * @author Oscar CN
 */
public class SessionManagement {
    protected static Session sesion;
    protected static Transaction trx;
    
    protected static void iniciarTransaccion() {
        sesion = GestionBD.getSessionFactory().openSession();
        trx = sesion.beginTransaction();
    }

    protected static void manejaExcepcion(HibernateException he) throws HibernateException {
            trx.rollback();
   
            sesion.close();
            throw new HibernateException(he.getMessage());
       
            
        
    }
     protected static void cerrarSesion(){
       sesion.close();
     }
}
