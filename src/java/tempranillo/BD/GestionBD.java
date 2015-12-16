/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.BD;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Jose Adame
 */
public class GestionBD {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException he) {
            System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he);
            throw new ExceptionInInitializerError(he);
        } catch (Exception genericEx){  
            System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + genericEx);
            throw genericEx;        
        }
        
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }
}