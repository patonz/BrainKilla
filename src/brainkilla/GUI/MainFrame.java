package brainkilla.GUI;

import brainkilla.Skeleton.FrameInterface;
import brainkilla.Skeleton.PanelInterface;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import util.AudioManager;
import util.Resource;

/**
 *
 * @author Lorenzo Gigli* 
 */
public class MainFrame extends JFrame implements FrameInterface
{

    private JPanel currentPanel = null;
    private Dimension screendim = null;
    private AudioManager audioManager;
    
    public MainFrame() throws HeadlessException 
    {
        super();
        this.setTitle("BrainKilla");
        screendim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screendim.width/4, screendim.height/4, 966, 628);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);        
        
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Resource.GetImage("icon.png")));
        
        audioManager = new AudioManager("dynamite.mp3");
        audioManager.loop();
    }

    @Override
    public AudioManager getAudioMf()
    {
        return(audioManager);
    }
    
    @Override
    public void nextPanel(JPanel panel) 
    {
        if(currentPanel != null){
            this.currentPanel.setVisible(false);
        }
        this.add(panel);
        this.currentPanel = panel;
        if (panel instanceof PanelInterface)
        {
            PanelInterface pi = (PanelInterface)panel;
            pi.setFrame(this);
        }
        this.repaint();
    }
    
}
