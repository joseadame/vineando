/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author alumnos
 */
public class UsuarioTest {
    
    public UsuarioTest() {
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
     * Test of getFecharegistro method, of class Usuario.
     */
    @Test
    public void testGetFecharegistro() {
        System.out.println("getFecharegistro");
        Usuario instance = new Usuario();
        Date expResult = null;
        Date result = instance.getFecharegistro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFecharegistro method, of class Usuario.
     */
    @Test
    public void testSetFecharegistro() {
        System.out.println("setFecharegistro");
        Date fecharegistro = null;
        Usuario instance = new Usuario();
        instance.setFecharegistro(fecharegistro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastlogin method, of class Usuario.
     */
    @Test
    public void testGetLastlogin() {
        System.out.println("getLastlogin");
        Usuario instance = new Usuario();
        Date expResult = null;
        Date result = instance.getLastlogin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastlogin method, of class Usuario.
     */
    @Test
    public void testSetLastlogin() {
        System.out.println("setLastlogin");
        Date lastlogin = null;
        Usuario instance = new Usuario();
        instance.setLastlogin(lastlogin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipoperfil method, of class Usuario.
     */
    @Test
    public void testGetTipoperfil() {
        System.out.println("getTipoperfil");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getTipoperfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipoperfil method, of class Usuario.
     */
    @Test
    public void testSetTipoperfil() {
        System.out.println("setTipoperfil");
        String tipoperfil = "";
        Usuario instance = new Usuario();
        instance.setTipoperfil(tipoperfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRutaAvatar method, of class Usuario.
     */
    @Test
    public void testGetRutaAvatar() {
        System.out.println("getRutaAvatar");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getRutaAvatar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRutaAvatar method, of class Usuario.
     */
    @Test
    public void testSetRutaAvatar() {
        System.out.println("setRutaAvatar");
        String rutaAvatar = "";
        Usuario instance = new Usuario();
        instance.setRutaAvatar(rutaAvatar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPuntuacion method, of class Usuario.
     */
    @Test
    public void testGetPuntuacion() {
        System.out.println("getPuntuacion");
        Usuario instance = new Usuario();
        int expResult = 0;
        int result = instance.getPuntuacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPuntuacion method, of class Usuario.
     */
    @Test
    public void testSetPuntuacion() {
        System.out.println("setPuntuacion");
        int puntuacion = 0;
        Usuario instance = new Usuario();
        instance.setPuntuacion(puntuacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRango method, of class Usuario.
     */
    @Test
    public void testGetRango() {
        System.out.println("getRango");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getRango();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRango method, of class Usuario.
     */
    @Test
    public void testSetRango() {
        System.out.println("setRango");
        String rango = "";
        Usuario instance = new Usuario();
        instance.setRango(rango);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Usuario.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Usuario instance = new Usuario();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Usuario.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setApellidos method, of class Usuario.
     */
    @Test
    public void testSetApellidos() {
        System.out.println("setApellidos");
        String Ape = "";
        Usuario instance = new Usuario();
        instance.setApellidos(Ape);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApellidos method, of class Usuario.
     */
    @Test
    public void testGetApellidos() {
        System.out.println("getApellidos");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getApellidos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Usuario.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Usuario.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Usuario instance = new Usuario();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAlias method, of class Usuario.
     */
    @Test
    public void testSetAlias() {
        System.out.println("setAlias");
        String Alias = "";
        Usuario instance = new Usuario();
        instance.setAlias(Alias);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class Usuario.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        Usuario instance = new Usuario();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlias method, of class Usuario.
     */
    @Test
    public void testGetAlias() {
        System.out.println("getAlias");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getAlias();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class Usuario.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Usuario.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Usuario.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Usuario instance = new Usuario();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
