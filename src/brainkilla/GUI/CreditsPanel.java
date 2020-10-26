package brainkilla.GUI;

import brainkilla.Skeleton.FrameInterface;
import brainkilla.Skeleton.PanelInterface;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import util.AudioManager;
import util.Resource;

/**
 *
 * @author Lorenzo Gigli*
 */
public class CreditsPanel extends DefaultPanel implements PanelInterface
{
 
    private FrameInterface frame = null;
    private Image backgroundImage = Toolkit.getDefaultToolkit().createImage(Resource.GetImage("backgroundcredits_lights.jpg"));
    private AudioManager audioManagerMainFrame;
    private AudioManager audioManagerCreditsPanel;
    
    public CreditsPanel()
    {    
        super();
        audioManagerCreditsPanel = new AudioManager("tsrh.mp3");
        audioManagerCreditsPanel.play();   
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                audioManagerCreditsPanel.stop();
                audioManagerMainFrame = frame.getAudioMf(); 
                audioManagerMainFrame.loop();
                frame.nextPanel(new MainPanel());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
   
    @Override
    public void setFrame(FrameInterface frame) {
        this.frame = frame;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if (backgroundImage!=null) 
        g.drawImage(backgroundImage,0,0,this);
    }

}
