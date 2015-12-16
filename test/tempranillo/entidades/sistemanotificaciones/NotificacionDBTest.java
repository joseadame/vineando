/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades.sistemanotificaciones;

import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Oscar CN
 */
public class NotificacionDBTest {
    
    public NotificacionDBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIdnotificacion method, of class NotificacionDB.
     */
    @Test
    public void testGetIdnotificacion() {
        System.out.println("getIdnotificacion");
        NotificacionDB instance = new NotificacionDB();
        int expResult = 0;
        int result = instance.getIdnotificacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdusuario method, of class NotificacionDB.
     */
    @Test
    public void testGetIdusuario() {
        System.out.println("getIdusuario");
        NotificacionDB instance = new NotificacionDB();
        int expResult = 0;
        int result = instance.getIdusuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdusuario method, of class NotificacionDB.
     */
    @Test
    public void testSetIdusuario() {
        System.out.println("setIdusuario");
        int idusuario = 0;
        NotificacionDB instance = new NotificacionDB();
        instance.setIdusuario(idusuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFecha method, of class NotificacionDB.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        NotificacionDB instance = new NotificacionDB();
        Date expResult = null;
        Date result = instance.getFecha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFecha method, of class NotificacionDB.
     */
    @Test
    public void testSetFecha() {
        System.out.println("setFecha");
        Date fecha = null;
        NotificacionDB instance = new NotificacionDB();
        instance.setFecha(fecha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotificacion method, of class NotificacionDB.
     */
    @Test
    public void testGetNotificacion() {
        System.out.println("getNotificacion");
        NotificacionDB instance = new NotificacionDB();
        String expResult = "";
        String result = instance.getNotificacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNotificacion method, of class NotificacionDB.
     */
    @Test
    public void testSetNotificacion() {
        System.out.println("setNotificacion");
        String notificacion = "";
        NotificacionDB instance = new NotificacionDB();
        instance.setNotificacion(notificacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXmlnotificacion method, of class NotificacionDB.
     */
    @Test
    public void testGetXmlnotificacion() {
        System.out.println("getXmlnotificacion");
        NotificacionDB instance = new NotificacionDB();
        String expResult = "";
        String result = instance.getXmlnotificacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXmlnotificacion method, of class NotificacionDB.
     */
    @Test
    public void testSetXmlnotificacion() {
        System.out.println("setXmlnotificacion");
        String xmlnotificacion = "";
        NotificacionDB instance = new NotificacionDB();
        instance.setXmlnotificacion(xmlnotificacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
