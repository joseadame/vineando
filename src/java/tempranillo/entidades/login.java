/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

import java.io.Serializable;

/**
 *
 * @author joseadamefernandez
 */
public class login implements Serializable{
    
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
