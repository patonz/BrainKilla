package brainkilla;

import brainkilla.Engine.Game;
import brainkilla.Engine.HighscoreManager;
import brainkilla.GUI.MainFrame;
import brainkilla.GUI.MainPanel;
import javax.media.Format;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;

/**
 * @version 0.1
 * @author Lorenzo Gigli & Markus Aurich
 */
public class BrainKilla //main class
{
    private static Game game;
    
    
    public static void startNewGame(String playerName) {
        
       BrainKilla.game = new Game(playerName);
       
       if (!BrainKilla.game.gameLevelEof())
           BrainKilla.game.startNextLevel();
    }
    
    public static void endGame() {
        
        HighscoreManager.addHighscore(BrainKilla.game.getHighscore());
        BrainKilla.game = null;
    }
    
    public static void main(String[] args) throws Exception {
        
        Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format input2 = new AudioFormat(AudioFormat.MPEG);
        Format output = new AudioFormat(AudioFormat.LINEAR);
        
        PlugInManager.addPlugIn(
            "com.sun.media.codec.audio.mp3.JavaDecoder",
            new Format[]{input1, input2},
            new Format[]{output},
            PlugInManager.CODEC
        ); 
        
        
        MainFrame mainFrame = new MainFrame();
        mainFrame.nextPanel(new MainPanel());
        mainFrame.setVisible(true);
    }
    
}
