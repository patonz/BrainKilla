package brainkilla.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import util.Resource;

/**
 *
 * @author Lorenzo Gigli*
 */
public class GameMapPanel extends JPanel {

    private ImageIcon mapIcon;
    private Image mapImage;

    public GameMapPanel(String mapFilePath) {
        super();

        int width = 0;
        int height = 0;

        mapIcon = new ImageIcon(Resource.GetImage(mapFilePath));
        mapImage = mapIcon.getImage();

        try {

            BufferedImage mapImage = ImageIO.read(Resource.GetImage(mapFilePath));
            width = mapImage.getWidth();
            height = mapImage.getHeight();

        } catch (IOException ex) {

            System.out.println("Non riesco a leggere il file della mappa!");

        }

        setBounds(0, 0, width, height);
    }

    public void setMapImage(String imagePath) {

        this.mapImage = Toolkit.getDefaultToolkit().createImage((Resource.GetImage(imagePath)));
    }

    @Override
    public void paintComponent(Graphics g) {
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, this);
        }
    }
    
}
