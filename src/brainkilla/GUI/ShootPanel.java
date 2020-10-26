package brainkilla.GUI;

import brainkilla.Engine.ActiveEntity;
import brainkilla.Engine.GameLevel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import util.AudioManager;
import util.Resource;

/**
 *
 * @author Lorenzo Gigli*
 */
public class ShootPanel extends JPanel implements ActionListener {

    private GameLevel gameLevel = null;
    private ActiveEntity activeEntity = null;
    private Timer timer = null;
    private Image shootImage = Toolkit.getDefaultToolkit().createImage(Resource.GetImage("shootgif.gif"));
    private AudioManager shootAudioManager = new AudioManager("shoot_sound.wav");
    private int displaceX = 0;
    private int displaceY = 0;

    public ShootPanel(GameLevel gameLevel, ActiveEntity activeEntity) {
        this.gameLevel = gameLevel;
        this.activeEntity = activeEntity;
        this.displaceX = this.gameLevel.getMapPanel().getWidth() / 20;
        this.displaceY = this.gameLevel.getMapPanel().getHeight() / 50;
        this.setVisible(false);

        timer = new Timer(5, this);
        timer.start();
    }

    public void setShootImage(String shootImagePath) {
        this.shootImage = Toolkit.getDefaultToolkit().createImage(Resource.GetImage("fart_shootgif.gif"));
    }

    public void setShootSound(String shootSoundPath) {
        this.shootAudioManager = new AudioManager(shootSoundPath);
    }
    
    private void loopSound() {
        
        shootAudioManager.loop();
    }
    
    public void playSound() {
        
        shootAudioManager.play();
    }
    
    public void stopSound() {
        
        this.shootAudioManager.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
        if (shootImage != null) {
            g.drawImage(shootImage, 0, 0, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Double pX = displaceX + this.activeEntity.getCenteredX() * (0.2);
        Double pY = displaceY + this.activeEntity.getCenteredY() * (0.2);
        this.setBounds((pX.intValue()) - 91 + gameLevel.getMapAdjustmentX(), (pY.intValue()) - 87 + gameLevel.getMapAdjustmentY(), 200, 200);
        
        if (activeEntity.getShootStatus() == true) {
            this.setVisible(true);
            this.loopSound();
        }
        if (activeEntity.getShootStatus() == false) {
            this.setVisible(false);
            this.stopSound();
        }

    }
}
