package brainkilla.Engine;

import brainkilla.GUI.GameFrame;
import brainkilla.GUI.GameLevelLoadingPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Markus Aurich
 */
public class Game {
    
    private GameFrame gameFrame;
    private Highscore highscore;
    
    private ArrayList<GameLevelLoadingPanel> levelLoadingPanelList = new ArrayList<>();
    private ArrayList<GameLevel> levelList = new ArrayList<>();
    private int levelIndex = 0;

    private long startTime;
    private javax.swing.Timer loadingTimer;


    public Game(String playerName) {
        
        this.gameFrame = new GameFrame();
        this.highscore = new Highscore(playerName);
        
        initialize();
    }
    
    private void initialize() {
        
        //1° livello
        {
            levelList.add(new GameLevel(this, "mappa01.jpg", "mappa01_entities.png"));        
            levelLoadingPanelList.add(new GameLevelLoadingPanel(new Color(0, 0, 0), "loading_level01.png",429,408));
        }
        
        //2° livello
        {
            GameLevel thisLevel = new GameLevel(this, "mappa02.jpg", "mappa01_entities.png");
            thisLevel.setMaxNumberMonsters(150);
            thisLevel.setMonsterImagePrefix("mostro02");
            thisLevel.setBossImagePrefixes(new String[]{"boss02"});
            thisLevel.setWardenImagePrefixes(new String[]{"wardenlevel02_01", "wardenlevel02_02", "wardenlevel02_03"});
            thisLevel.setSongFilename("bgmusic_level02.mp3");
            
            levelList.add(thisLevel);
            levelLoadingPanelList.add(new GameLevelLoadingPanel(new Color(0, 0, 0), "loading_level02.png",429,408));
        }
        
        //3° livello
        {
            GameLevel thisLevel = new GameLevel(this, "mappa03.jpg", "mappa03_entities.png");
            thisLevel.setMaxNumberMonsters(0);
            thisLevel.setMainPlayerImagePrefix("principessa");
            thisLevel.setSongFilename("bgmusic_level03.mp3");
            thisLevel.setMapAdjustmentX(200);
            thisLevel.setMapAdjustmentY(300);
            
            levelList.add(thisLevel);
            levelLoadingPanelList.add(new GameLevelLoadingPanel(new Color(0, 0, 0), "loading_level03.png",429,408));
        }
       
    }
    
    public final void startNextLevel() {
        
         loadingTimer = new javax.swing.Timer(50, new ActionListener() {

            boolean levelInitialized = false;

            @Override
            public void actionPerformed(ActionEvent e) {

                long diffTime = System.currentTimeMillis() - startTime;
                double seconds = (int) (diffTime / 1000 % 60);

                if (seconds > 0.1 && !levelInitialized) {
                    
                    //caricare il livello
                    levelList.get(levelIndex).load();

                    new Thread(levelList.get(levelIndex), "threadGameLevel").start();
                    
                    loadingTimer.stop();
                    
                    levelInitialized = true;
                }
            }
        });

        gameFrame.addSubPanel(levelLoadingPanelList.get(levelIndex));
        
        startTime = System.currentTimeMillis();
        loadingTimer.start();
    }
    
    public final void destroyLevel(GameLevel gameLevel) {
        
        levelList.set(levelList.indexOf(gameLevel), null);
    }    
    
    public void addToHighscore(int scores, int secondsElapsed, int questionsResolved) {
        
        this.highscore.addScores(scores - this.highscore.getScores());
        this.highscore.addSecondsElapsed(secondsElapsed);
        this.highscore.addQuestionsResolved(questionsResolved);
    }
    
    public Highscore getHighscore() {
        
        return this.highscore;
    }
    
    public GameFrame getGameFrame() {
        
        return this.gameFrame;
    }
    
    public boolean gameLevelEof() {
        
        if (levelList.size() > levelIndex)
            return false;
        else
            return true;
    }
    
    public void gameLevelMoveNext() {
        
        levelIndex++;
    }
    
    public int getLevelIndex() {
        
        return levelIndex;
    }
    
    public GameLevel getLevel(int levelIndex) {
        
        if (levelList.size() > levelIndex)
            return levelList.get(levelIndex);
        
        else
            return null;
    }
    
}
