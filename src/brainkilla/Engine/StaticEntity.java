package brainkilla.Engine;

/**
 * @author Markus Aurich
 */
public class StaticEntity extends MapEntity {
    
    public StaticEntity(int x, int y, MapEntityType mapEntityType) {
        
        super(x, y, mapEntityType);
    }
    
    @Override
    public String toString() {
        
        return "x: " + getCenteredX() + ", y: " + getCenteredY() + ", mapEntityType: " + getMapEntityType();
    }

}
