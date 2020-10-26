package brainkilla.Skeleton;

/**
 *
 * @author Markus Aurich
 */
public interface MapEntityInterface {
    
    public int getCenteredX();
    
    public int getCenteredY();
    
    public int getTopY();
    
    public int getBottomY();
    
    public int getRightX();
    
    public int getLeftX();
    
    public int getWidth();
    
    public int getHeight();    
    
    public boolean setStartingPosition(int centeredX, int centeredY);
    
    public void setPosition(int centeredX, int centeredY);
    
    public void setWidth(int width);
    
    public void setHeight(int height);        
    
}
