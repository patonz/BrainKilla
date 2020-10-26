/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brainkilla.Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Leonardo Montecchiari
 */
/*
 * Questa classe determina il tempo di ogni singola sessione di gioco
 */
public class TimerGame {

    private GameLevel gameLevel;
    private long timeElpased;
    private Timer timer;
    private long startTime;
    private String statusTime;
    private int decSeconds;
    private int seconds;
    private int minutes;
    private int hours;
    private int sec = 0;

    public TimerGame(GameLevel gameLevel) {

        this.gameLevel = gameLevel;
    }

    // Start del Timer
    public void start() {

        timer = new Timer(50, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                timeElpased = System.currentTimeMillis() - startTime;  // tempo trascorso calcolato tramite l'orologio interno

                decSeconds = (int) (timeElpased % 1000 / 100); //decimi di secondo 
                seconds = (int) (timeElpased / 1000 % 60);     // secondi
                minutes = (int) (timeElpased / 60000 % 60);     //minuti
                hours = (int) (timeElpased / 3600000);          //ore

                statusTime = String.format("%d:%02d:%02d.%d", hours, minutes, seconds, decSeconds); // formazione della stringa in output

                gameLevel.getGameStatusPanel().setTime(statusTime); //richiamo il metodo per settare il timer

                //Blocco che determina la perdita di un punto per ogni secondo che passa durante la sessione di gioco
                if (sec < seconds) {

                    gameLevel.getMainPlayer().addScores(-1);
                    sec++;

                } else {
                    if (sec > seconds) {
                        sec = 0;
                        gameLevel.getMainPlayer().addScores(-1);
                    }
                }
            }
        });

        startTime = System.currentTimeMillis();
        timer.start();
    }
    //metodo "Stop" del timer

    public void stop() {

        timer.stop();
    }
    //metodo "get" dei secondi passati dallo start del timer

    public int getElapsedSeconds() {

        return (int) (timeElpased / 1000);
    }
}
