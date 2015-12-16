/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Clase para los metodos ligados al manejo de imagenes.
 * @author Oscar CN
 */
public final class ImageUtils {
  
  private static double obtenerPorcentajeAjuste(int originalHeight, int originalWidht, int maxHeight, int maxWidht){
      double percentH = originalHeight/maxHeight;
      double percentW = originalWidht/maxWidht;
      return ((percentH>percentW)?percentH:percentW);
  }  
    
  public static BufferedImage resizeImageToMaxSize(BufferedImage originalImage, int maxHeight, int maxWidht){
     return resizeImage(originalImage, obtenerPorcentajeAjuste(originalImage.getHeight(), originalImage.getWidth(), maxHeight, maxWidht));
  }
    
  public static BufferedImage resizeImage(BufferedImage originalImage, double percent){
      return resizeImage(originalImage, (int)(originalImage.getHeight()*percent),(int)(originalImage.getWidth()*percent));
  }
    
  public static BufferedImage resizeImage(BufferedImage originalImage, int height, int width){
     int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
     	BufferedImage resizedImage = new BufferedImage(height,width, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, width, height, null);
	g.dispose();	
	g.setComposite(AlphaComposite.Src);
 
	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
 
	return resizedImage;
    }
}
