package util;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Markus Aurich
 */
public final class Resource {
    
    public static URL GetAudio(String filename) {
        
        URL audio = null;
        
        try {
            
            audio = new File("audio\\" + filename).toURL();
            
        } catch (MalformedURLException ex) {
            
            Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
            
        }        
        
        if (audio == null)
            System.out.println("Audio resource /res/audio/" + filename + " non trovato");
        
        return audio;
    }
    
    public static URL GetImage(String filename) {
        
        URL image = Resource.class.getResource("/res/images/" + filename);
        
        if (image == null)
            System.out.println("Image resource /res/images/" + filename + " non trovato");        
        
        return image;
    }
    
    public static InputStream GetFileAsInputStream(String filename) {
    
        InputStream fileInputStream = Resource.class.getResourceAsStream("/res/" + filename);
        
        return fileInputStream;
    }
}
