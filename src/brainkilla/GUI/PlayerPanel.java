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
import util.Resource;

/**
 *
 * @author Lorenzo Gigli*
 */
public class PlayerPanel extends JPanel implements ActionListener {

    private GameLevel gameLevel = null;
    private ActiveEntity activeEntity = null;
    private Timer timer = null;
    private String oldName = "";
    private String newName = "";
    private Boolean refresh = true;
    private Image image = null;
    private int displaceX = 0;
    private int displaceY = 0;

    public PlayerPanel(GameLevel gameLevel, ActiveEntity activeEntity) {
        this.gameLevel  = gameLevel;
        this.activeEntity = activeEntity;
        this.getImage();
        this.setDoubleBuffered(true);
        this.displaceX = this.gameLevel.getMapPanel().getWidth() / 20;
        this.displaceY = this.gameLevel.getMapPanel().getHeight() / 50;

        timer = new Timer(5, this);
        timer.start();
    }

    public PlayerPanel(ActiveEntity activeEntity) {
        this.activeEntity = activeEntity;
        this.getImage();
        this.setDoubleBuffered(true);

        timer = new Timer(5, this);
        timer.start();

    }

    private void getImage() {
        newName = "";
        switch (this.activeEntity.getDirection()) {
            case East:
                newName = this.activeEntity.getImagePrefix() + "_right.gif";
                break;
            case North:
                newName = this.activeEntity.getImagePrefix() + "_up.gif";
                break;
            case South:
                newName = this.activeEntity.getImagePrefix() + "_down.gif";
                break;
            case West:
                newName = this.activeEntity.getImagePrefix() + "_left.gif";
                break;
            case Stop:
                newName = this.activeEntity.getImagePrefix() + "_stop.gif";
                break;
            default:
                break;
        }
        if (this.oldName.equals(this.newName)) {
            refresh = false;
        } else {
            this.oldName = this.newName;
            this.image = Toolkit.getDefaultToolkit().createImage(Resource.GetImage(newName));
            refresh = true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        getImage();

        if (gameLevel.getMapPanel() != null) {

            Double nX = displaceX + this.activeEntity.getCenteredX() * (-0.8);
            Double nY = displaceY + this.activeEntity.getCenteredY() * (-0.8);
            gameLevel.getMapPanel().setBounds(nX.intValue() + gameLevel.getMapAdjustmentX(), nY.intValue() + gameLevel.getMapAdjustmentY(), gameLevel.getMapPanel().getWidth(), gameLevel.getMapPanel().getHeight());
            gameLevel.getEntityPanel().setBounds(nX.intValue() + gameLevel.getMapAdjustmentX(), nY.intValue() + gameLevel.getMapAdjustmentY(), gameLevel.getMapPanel().getWidth(), gameLevel.getMapPanel().getHeight());
            gameLevel.getMapPanel().repaint();

            Double pX = displaceX + this.activeEntity.getCenteredX() * (0.2);
            Double pY = displaceY + this.activeEntity.getCenteredY() * (0.2);


            this.setBounds(pX.intValue() + gameLevel.getMapAdjustmentX(), pY.intValue() + gameLevel.getMapAdjustmentY(), this.activeEntity.getWidth(), this.activeEntity.getHeight());
            if (refresh) {
                this.repaint();
            }

        } else {
            this.setBounds(displaceX + this.activeEntity.getCenteredX(), displaceY + this.activeEntity.getCenteredY(), this.activeEntity.getWidth(), this.activeEntity.getHeight());
            if (refresh) {
                this.repaint();
            }
        }
    }
}
