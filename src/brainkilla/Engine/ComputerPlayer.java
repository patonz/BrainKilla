package brainkilla.Engine;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Gigli*
 */
public class ComputerPlayer extends Player implements Runnable {

    private boolean active = true;
    private ActiveEntity activeEntity;

    public ComputerPlayer(ActiveEntity activeEntity, GameLevel gameLevel) {
        super(activeEntity, gameLevel);
        this.activeEntity = activeEntity;
    }

    @Override
    public void run() {
        
        if (activeEntity.getLifeScores() <= 0) {
            this.stop();
        }
        
        Random r = new Random();

        //movimento dei boss: seguono sempre il giocatore
        if (activeEntity.getMapEntityType() == MapEntityType.Boss || activeEntity.getMapEntityType() == MapEntityType.Heaven) {

            while (active) {

                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (activeEntity.getCenteredX() >= this.getGameLevel().getMainPlayer().getCenteredX()) {
                    move(-1, 0);
                }
                if (activeEntity.getCenteredX() <= this.getGameLevel().getMainPlayer().getCenteredX()) {
                    move(1, 0);
                }
                if (activeEntity.getCenteredY() >= this.getGameLevel().getMainPlayer().getCenteredY()) {
                    move(0, -1);
                }
                if (activeEntity.getCenteredY() <= this.getGameLevel().getMainPlayer().getCenteredY()) {
                    move(0, 1);
                }
            }
        }

        //movimento dei mostri: random + aggro
        if (activeEntity.getMapEntityType() == MapEntityType.Monster) {

            while (active) {

                if (activeEntity.inSight(this.getGameLevel().getMainPlayer())) {

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (activeEntity.getCenteredX() >= this.getGameLevel().getMainPlayer().getCenteredX()) {
                        move(-1, 0);
                    }
                    if (activeEntity.getCenteredX() <= this.getGameLevel().getMainPlayer().getCenteredX()) {
                        move(1, 0);
                    }
                    if (activeEntity.getCenteredY() >= this.getGameLevel().getMainPlayer().getCenteredY()) {
                        move(0, -1);
                    }
                    if (activeEntity.getCenteredY() <= this.getGameLevel().getMainPlayer().getCenteredY()) {
                        move(0, 1);
                    }
                }

                if (!(activeEntity.inSight(this.getGameLevel().getMainPlayer()))) {

                    int direction = r.nextInt(4);
                    int numTimes = r.nextInt(6) + 1;

                    for (int i = 0; i < numTimes; i++) {

                        try {
                            Thread.sleep(r.nextInt(300));
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        switch (direction) {
                            case 0:
                                move(0, 5);
                                break;
                            case 1:
                                move(5, 0);
                                break;
                            case 2:
                                move(-5, 0);
                                break;
                            case 3:
                                move(0, -5);
                                break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void stop() {

        active = false;
    }
}
