package brainkilla.Engine;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import util.Resource;

/**
 * @author Markus Aurich
 */
public class MapScanner {
    
    private BufferedImage mapImage = null;
    
    private int width;
    private int height;
    
    private Color colorBackground;
    private Color colorBarriers;
    private Color colorMonstersStartPoint;
    private Color colorHeavenStartPoint;
    private ArrayList<Color> colorsPlayerStartPoints = new ArrayList<>();
    private ArrayList<Color> colorsTransitionPoint = new ArrayList<>();
    private ArrayList<Color> colorsWarden = new ArrayList<>();
    private ArrayList<Color> colorsBosses = new ArrayList<>();
    
    private Hashtable staticEntityTable = new Hashtable();
    
    private int transitionPointIndex;
    private int monsterStartpointIndex;
    private int playerStartpointIndex;
    private int heavenStartpointIndex;
    private int wardenStartpointIndex;
    private int bossStartpointIndex;
    
    private ArrayList<StaticEntity> transitionPointList = new ArrayList<>();
    private ArrayList<MapEntity> monsterStartpointList = new ArrayList<>();
    private ArrayList<MapEntity> playerStartpointList = new ArrayList<>();
    private ArrayList<MapEntity> heavenStartpointList = new ArrayList<>();
    private ArrayList<MapEntity> wardenStartpointList = new ArrayList<>();
    private ArrayList<MapEntity> bossStartpointList = new ArrayList<>();
    
    public MapScanner() {
        
        this.initialize();
    }
    
    public MapScanner(String mapFilename ){
        
        this.initialize();
        this.loadMap(mapFilename);
        this.scanMap();
    }
    
    private void initialize() {
        
        colorBackground = new Color(255, 255, 255);             //#ffffff
        
        colorBarriers = new Color(0, 0, 0);                     //#000000
        
        colorMonstersStartPoint = new Color(255, 162, 0);       //#ffa200
        
        colorHeavenStartPoint = new Color(48, 0, 255);          //
        
        colorsPlayerStartPoints.add(new Color(255, 0 , 0));     //#ff0000 
        
        colorsTransitionPoint.add(new Color(246, 255, 0));           //#f6ff00
        colorsTransitionPoint.add(new Color(0, 250, 31));            //#00fa1f
        
        colorsWarden.add(new Color(255, 0, 174));               //#ff00ae
        colorsWarden.add(new Color(255, 112, 210));             //#ff6fd1
        colorsWarden.add(new Color(153, 0, 104));               //#990068
        
        colorsBosses.add(new Color(0, 255, 228));               //#00ffe4
        colorsBosses.add(new Color(0, 155, 139));               //#009b8b
        colorsBosses.add(new Color(0, 66, 59));                 //#00423b
        
        this.monsterStartpointsMoveFirst();
        this.playerStartpointsMoveFirst();
        this.wardenStartpointsMoveFirst();
        this.transitionPointsMoveFirst();
    }
    
    
    public int getWidth() {
        
        return width;
    }
    
    
    public int getHeight() {
        
        return height;
    }
    
    public Hashtable getStaticEntityTable() {
        
        return staticEntityTable;
    }
    
    
    //Gestione Monster Startpoint
    public boolean monsterStartpointsEof() {
        
        if (monsterStartpointList.size() > monsterStartpointIndex)
            return false;
        else
            return true;
    }
    
    public int getMonsterStartpointsCount() {
        
        return monsterStartpointList.size();
    }
    
    public MapEntity getNextMonsterStartpoint() {
        
        MapEntity monsterStartpoint = null;
        
        if (!monsterStartpointsEof()) {
            
            monsterStartpoint = monsterStartpointList.get(monsterStartpointIndex);
            monsterStartpointIndex++;
        }
        
        return monsterStartpoint;
    }
    
    public void monsterStartpointsMoveFirst() {
        
        monsterStartpointIndex = 0;
    }
    
    
    //Gestione Player Startpoints
    public boolean playerStartpointsEof() {
        
        if (playerStartpointList.size() > playerStartpointIndex)
            return false;
        else
            return true;
    }
    
    public MapEntity getNextPlayerStartpoint() {
        
        MapEntity playerStartpoint = null;
        
        if (!playerStartpointsEof()) {
            
            playerStartpoint = playerStartpointList.get(playerStartpointIndex);
            playerStartpointIndex++;
        }
        
        return playerStartpoint;
    }
    
    public void playerStartpointsMoveFirst() {
        
        playerStartpointIndex = 0;
    }
    
    
    //Gestione Heaven
    public boolean heavenStartpointsEof() {
        
        if (heavenStartpointList.size() > heavenStartpointIndex)
            return false;
        else
            return true;
    }
    
    public MapEntity getNextHeavenStartpoint() {
        
        MapEntity heavenStartpoint = null;
        
        if (!heavenStartpointsEof()) {
            
            heavenStartpoint = heavenStartpointList.get(heavenStartpointIndex);
            heavenStartpointIndex++;
        }
        
        return heavenStartpoint;
    }
    
    public void heavenStartpointsMoveFirst() {
        
        heavenStartpointIndex = 0;
    }      
    

        
    //Gestione Warden Startpoints
    public boolean wardenStartpointsEof() {
        
        if (wardenStartpointList.size() > wardenStartpointIndex)
            return false;
        else
            return true;
    }
    
    public int getWardenStartpointsCount() {
        
        return wardenStartpointList.size();
    }
    
    public MapEntity getNextWardenStartpoint() {
        
        MapEntity wardenStartpoint = null;
        
        if (!wardenStartpointsEof()) {
            
            wardenStartpoint = wardenStartpointList.get(wardenStartpointIndex);
            wardenStartpointIndex++;
        }
        
        return wardenStartpoint;
    }
    
    public void wardenStartpointsMoveFirst() {
        
        wardenStartpointIndex = 0;
    } 
    
    
    //Gestione Boss Startpoints
    public boolean bossStartpointsEof() {
        
        if (bossStartpointList.size() > bossStartpointIndex)
            return false;
        else
            return true;
    }
    
    public MapEntity getNextBossStartpoint() {
        
        MapEntity bossStartpoint = null;
        
        if (!bossStartpointsEof()) {
            
            bossStartpoint = bossStartpointList.get(bossStartpointIndex);
            bossStartpointIndex++;
        }
        
        return bossStartpoint;
    }
    
    public void bossStartpointsMoveFirst() {
        
        bossStartpointIndex = 0;
    }  
    
    
    
    //Gestione Transition Points
    public boolean transitionPointsEof() {
        
        if (transitionPointList.size() > transitionPointIndex)
            return false;
        else
            return true;
    }
    
    public StaticEntity getNextTransitionPoint() {
        
        StaticEntity transitionPoint = null;
        
        if (!transitionPointsEof()) {
            
            transitionPoint = transitionPointList.get(transitionPointIndex);
            transitionPointIndex++;
        }
        
        return transitionPoint;
    }
    
    public void transitionPointsMoveFirst() {
        
        transitionPointIndex = 0;
    }
    
    
    private void loadMap(String mapFilename) {
           
        try {
            
            this.mapImage = ImageIO.read(Resource.GetImage(mapFilename));
            
            this.width = mapImage.getWidth();
            this.height = mapImage.getHeight();           
            
        } catch (IOException ex) {
            
            System.out.println("Problemi di leggere il file");
            
        }
    }
    
    
    private void scanMap() {

        if (this.mapImage != null) {

            for (int x = 0; x < this.width; x++) {

                for (int y = 0; y < this.height; y++) {

                    int color = mapImage.getRGB(x,y);

                    int  red   = (color & 0x00ff0000) >> 16;
                    int  green = (color & 0x0000ff00) >> 8;
                    int  blue  =  color & 0x000000ff;  

                    if (red == colorBackground.getRed() && green == colorBackground.getGreen() && blue == colorBackground.getBlue()) {
                        //Do nothing
                    }
                    
                    else if (red == colorBarriers.getRed() && green == colorBarriers.getGreen() && blue == colorBarriers.getBlue())
                        staticEntityTable.put(x + "|" + y, new StaticEntity(x, y, MapEntityType.Barrier));
                    
                    else if (red == colorMonstersStartPoint.getRed() && green == colorMonstersStartPoint.getGreen() && blue == colorMonstersStartPoint.getBlue())
                        monsterStartpointList.add(new MapEntity(x, y));
                    
                    else if (red == colorHeavenStartPoint.getRed() && green == colorHeavenStartPoint.getGreen() && blue == colorHeavenStartPoint.getBlue())
                        heavenStartpointList.add(new MapEntity(x, y));
                    
                    else {
                        
                        //Transition Points
                        for (int i = 0; i < colorsTransitionPoint.size(); i++) {
                            
                            if (red == colorsTransitionPoint.get(i).getRed() && green == colorsTransitionPoint.get(i).getGreen() && blue == colorsTransitionPoint.get(i).getBlue()) {
                                
                                StaticEntity transitionPoint = new StaticEntity(x, y, MapEntityType.Transition);
                                staticEntityTable.put(x + "|" + y, transitionPoint);
                                transitionPointList.add(transitionPoint);
                            }
                        }
                        
                        //Player Startpoints
                        for (int i = 0; i < colorsPlayerStartPoints.size(); i++) {
                            
                            if (red == colorsPlayerStartPoints.get(i).getRed() && green == colorsPlayerStartPoints.get(i).getGreen() && blue == colorsPlayerStartPoints.get(i).getBlue())
                                playerStartpointList.add(new MapEntity(x, y));
                
                        }
                        
                        //Warden Startpoints
                        for (int i = 0; i < colorsWarden.size(); i++) {
                            
                            if (red == colorsWarden.get(i).getRed() && green == colorsWarden.get(i).getGreen() && blue == colorsWarden.get(i).getBlue())
                                wardenStartpointList.add(new MapEntity(x, y));
                        }   
                         
                        //Boss Startpoints
                        for (int i = 0; i < colorsBosses.size(); i++) {
                            
                            if (red == colorsBosses.get(i).getRed() && green == colorsBosses.get(i).getGreen() && blue == colorsBosses.get(i).getBlue())
                                bossStartpointList.add(new MapEntity(x, y));
                        }                        

                    }

                }

            }

        }         
    }
    
}
