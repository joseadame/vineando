/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.webservice.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import tempranillo.entidades.Checkin;
import tempranillo.entidades.LoginResult;
import tempranillo.entidades.UserAuth;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.login;
import tempranillo.modelo.GestionCheckins;
import tempranillo.modelo.GestionUsuarios;
import tempranillo.utils.CipherUtils;

/**
 *
 * @author Redbaron Clase que implementa el servicio REST para el login de la
 * API con autentificacion basica http.
 * No usar este metodo.
 */
@Controller
public class LoginController {

    
    
     //metodo de prueba para obtener una clase login serializada. 
    @RequestMapping(value="/loginUser",   produces = "application/json")
    public @ResponseBody UserAuth getLoginUser() {
        UserAuth l = new UserAuth();
        l.setPassword("12345");
        l.setUsername("oscarcasillas@gmail.com");
        l.setTimestamp(new java.sql.Timestamp(new Date().getTime()));
        return l;
    }
    
    
    
    
    //metodo para realizar login mediante REST. Devuelve un status de la operacion (OK/Error) y en caso de login correcto devuelve los datos del usuario.
    
    
    @RequestMapping(value="/loginBody", method= RequestMethod.PUT,  produces = "application/json")
    public @ResponseBody LoginResult loginUser(@RequestBody UserAuth loginData) {
          GestionUsuarios gestorUsuarios = new GestionUsuarios();
          LoginResult resultado;
          if (loginData != null)
          {
              String passwordrecuperado = gestorUsuarios.loginUsuarioREST(loginData.getUsername());
              Calendar c = Calendar.getInstance();
                c.setTime(new Date());
            c.add(Calendar.DAY_OF_MONTH, 1);;
            if (loginData.getPassword().equalsIgnoreCase(passwordrecuperado)
                    && (loginData.getTimestamp().before(c.getTime()))) {
                resultado = new LoginResult();
                  resultado.setStatus("OK");
                  Usuario user =gestorUsuarios.getUsuarioPorEmail(loginData.getUsername());
                  resultado.setUser(user);
                  try {
                      resultado.setKeyLogin(getKeyLogin(loginData));
                  } catch (NoSuchAlgorithmException ex) {
                      Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  return resultado;                 
              }else{                  
                  resultado= new LoginResult();
                  resultado.setStatus("Error");
                  return resultado;
              }
          }else{
              resultado = new LoginResult();
              resultado.setStatus("Error");
              return resultado;              
          }
    } 
    
    private String getKeyLogin(UserAuth loginData) throws NoSuchAlgorithmException
    {
        try {
            //return new String(CipherUtils.encrypt(loginData.getUsername()+"/"+loginData.getTimestamp()),"UTF-8");
            return new String(loginData.getUsername()+loginData.getTimestamp().toString());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    //Devuelve los datos del usuario a partir del mail proporcionado. No devuelve la contraseña por motivos de seguridad.
    
    @RequestMapping(value="/datalogin", method= RequestMethod.PUT,  produces = "application/json")
    public @ResponseBody LoginResult getDataUser(@RequestBody UserAuth loginData) {
          GestionUsuarios gestorUsuarios = new GestionUsuarios();
          LoginResult resultado;
          if (loginData != null)
          { 
              resultado= new LoginResult();
              try{
             Usuario user=gestorUsuarios.getUsuarioPorEmail(loginData.getUsername());
             user.setPassword(""); //vaciamos el password por motivos de seguridad.
            
              resultado.setStatus("OK");
              resultado.setUser(user);
              UserAuth userAuth = new UserAuth();
              userAuth.setUsername(loginData.getUsername());
              
              resultado.setKeyLogin(getKeyLogin(userAuth));
              return resultado;
              }
              catch(Exception e)
              {
                  
              resultado.setStatus("ERROR");
              return resultado;
              
              }
              
          }else{
              resultado = new LoginResult();
              resultado.setStatus("Error");
              return resultado;              
          }
    } 
    
    
    
    
    
    
    
    
}
