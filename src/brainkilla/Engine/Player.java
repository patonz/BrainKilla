package brainkilla.Engine;

import brainkilla.Skeleton.PlayerInterface;
import java.util.ArrayList;

/**
 *
 * @author Lorenzo Gigli & Markus Aurich*
 */
public abstract class Player implements PlayerInterface {

    private ActiveEntity entity = null;
    private GameLevel gameLevel = null;

    public Player(ActiveEntity e, GameLevel gameLevel) {

        this.gameLevel = gameLevel;
        this.entity = e;
    }

    @Override
    public int getPositionX() {
        return entity.getCenteredX();
    }

    @Override
    public int getPositionY() {
        return entity.getCenteredY();
    }
    
    public GameLevel getGameLevel() {
        
        return gameLevel;
    }

    protected void move(int x, int y) {

        if (this.entity.getLifeScores() > 0) {

            ArrayList<MapEntity> mapEntityList = CollisionDetector.controlActiveEntityMove(this.entity, this.getPositionX() + x, this.getPositionY() + y);

            if (mapEntityList.isEmpty()) {

                if (x == 0) {
                    if (y > 0) {
                        this.entity.setDirection(Direction.South);
                    } else {
                        this.entity.setDirection(Direction.North);
                    }
                }
                if (y == 0) {
                    if (x > 0) {
                        this.entity.setDirection(Direction.East);
                    } else {
                        this.entity.setDirection(Direction.West);
                    }
                }

                this.entity.setPosition(this.getPositionX() + x, this.getPositionY() + y);

            } else if (!mapEntityList.isEmpty()) {

                for (int i = 0; i < mapEntityList.size(); i++) {

                    this.launchEvent(mapEntityList.get(i));

                }
            }
        }
    }

    protected void launchEvent(MapEntity mapEntity) {

        if (mapEntity.getMapEntityType() == MapEntityType.Barrier) {
            
            //Do Nothing
            
        } else if (mapEntity.getMapEntityType() == MapEntityType.Transition) {
            
            gameLevel.getMapScanner().transitionPointsMoveFirst();

            StaticEntity transitionPoint = gameLevel.getMapScanner().getNextTransitionPoint();
            
            while (!gameLevel.getMapScanner().transitionPointsEof() && transitionPoint.equals(mapEntity))
                transitionPoint = gameLevel.getMapScanner().getNextTransitionPoint();            

            this.entity.setPosition(transitionPoint.getCenteredX(), transitionPoint.getCenteredY());
            
        } 
        else  {

            ActiveEntity activeEntity = null;

            try {

                activeEntity = (ActiveEntity) mapEntity;
                
            } catch (ClassCastException e) {

                System.out.println("Error: activeEntity = (ActiveEntity) mapEntity;");
                System.out.println(mapEntity.toString());
            }

            if (activeEntity != null) {

                //Player vs. Monster, Boss, Warden
                if (this.entity.getMapEntityType() == MapEntityType.MainPlayer) {

                    if (mapEntity.getMapEntityType() == MapEntityType.Monster) {

                        this.entity.reduceLifeScores(100);
                        activeEntity.reduceLifeScores(100);

                    } else if (activeEntity.getMapEntityType() == MapEntityType.Boss) {

                        this.entity.reduceLifeScores(100);
                        activeEntity.reduceLifeScores(500);

                    } else if (activeEntity.getMapEntityType() == MapEntityType.Warden) {

                        if (!gameLevel.isQuestionPanelActive()) {
                            gameLevel.openQuestionPanel(activeEntity);
                        }

                    } else if (activeEntity.getMapEntityType() == MapEntityType.Heaven) {
                        
                        this.entity.addScores(1000);
                        gameLevel.endLevelWon();

                    }                    

                //Monster vs. Mainplayer
                } else if (this.entity.getMapEntityType() == MapEntityType.Monster) {

                    if (activeEntity.getMapEntityType() == MapEntityType.MainPlayer) {

                        activeEntity.reduceLifeScores(100);
                        this.entity.reduceLifeScores(100);

                    }

                //Boss vs. Mainplayer
                } else if (this.entity.getMapEntityType() == MapEntityType.Boss) {

                    if (activeEntity.getMapEntityType() == MapEntityType.MainPlayer) {

                        activeEntity.reduceLifeScores(100);
                        this.entity.reduceLifeScores(500);

                    }
                
                //Heaven vs. Mainplayer
                } else if (this.entity.getMapEntityType() == MapEntityType.Heaven) {

                    if (activeEntity.getMapEntityType() == MapEntityType.MainPlayer) {

                        this.entity.addScores(1000);
                        gameLevel.endLevelWon();

                    }
                    
                }   
            }
        }
    }

    protected void stop() {
        entity.setShootStatus(false);
        this.entity.setDirection(Direction.Stop);
    }

    protected void shoot() {

        entity.setShootStatus(true);

        ArrayList<ActiveEntity> activeEntityList = CollisionDetector.getActiveEntitiesByRadius(this.entity.getCenteredX(), this.entity.getCenteredY(), 100);

        for (int i = 0; i < activeEntityList.size(); i++) {

            ActiveEntity activeEntity = activeEntityList.get(i);

            if (!this.entity.equals(activeEntity)) {

                if (!activeEntity.reduceLifeScores(50)) {

                    if (activeEntity.getMapEntityType() == MapEntityType.Monster) {

                        this.entity.addScores(10);

                    } else if (activeEntity.getMapEntityType() == MapEntityType.Boss) {

                        this.entity.addScores(75);
                    }
                }
            }
        }
    }
}
