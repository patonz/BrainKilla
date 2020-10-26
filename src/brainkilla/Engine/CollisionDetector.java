package brainkilla.Engine;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;

/**
 * @author Markus Aurich
 */
public class CollisionDetector {
    
    private static ArrayList<ActiveEntity> activeEntityList = new ArrayList<>();
    
    public static Hashtable staticEntityTable = new Hashtable();
    public static int mapHeight = 0;
    
    public static void reset() {

        activeEntityList.clear();
        staticEntityTable.clear();
        mapHeight = 0;        
    }
    
    public static void addActiveEntity(ActiveEntity activeEntity) {
        
        activeEntityList.add(activeEntity);
    }
    
    public static void removeEntity(ActiveEntity activeEntity){
        activeEntityList.remove(activeEntity);
    }
    
    
    private static StaticEntity getStaticEntity(int x, int y) {
        
        if (staticEntityTable.containsKey(x + "|" + y))
            return (StaticEntity)staticEntityTable.get(x + "|" + y);
        else
            return null;
        
    }
    
    private static ArrayList<ActiveEntity> getActiveEntities(int x, int y) throws ConcurrentModificationException {
        
        ArrayList<ActiveEntity> resultActiveEntityList = new ArrayList<>();
                
        for (int i = 0; i < activeEntityList.size(); i++) {
            
            if(y >= activeEntityList.get(i).getTopY()
                    && y <= activeEntityList.get(i).getBottomY()
                    && x >= activeEntityList.get(i).getLeftX()
                    && x <= activeEntityList.get(i).getRightX()) {
                
                resultActiveEntityList.add(activeEntityList.get(i));
            
            }       
        }
        
        return resultActiveEntityList;
    }
    
    public static ArrayList<ActiveEntity> getActiveEntities(int x1, int y1, int x2, int y2) {
        
        ArrayList<ActiveEntity> resultActiveEntityList = new ArrayList<>();

        for (int x = x1; x < x2; x++) {
            
            for (int y = y1; y < y2; y++) {
                
                ArrayList<ActiveEntity> thisPointActiveEntityList = getActiveEntities(x, y);
                                
                for (int i = 0; i < thisPointActiveEntityList.size(); i++) {
                    
                    if (!resultActiveEntityList.contains(thisPointActiveEntityList.get(i)))
                        resultActiveEntityList.add(thisPointActiveEntityList.get(i));                    
                }
            }
        }
        
        return resultActiveEntityList;
    }
    
    public static ArrayList<MapEntity> controlActiveEntityMove(MapEntity mapEntity, int centeredX, int centeredY) throws ConcurrentModificationException {
        
        ArrayList<MapEntity> mapEntityList = new ArrayList<>();
        
        int differenceX = (mapEntity.getCenteredX() - centeredX) * -1;
        int differenceY = (mapEntity.getCenteredY() - centeredY) * -1;
        
        {
            int fromX = 0;
            int toX = 0;
            int fromY = 0;
            int toY = 0;
            
            //muovimento sull'assa Y
            if (Math.abs(differenceY) > 0) {

                fromX = mapEntity.getLeftX();
                toX = mapEntity.getRightX();
               
                //muovimento verso giù
                if (differenceY > 0) {

                    fromY = mapEntity.getBottomY();
                    toY = mapEntity.getBottomY() + differenceY;                
                }

                //muovimento verso sù
                else {

                    fromY = mapEntity.getTopY() + differenceY;
                    toY = mapEntity.getTopY();
                }

            }
            
            //muovimento sull'assa X
            if (Math.abs(differenceX) > 0) {

                fromY = mapEntity.getTopY();
                toY = mapEntity.getBottomY();
               

                //muovimento verso destra
                if (differenceX > 0) {

                    fromX = mapEntity.getRightX();
                    toX = mapEntity.getRightX() + differenceX;
                }
            
                //muovimento verso sinistra
                else {

                    fromX = mapEntity.getRightX() + differenceX;
                    toX = mapEntity.getRightX();            
                }
            }
            
            for (int y = fromY; y <= toY; y++) {

                for (int x = fromX; x <= toX; x++) {

                    if (getStaticEntity(x, y) != null) {
                        
                        mapEntityList.add(getStaticEntity(x, y));

                    }
                    
                }
            
            }
        
        }  
          
        for (int i = 0; i < activeEntityList.size(); i++) {
            
           if(!mapEntity.equals(activeEntityList.get(i))
                   && mapEntity.getBottomY() + differenceY >= activeEntityList.get(i).getTopY()
                   && mapEntity.getTopY() + differenceY <= activeEntityList.get(i).getBottomY() 
                   && mapEntity.getRightX() + differenceX >= activeEntityList.get(i).getLeftX() 
                   && mapEntity.getLeftX() + differenceX <= activeEntityList.get(i).getRightX()) {
               
               mapEntityList.add(activeEntityList.get(i));
               
           }
        
        }

        return mapEntityList;
    }
    
    public static ArrayList<ActiveEntity> getActiveEntitiesByRadius(int x, int y, int radius) {

        ArrayList<ActiveEntity> activeEntityList = new ArrayList<>();
        int loopCounter = 0; 
        
        for (int x2 = x-radius; x2 < x+radius; x2 = x2 + 4) {
            
            for (int y2 = y-radius; y2 < y+radius; y2 = y2 + 4) {
                
                loopCounter++;
                
                if (CollisionDetector.GetDistance(x, y, x2, y2) <= radius) {
                    
                    ArrayList<ActiveEntity> resultActiveEntityList = getActiveEntities(x2, y2);
                    
                    for (int i = 0; i < resultActiveEntityList.size(); i++) {
                        
                        loopCounter++;
                        
                        if (!activeEntityList.contains(resultActiveEntityList.get(i))) {                            
                            
                            activeEntityList.add(resultActiveEntityList.get(i));
                        }                     
                    }
                    
                }                
            }
        }

        return activeEntityList;      
    }
    
    private static double GetDistance(int x1, int y1, int x2, int y2) {
        
        int opposite = Math.abs(x2-x1);
        int adjacent = Math.abs(y1-y2);
        double hypotenuse = (double)Math.sqrt(opposite * opposite + adjacent * adjacent);
        
        return hypotenuse;
    }

}
