/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades;

/**
 *
 * @author Redbaron
 */
public class LoginResult {
    
    private String status;
    private Usuario user;
    private String KeyLogin = null;

    /**
     * Get the value of KeyLogin
     *
     * @return the value of KeyLogin
     */
    public String getKeyLogin() {
        return KeyLogin;
    }

    /**
     * Set the value of KeyLogin
     *
     * @param KeyLogin new value of KeyLogin
     */
    public void setKeyLogin(String KeyLogin) {
        this.KeyLogin = KeyLogin;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
    
}
