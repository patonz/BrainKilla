package brainkilla.GUI;

import brainkilla.Skeleton.FrameInterface;
import brainkilla.Skeleton.PanelInterface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import util.AudioManager;
import util.Resource;

/**
 *
 * @author Lorenzo Gigli*
 */
public class MainPanel extends DefaultPanel implements PanelInterface 
{
    
    private DefaultButton buttonNewGame;
    private DefaultButton buttonHighScore;
    private DefaultButton buttonCredits;
    private FrameInterface frame = null;
    private Image backgroundImage = Toolkit.getDefaultToolkit().createImage(Resource.GetImage("backgroundmp2.jpg"));
    
    public MainPanel()
    {
        super();   
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
             
        buttonNewGame = new DefaultButton("buttnewgame.jpg");
        buttonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
                buttonNewGame.addActionListener(
                new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        frame.nextPanel(new NewGamePanel());
                    }
                });
        
        buttonHighScore = new DefaultButton("butthighscore.jpg");
        buttonHighScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonHighScore.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        AudioManager audioManager = frame.getAudioMf(); 
                        audioManager.pause();
                        frame.nextPanel(new HighScorePanel());
                    }
                });
        
        buttonCredits = new DefaultButton("buttcredits.jpg");
        buttonCredits.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCredits.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        AudioManager audioManager = frame.getAudioMf(); 
                        audioManager.pause();
                        frame.nextPanel(new CreditsPanel());
                    }
                });
        
        this.add(Box.createRigidArea(new Dimension(0, 220)));
        this.add(buttonNewGame);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(buttonHighScore);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(buttonCredits);
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        if (backgroundImage!=null) 
        g.drawImage(backgroundImage,0,0,this);
    }
    
    @Override
    public void setFrame(FrameInterface frame) 
    {
        this.frame = frame;
    }
   
}
