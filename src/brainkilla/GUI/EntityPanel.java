package brainkilla.GUI;

import brainkilla.Engine.ActiveEntity;
import brainkilla.Engine.GameLevel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JPanel;
import javax.swing.Timer;
import util.Resource;

/**
 *
 * @author Lorenzo Gigli*
 */
public class EntityPanel extends JPanel implements ActionListener {

    private ActiveEntity e = null;
    private GameLevel gameLevel;
    private Timer timer = null;
    private EntityPanel ep = null;
    private ArrayList<ActiveEntity> entities = new ArrayList<>();
    
    private Hashtable<ActiveEntity, Image> entity_imgs = new Hashtable<>();
    private Hashtable<ActiveEntity, String> entity_olds = new Hashtable<>();
    
    private Hashtable entityImageTable = new Hashtable<>();
    
    
    public EntityPanel(GameLevel gameLevel) {
        
        this.gameLevel = gameLevel;
        this.setOpaque(false);
        this.timer = new Timer(5, this);
        this.timer.start();
    }

    private void getImage(ActiveEntity activeEntity) {

        String newName = "";

        switch (activeEntity.getDirection()) {
            case East:
                newName = activeEntity.getImagePrefix() + "_right.gif";
                break;
            case North:
                newName = activeEntity.getImagePrefix() + "_up.gif";
                break;
            case South:
                newName = activeEntity.getImagePrefix() + "_down.gif";
                break;
            case West:
                newName = activeEntity.getImagePrefix() + "_left.gif";
                break;
            case Stop:
                newName = activeEntity.getImagePrefix() + "_stop.gif";
                break;
            default:
                break;
        }
        
        if (!(entity_olds.containsKey(activeEntity) && entity_olds.get(activeEntity).equals(newName))) {
            
            Image entityImage;
            
            if (entityImageTable.contains(newName)) {
                
                entityImage = (Image)entityImageTable.get(newName);
            }
            else {
               
                URL imageURL = Resource.GetImage(newName);
                
                if (imageURL != null)
                    entityImage = Toolkit.getDefaultToolkit().createImage(imageURL);                   
                else
                    entityImage = null;
                
                entityImageTable.put(newName, entityImage);
            }
            
            if (entityImage != null) {
                
                entity_olds.put(activeEntity, newName);
                entity_imgs.put(activeEntity, entityImage);
            }
        }
    }

    public void addEntity(ActiveEntity ae) {
        entities.add(ae);
        getImage(ae);
    }

    public synchronized void removeEntity(ActiveEntity ae) {
        entities.remove(ae);
        entity_imgs.remove(ae);
        entity_olds.remove(ae);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        for (int i = 0; i < entities.size(); i++) {

            ActiveEntity activeEntity = entities.get(i);

            if (activeEntity.getLifeScores() <= 0) {

                gameLevel.removeActiveEntity(activeEntity);

            } else {

                g.drawImage(entity_imgs.get(activeEntity), activeEntity.getCenteredX(), activeEntity.getCenteredY(), null);

            }
        }
    }
}
