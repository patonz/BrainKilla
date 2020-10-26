package brainkilla.Engine;

import brainkilla.Skeleton.MapEntityInterface;

/**
 *
 * @author Lorenzo Gigli
 */
public class MapEntity implements MapEntityInterface {
    
    private int width;
    private int height;
    private int centeredX;
    private int centeredY;
    public MapEntityType mapEntityType;
    
    public MapEntity() {
        
    }
    
    public MapEntity(int centeredX, int centeredY) {

        this.setWidth(1);
        this.setHeight(1);
        
        this.setStartingPosition(centeredX, centeredY);
    }
    
    public MapEntity(int centeredX, int centeredY, MapEntityType mapEntityType) {
        
        this.setWidth(1);
        this.setHeight(1);
        this.setMapEntityType(mapEntityType);
        
        this.setStartingPosition(centeredX, centeredY);
    }    
    
    public MapEntityType getMapEntityType() {
        return mapEntityType;
    }
    
    @Override
    public int getCenteredX() {
        return centeredX;
    }

    @Override
    public int getCenteredY() {
        return centeredY;
    }

    @Override
    public int getTopY() {
        return getCenteredY() - getHeight()/2;
    }

    @Override
    public int getBottomY() {
        return getCenteredY() + getHeight()/2;
    }

    @Override
    public int getRightX() {
        return getCenteredX() + getWidth()/2;
    }

    @Override
    public int getLeftX() {
        return getCenteredX() - getWidth()/2;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public boolean setStartingPosition(int centeredX, int centeredY) {

        if (CollisionDetector.getActiveEntities(centeredX - getWidth()/2, centeredY - getHeight()/2, centeredX + getWidth()/2, centeredY + getHeight()/2).isEmpty()) {
        
            this.centeredX = centeredX;
            this.centeredY  = centeredY;

            return true;
        }
        else {
            
            return false;
        }
    }
    
    @Override
    public void setPosition(int centeredX, int centeredY) {
        
        this.centeredX = centeredX;
        this.centeredY = centeredY;
    }
    
    @Override
    public final void setWidth(int width) {
        this.width = width;
    }

    @Override
    public final void setHeight(int height) {
        this.height = height;
    }
    
    public final void setMapEntityType(MapEntityType mapEntityType) {
        
        this.mapEntityType = mapEntityType;
    }
    
}
