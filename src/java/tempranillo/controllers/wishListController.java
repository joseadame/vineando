/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tempranillo.entidades.Usuario;
import tempranillo.entidades.WishList;
import tempranillo.modelo.GestionBodegas;

/**
 *
 * @author Jose Adame
 */

@Controller
public class wishListController {
    
 private   GestionBodegas gesbodegas;
    
  @RequestMapping("wishlist.htm")
  public @ResponseBody String altaWishList(HttpServletRequest hsr, HttpServletResponse hsr1)
  {
        HttpSession session = hsr.getSession();
        Usuario userSesion = (Usuario) session.getAttribute("usuarioEnSession");
        String idvino = hsr.getParameter("idvino");
        gesbodegas= new GestionBodegas();
        try{
        if(gesbodegas.altaVinoEnWishList(userSesion.getId(),Integer.parseInt(idvino))){
            
            return "ok";
        
        
        }
        else
        {
            return "ko";
        }
        }
        catch (HibernateException e)
        {
        
            return "ko";
        
        }
      
         
  }
    
}
