/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import org.junit.*;
import static org.junit.Assert.*;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.Vino;
import tempranillo.entidades.sistemanotificaciones.Notificacion;

/**
 *
 * @author Oscar CN
 */
public class GestionNotificacionTest {
    
    public GestionNotificacionTest() {
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
     * Test of createNotificacion method, of class GestionNotificacion.
     */
    @Test
    public void testCreateNotificacion() {
        System.out.println("createNotificacion");
        Vino v = new Vino();
        v.setAnio("1932");
        v.setNombre("Vino de Prueba");
        v.setIdusuario(12);
        
        Usuario u = new Usuario();
        u.setNombre("UsuarioPrueba");
        u.setAlias("TestUser");
        u.setEmail("correo@gmail.com");
        
        Notificacion notificacion = new Notificacion();
        notificacion.setTipoNotificacion(Notificacion.TipoNotificacion.CHECKIN);
        notificacion.setVino(v);
        notificacion.setUsuarioOriginal(u);

        GestionNotificacion instance = new GestionNotificacion();
        int expResult = 0;
        int result = instance.createNotificacion(notificacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
