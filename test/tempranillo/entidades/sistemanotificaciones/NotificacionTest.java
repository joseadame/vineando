/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades.sistemanotificaciones;

import org.junit.*;
import static org.junit.Assert.*;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.entidades.sistemanotificaciones.Notificacion.TipoNotificacion;

/**
 *
 * @author Oscar CN
 */
public class NotificacionTest {

    public NotificacionTest() {
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
     * Test of getTipoNotificacion method, of class Notificacion.
     */
    @Test
    public void testGetTipoNotificacion() {
        System.out.println("getTipoNotificacion");
        Notificacion instance = new Notificacion();
        TipoNotificacion expResult = null;
        TipoNotificacion result = instance.getTipoNotificacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipoNotificacion method, of class Notificacion.
     */
    @Test
    public void testSetTipoNotificacion() {
        System.out.println("setTipoNotificacion");
        TipoNotificacion tipoNotificacion = null;
        Notificacion instance = new Notificacion();
        instance.setTipoNotificacion(tipoNotificacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogro method, of class Notificacion.
     */
    @Test
    public void testGetLogro() {
        System.out.println("getLogro");
        Notificacion instance = new Notificacion();
        Logro expResult = null;
        Logro result = instance.getLogro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogro method, of class Notificacion.
     */
    @Test
    public void testSetLogro() {
        System.out.println("setLogro");
        Logro logro = null;
        Notificacion instance = new Notificacion();
        instance.setLogro(logro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVino method, of class Notificacion.
     */
    @Test
    public void testGetVino() {
        System.out.println("getVino");
        Notificacion instance = new Notificacion();
        Vino expResult = null;
        Vino result = instance.getVino();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVino method, of class Notificacion.
     */
    @Test
    public void testSetVino() {
        System.out.println("setVino");
        Vino vino = null;
        Notificacion instance = new Notificacion();
        instance.setVino(vino);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarioOriginal method, of class Notificacion.
     */
    @Test
    public void testGetUsuarioOriginal() {
        System.out.println("getUsuarioOriginal");
        Notificacion instance = new Notificacion();
        Usuario expResult = null;
        Usuario result = instance.getUsuarioOriginal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsuarioOriginal method, of class Notificacion.
     */
    @Test
    public void testSetUsuarioOriginal() {
        System.out.println("setUsuarioOriginal");
        Usuario usuarioOriginal = null;
        Notificacion instance = new Notificacion();
        instance.setUsuarioOriginal(usuarioOriginal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarioDestino method, of class Notificacion.
     */
    @Test
    public void testGetUsuarioDestino() {
        System.out.println("getUsuarioDestino");
        Notificacion instance = new Notificacion();
        Usuario expResult = null;
        Usuario result = instance.getUsuarioDestino();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsuarioDestino method, of class Notificacion.
     */
    @Test
    public void testSetUsuarioDestino() {
        System.out.println("setUsuarioDestino");
        Usuario usuarioDestino = null;
        Notificacion instance = new Notificacion();
        instance.setUsuarioDestino(usuarioDestino);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SerializeToXml method, of class Notificacion.
     */
    @Test
    public void testSerializeToXml_0args() {
        System.out.println("SerializeToXml");
        Vino v = new Vino();
        v.setAnio("1932");
        v.setNombre("Vino de Prueba");
        v.setIdusuario(12);

        Usuario u = new Usuario();
        
        u.setNombre("UsuarioPrueba");
        u.setAlias("TestUser");
        u.setEmail("correo@gmail.com");

        Notificacion instance = new Notificacion();
        instance.setTipoNotificacion(TipoNotificacion.CHECKIN);
        instance.setVino(v);
        instance.setUsuarioOriginal(u);

        String expResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Notificacion><tipoNotificacion>CHECKIN</tipoNotificacion><usuarioOriginal><alias>TestUser</alias><email>correo@gmail.com</email><nombre>UsuarioPrueba</nombre><puntuacion>0</puntuacion></usuarioOriginal><vino><anio>1932</anio><idusuario>12</idusuario><nombre>Vino de Prueba</nombre><numerovotaciones>0</numerovotaciones></vino></Notificacion>";
        String result = instance.SerializeToXml(false);
        assertEquals(expResult, result);
    }

    /**
     * Test of SerializeToXml method, of class Notificacion.
     */
    @Test
    public void testSerializeToXml_boolean() {
        System.out.println("SerializeToXml");
        Vino v = new Vino(33);
        v.setAnio("1932");
        v.setNombre("Vino de Prueba");
        v.setIdusuario(12);

        Usuario u = new Usuario();
        u.setId(13);
        u.setNombre("UsuarioPrueba");
        u.setAlias("TestUser");
        u.setEmail("correo@gmail.com");

        Notificacion instance = new Notificacion();        
        instance.setTipoNotificacion(TipoNotificacion.CHECKIN);
        instance.setVino(v);
        instance.setUsuarioOriginal(u);

        String expResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Notificacion><tipoNotificacion>CHECKIN</tipoNotificacion><usuarioOriginal><id>13</id></usuarioOriginal><vino><idvino>33</idvino></vino></Notificacion>";
        String result = instance.SerializeToXml(false);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of SerializeToXml method, of class Notificacion.
     */
    @Test
    public void testSerializeToXml_Logro() {
        System.out.println("SerializeToXml");
        
        Logro l = new Logro(1);
                
        Usuario u = new Usuario();
        u.setId(13);

        Notificacion instance = new Notificacion();        
        instance.setTipoNotificacion(TipoNotificacion.LOGROCONSEGUIDO);        
        instance.setUsuarioOriginal(u);
        instance.setLogro(l);

        String expResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Notificacion><Logro><idlogro>1</idlogro></Logro><tipoNotificacion>LOGROCONSEGUIDO</tipoNotificacion><usuarioOriginal><id>13</id></usuarioOriginal></Notificacion>";
        String result = instance.SerializeToXml(false);
        assertEquals(expResult, result);
    }
}
