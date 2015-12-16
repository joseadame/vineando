package tempranillo.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;


import tempranillo.entidades.Bodega;
import tempranillo.entidades.BusquedaVino;
import tempranillo.entidades.Password;
import tempranillo.entidades.Usuario;
import tempranillo.modelo.FileUpload;
import tempranillo.modelo.GestionBodegas;
import tempranillo.modelo.GestionUsuarios;
import tempranillo.utils.Configuracion;
import tempranillo.utils.ImageUtils;

public class FileUploadController extends AbstractCommandController {

    @Override
    protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        HttpSession session = request.getSession();
        Usuario user = (Usuario)session.getAttribute("usuarioEnSession");
    GestionBodegas gesBodegas= null;
    ModelAndView _modelAndView = null;
     try
     {  
         
        FileUpload file = (FileUpload) command;
        MultipartFile multipartFile = file.getFile();
  	 
        if (multipartFile != null) {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            //Obtenemos el nombre del fichero y generamos un nombre aleatorio más su extensión.
            String fileName = multipartFile.getOriginalFilename();
            String _uuidFichero = UUID.randomUUID().toString().replace("-","").substring(1,9);
            String extension=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()); 
            
             
            inputStream = multipartFile.getInputStream();
            //TODO: La ruta del fichero debe ponerse a nivel de configuración.
            fileName = String.format("%s%smedia%susuarios%s%s_%s.%s",
                    request.getRealPath(""),
                    File.separator,File.separator,File.separator,
                    user.getId(), _uuidFichero, extension);
            outputStream = new FileOutputStream(fileName);
            int readBytes = 0;
            byte[] buffer = new byte[10000];
            while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                outputStream.write(buffer, 0, readBytes);
            }
            outputStream.close();
           
            //Patrón para reducir el tamaño de una imagen al 50% y guardarla en un fichero.
            inputStream = multipartFile.getInputStream();
            BufferedImage originalImage = ImageIO.read(inputStream);
                                    
            BufferedImage resizeImageHintJpg = ImageUtils.resizeImageToMaxSize(originalImage,150,150);
            fileName = String.format("%s%smedia%susuarios%slow_%s_%s.%s", 
                    request.getRealPath(""),
                    File.separator,File.separator,File.separator,
                    user.getId(),
                    _uuidFichero,
                    extension);
            outputStream = new FileOutputStream(fileName);
            ImageIO.write(resizeImageHintJpg, extension, outputStream); 
           
            inputStream.close();
            outputStream.close();
            
            //Modifico el avatar en BD mediante el modelo.
            GestionUsuarios gestionUsuarios = new GestionUsuarios();
            gesBodegas=new GestionBodegas();
            //TODO: La ruta web /media/usuario debe ponerse a nivel de configuración.
            
            Configuracion config = new Configuracion();
            String url = config.getURL();
            
            String _rutaWebAvatar = String.format(url+"%smedia%susuarios%s%s_%s.%s", 
                    File.separator,File.separator,File.separator,
                    user.getId(),
                    _uuidFichero, 
                    extension);
            gestionUsuarios.modificarAvatarUsuario(user.getId(), _rutaWebAvatar);
            //Modifico la ruta del avatar del usuario en sesión.
            user.setRutaAvatar(_rutaWebAvatar);
            session.setAttribute("usuarioEnSession",user);
           List<Bodega> listabodegas=gesBodegas.listarBodegasUsuario(user.getId());
            Map model = new HashMap();
                model.put("error","");
                model.put("exito", "¡Bien, Datos actualizados con exito!");
                model.put("errorpassword","");
                model.put("exitopassword","");
                model.put("listabodegas",listabodegas);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",user);
                model.put("nuevaPassword",new Password());              
                 _modelAndView = new ModelAndView("perfilUsuario", model); 
        }
         }
    catch(HibernateException he){
        gesBodegas=new GestionBodegas();
        List<Bodega> listabodegas=gesBodegas.listarBodegasUsuario(user.getId());
                Map model = new HashMap();
                model.put("error",he.getLocalizedMessage());
                model.put("exito","");
                model.put("errorpassword","");
                model.put("exitopassword","");
                model.put("listabodegas", listabodegas);
                model.put("busquedavino",new BusquedaVino());
                model.put("usuario",user);
                model.put("nuevaPassword",new Password());
              _modelAndView = new ModelAndView("perfilUsuario", model);
       }
         return _modelAndView; 
    }
}