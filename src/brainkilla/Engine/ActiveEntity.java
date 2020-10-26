package brainkilla.Engine;

import brainkilla.Skeleton.ActiveEntityInterface;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import util.Resource;

/**
 *
 * @author Lorenzo Gigli*
 */
public class ActiveEntity extends MapEntity implements ActiveEntityInterface {
    
    private GameLevel gameLevel;
    private int sight = 150;
    private String imagePrefix;
    private Direction direction = Direction.Stop;
    private int lifeScores;
    private boolean isShootActive;
    private int scores;


    public ActiveEntity(GameLevel gameLevel, String imagePrefix, MapEntityType type, int lifeScores) {

        super();
        
        this.gameLevel = gameLevel;
        this.imagePrefix = imagePrefix;
        this.mapEntityType = type;
        this.lifeScores = lifeScores;
        isShootActive = false;
        
         try {
             
            BufferedImage readedImage = ImageIO.read(Resource.GetImage(this.imagePrefix + "_stop.gif"));

            setWidth(readedImage.getWidth());
            setHeight(readedImage.getHeight());          
            
        } catch (IOException ex) {
            
            System.out.println("File '" + this.imagePrefix + "_stop.gif' non trovato");
            
        } catch (IllegalArgumentException ex) {
            
            System.out.println("File '" + this.imagePrefix + "_stop.gif' non trovato. Illegal Argument");    
        }    
    }    
    

    public ActiveEntity(GameLevel gameLevel, int width, int height, String imagePrefix, MapEntityType type, int lifeScores) {

        super();
        
        this.gameLevel = gameLevel;
        this.setWidth(width);
        this.setHeight(height);
        this.imagePrefix = imagePrefix;
        this.mapEntityType = type;
        this.lifeScores = lifeScores;
        isShootActive = false;
    }    
    
    public synchronized Direction getDirection() {
        return direction;
    }

    public synchronized void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public boolean getShootStatus() {
        return isShootActive;
    }
    
    public void setShootStatus(boolean shootStatus) {
        this.isShootActive = shootStatus;
    }

    public String getImagePrefix() {
        return this.imagePrefix;
    }

    public void setImage(String imageBase) {
        this.imagePrefix = imageBase;
    }
    
    public void addScores(int scores) {
        
        this.scores = this.scores + scores;
        
        if (this.getMapEntityType() == mapEntityType.MainPlayer)
            gameLevel.getGameStatusPanel().setScore(this.scores);
    }
    
    public int getScores() {
        
        return this.scores;
    }

    public int getLifeScores() {
        return lifeScores;
    }

    public void setLifeScores(int lifeScores) {
        this.lifeScores = lifeScores;
    }

    public boolean reduceLifeScores(int lifeScores) {

        this.lifeScores = this.lifeScores - lifeScores;

        if (this.getMapEntityType() == mapEntityType.MainPlayer)
            gameLevel.getGameStatusPanel().setLifes((int)(this.lifeScores / 100));        
        
        if (this.lifeScores <= 0) {       
            
            gameLevel.removeActiveEntity(this);
            
            return false;
        }
        
        return true;        
    }

    public boolean inSight(ActiveEntity activeEntity) {
        return Math.abs(activeEntity.getCenteredX() - this.getCenteredX()) < sight && Math.abs(activeEntity.getCenteredY() - this.getCenteredY()) < sight;
    }

    @Override
    public String toString() {

        return "witdh: " + getWidth() + ", height: " + getHeight() + ", centeredX: " + getCenteredX() + ", centeredY: " + getCenteredY() + ", topY: " + getTopY() + ", rightX: " + getRightX() + ", bottomY: " + getBottomY() + ", leftX:" + getLeftX();
    }
    
}
