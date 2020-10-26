package brainkilla.Engine;

import brainkilla.BrainKilla;
import brainkilla.GUI.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.AudioManager;

/**
 *
 * @author Markus Aurich
 */
public class GameLevel implements Runnable {
    
    private int maxNumberMonsters = 75;
    private String songFilename = "bgmusic_level01.mp3";
    private String questionFilename = "domande.txt";
    
    private String mapFilename;
    private String mapEntitiesFilename;
    
    private String mainPlayerImagePrefix = "hero";
    private String monsterImagePrefix = "mostro01";
    private String heavenImagePrefix = "hero";
    private String[] bossImagePrefixes = new String[] {"boss01"};
    private String[] wardenImagePrefixes = new String[] {"wardenlevel01_01", "wardenlevel01_02", "wardenlevel01_03"};
    
    private int mapAdjustmentX = 0;
    private int mapAdjustmentY = 0;
    
    private int bossImagePrefixesIndex = 0;
    private int wardenImagePrefixesIndex = 0;
    
    private boolean threadActive = true;
    private Game game;
    private MapScanner mapScanner;
    private AudioManager soundGameLevel;
    private QuestionManager questionManager;
    private TimerGame timerGame;
    
    private ActiveEntity mainPlayer;
    private KeyboardPlayer keyboardPlayer;
   
    private ArrayList<ActiveEntity> monsterActiveEntites;
    private ArrayList<ActiveEntity> bossActiveEntites;
    private ArrayList<ActiveEntity> wardenActiveEntites;
    private ArrayList<ComputerPlayer> computerPlayers;
    
    private GameMapPanel gameMapPanel;
    private EntityPanel entityPanel;
    private GameStatusPanel gameStatusPanel;
    private PlayerPanel playerPanel;
    private ShootPanel shootPanel;
    private QuestionsPanel questionPanel;

        
    GameLevel(Game game, String mapFilename, String mapEntitiesFilename) {
        
        this.game = game;        
        this.mapFilename = mapFilename;
        this.mapEntitiesFilename = mapEntitiesFilename;
    }

    public void load() {

        computerPlayers = new ArrayList<>();

        monsterActiveEntites = new ArrayList<>();
        bossActiveEntites = new ArrayList<>();
        wardenActiveEntites = new ArrayList<>();

        soundGameLevel = new AudioManager(songFilename);
        mapScanner = new MapScanner(mapEntitiesFilename);
        
        timerGame = new TimerGame(this);

        gameMapPanel = new GameMapPanel(mapFilename);
        entityPanel = new EntityPanel(this);

        CollisionDetector.staticEntityTable = mapScanner.getStaticEntityTable();
        CollisionDetector.mapHeight = mapScanner.getHeight();

        gameStatusPanel = new GameStatusPanel(this);
        questionManager = new QuestionManager(this, questionFilename);

        generateMainPlayer();
        generateHeaven();
        generateWardens();
        generateBoss();
        generateMonsters();
        
        mainPlayer.addScores(game.getHighscore().getScores());

        gameStatusPanel.setLifes(mainPlayer.getLifeScores() / 100);
        gameStatusPanel.setScore(mainPlayer.getScores());

        start();
    }

    public void start() {

        game.getGameFrame().addSubPanel(gameMapPanel);
        game.getGameFrame().addSubPanel(entityPanel);
        game.getGameFrame().addSubPanel(shootPanel);
        game.getGameFrame().addSubPanel(playerPanel);
        game.getGameFrame().addSubPanel(gameStatusPanel);

        playSound();
        timerGame.start();
    }

    @Override
    public void run() {

        int passedTimeMs = 0;

        while (threadActive) {

            try {

                Thread.sleep(10000);
                passedTimeMs = passedTimeMs + 10000;

            } catch (InterruptedException ex) {

                Logger.getLogger(GameLevel.class.getName()).log(Level.SEVERE, null, ex);
            }

            generateMonsters();

            if (passedTimeMs == 60 * 1000 || passedTimeMs == 3 * 60 * 1000) {
                
                generateBoss();
            }
        }

    }

    private void stopThread() {

        threadActive = false;
    }

    private void generateMainPlayer() {

        if (!mapScanner.playerStartpointsEof()) {

            MapEntity playerStartpoint = mapScanner.getNextPlayerStartpoint();

            mainPlayer = new ActiveEntity(this, 18, 28, mainPlayerImagePrefix, MapEntityType.MainPlayer, 300);
            mainPlayer.setStartingPosition(playerStartpoint.getCenteredX(), playerStartpoint.getCenteredY());

            keyboardPlayer = new KeyboardPlayer(mainPlayer, this);
            CollisionDetector.addActiveEntity(mainPlayer);

            playerPanel = new PlayerPanel(this, mainPlayer);
            shootPanel = new ShootPanel(this, mainPlayer);
        }
    }
    
    private void generateHeaven() {
        
        if (!mapScanner.heavenStartpointsEof()) {
            
            MapEntity heavenStartpoint = mapScanner.getNextHeavenStartpoint();

            ActiveEntity heavenActiveEntity = new ActiveEntity(this, heavenImagePrefix, MapEntityType.Heaven, 50);
            
            if (heavenActiveEntity.setStartingPosition(heavenStartpoint.getCenteredX(), heavenStartpoint.getCenteredY())) {

                this.addActiveEntity(heavenActiveEntity);

                ComputerPlayer computerPlayerHeaven = new ComputerPlayer(heavenActiveEntity, this);
                computerPlayers.add(computerPlayerHeaven);

                new Thread(computerPlayerHeaven).start();
            }
        }        
    }

    private void generateMonsters() {
        
        for (int i = 0; i < mapScanner.getMonsterStartpointsCount(); i++) {

            if (!mapScanner.monsterStartpointsEof() && monsterActiveEntites.size() < maxNumberMonsters) {

                MapEntity startPoint = mapScanner.getNextMonsterStartpoint();
                ActiveEntity monsterActiveEntity = new ActiveEntity(this, 16, 23, monsterImagePrefix, MapEntityType.Monster, 100);

                if (monsterActiveEntity.setStartingPosition(startPoint.getCenteredX(), startPoint.getCenteredY())) {
                    
                    this.addActiveEntity(monsterActiveEntity);

                    ComputerPlayer monsterComputerPlayer = new ComputerPlayer(monsterActiveEntity, this);
                    computerPlayers.add(monsterComputerPlayer);

                    new Thread(monsterComputerPlayer).start();
                }
            }
        }

        mapScanner.monsterStartpointsMoveFirst();
    }

    private void generateBoss() {
        
        if (!mapScanner.bossStartpointsEof()) {

            MapEntity bossStartpoint = mapScanner.getNextBossStartpoint();

            ActiveEntity bossActiveEntity = new ActiveEntity(this, bossImagePrefixes[bossImagePrefixesIndex], MapEntityType.Boss, 500);
            
            if (bossActiveEntity.setStartingPosition(bossStartpoint.getCenteredX(), bossStartpoint.getCenteredY())) {

                this.addActiveEntity(bossActiveEntity);

                ComputerPlayer computerPlayerBoss = new ComputerPlayer(bossActiveEntity, this);
                computerPlayers.add(computerPlayerBoss);

                new Thread(computerPlayerBoss).start();

                if (bossImagePrefixes.length > bossImagePrefixesIndex+1)
                    bossImagePrefixesIndex++;
                else
                    bossImagePrefixesIndex = 0;
            }
        }

    }

    private void generateWardens() {
        
        while (!mapScanner.wardenStartpointsEof()) {
            
            MapEntity entityStartpoint = mapScanner.getNextWardenStartpoint();

            ActiveEntity wardenActiveEntity = new ActiveEntity(this, wardenImagePrefixes[wardenImagePrefixesIndex], MapEntityType.Warden, 100);
            
            if (wardenActiveEntity.setStartingPosition(entityStartpoint.getCenteredX(), entityStartpoint.getCenteredY())) {

                this.addActiveEntity(wardenActiveEntity);

                if (wardenImagePrefixes.length > wardenImagePrefixesIndex+1)
                    wardenImagePrefixesIndex++;
                else
                    wardenImagePrefixesIndex = 0;
            }
        }
    }

    private void addActiveEntity(ActiveEntity activeEntity) {

        CollisionDetector.addActiveEntity(activeEntity);
        
        if (activeEntity.getMapEntityType() == MapEntityType.Monster) {
            
            monsterActiveEntites.add(activeEntity);
            
        } else if (activeEntity.getMapEntityType() == MapEntityType.Boss) {
            
            bossActiveEntites.add(activeEntity);
            
        } else if (activeEntity.getMapEntityType() == MapEntityType.Warden) {
            
            wardenActiveEntites.add(activeEntity);
            
        }

        entityPanel.addEntity(activeEntity);
    }

    public void removeActiveEntity(ActiveEntity activeEntity) {

        if (activeEntity.getMapEntityType() == MapEntityType.Monster) {

            monsterActiveEntites.remove(activeEntity);
            
        } else if (activeEntity.getMapEntityType() == MapEntityType.Boss) {

            bossActiveEntites.remove(activeEntity);
            
        } else if (activeEntity.getMapEntityType() == MapEntityType.Warden) {

            wardenActiveEntites.remove(activeEntity);

            if (questionManager.getResolvedQuestions() != mapScanner.getWardenStartpointsCount() - wardenActiveEntites.size()) {
                
                this.endLevelLost();
            
            } else if (wardenActiveEntites.isEmpty() && questionManager.getResolvedQuestions() == mapScanner.getWardenStartpointsCount()) {
                
                mainPlayer.addScores(1000);
                this.endLevelWon();
                
            }
            
        } else if (activeEntity.getMapEntityType() == MapEntityType.MainPlayer || activeEntity.getMapEntityType() == MapEntityType.Heaven) {

            this.endLevelLost();
        }

        entityPanel.removeEntity(activeEntity);
        CollisionDetector.removeEntity(activeEntity);
    }

    private void stopAllComputerPlayers() {

        for (int i = 0; i < computerPlayers.size(); i++) {

            computerPlayers.get(i).stop();
        }
    }

    public void openQuestionPanel(ActiveEntity wardenActiveEntity) {

        questionManager.setWardenToRemove(wardenActiveEntity);

        questionPanel = new QuestionsPanel(questionManager);

        keyboardPlayer.removeKeyEvents();
        game.getGameFrame().addSubPanel(questionPanel);
    }

    public boolean isQuestionPanelActive() {

        if (questionPanel != null) {
            return true;
        } else {
            return false;
        }
    }

    public void closeQuestionPanel() {

        if (this.isQuestionPanelActive()) {
            game.getGameFrame().removeSubPanel(questionPanel);
        }

        questionPanel = null;
        keyboardPlayer.addKeyEvents();
    }
    
    public void setSongFilename(String songFilename) {
        
        this.songFilename = songFilename;
    }
    
    public void setQuestionFilename(String questionFilename) {
        
        this.questionFilename = questionFilename;
    }
    
    public void setMaxNumberMonsters(int maxNumberMonsters) {
        
        this.maxNumberMonsters = maxNumberMonsters;
    }
    
    public void setMainPlayerImagePrefix(String mainPlayerImagePrefix) {
        
        this.mainPlayerImagePrefix = mainPlayerImagePrefix;
    }
    
    public void setMonsterImagePrefix(String monsterImagePrefix) {
        
        this.monsterImagePrefix = monsterImagePrefix;
    }
    
    public void setBossImagePrefixes(String[] bossImagePrefixes) {
        
        this.bossImagePrefixes = bossImagePrefixes;
    }
    
    public void setWardenImagePrefixes(String[] wardenImagePrefixes) {
        
        this.wardenImagePrefixes = wardenImagePrefixes;
    }
    
    public void setMapAdjustmentX(int mapAdjustmentX) {
        
        this.mapAdjustmentX = mapAdjustmentX;
    }
    
    public void setMapAdjustmentY(int mapAdjustmentY) {
        
        this.mapAdjustmentY = mapAdjustmentY;
    }
    
    public int getActiveWardensCount() {
        
        return wardenActiveEntites.size();
    }
    
    public int getMapAdjustmentX() {
        
        return mapAdjustmentX;
    }
    
    public int getMapAdjustmentY() {
        
        return mapAdjustmentY;
    }    

    public GameStatusPanel getGameStatusPanel() {
        
        return gameStatusPanel;
    }
    
    public Game getGame() {
        
        return game;
    }
    
    public ActiveEntity getMainPlayer() {

        return this.mainPlayer;
    }
    
    public MapScanner getMapScanner() {
        
        return this.mapScanner;
    }

    public ShootPanel getShootPanel() {

        return this.shootPanel;
    }

    public GameMapPanel getMapPanel() {
        
        return this.gameMapPanel;
    }
    
    public EntityPanel getEntityPanel() {
        
        return entityPanel;
    }

    public void endLevel() {
        
        if (mainPlayer.getShootStatus()) {
            
            mainPlayer.setShootStatus(false);
            shootPanel.playSound();
        }
        
        
        
        this.stopThread();
        this.stopAllComputerPlayers();
        game.getGameFrame().removeAllSubPanel();
        timerGame.stop();
        CollisionDetector.reset();
        soundGameLevel.stop();
        
        keyboardPlayer.removeKeyEvents();
        
        game.addToHighscore(this.getMainPlayer().getScores(), this.timerGame.getElapsedSeconds(), this.questionManager.getResolvedQuestions());
    }

    public void endLevelLost() {

        this.endLevel();
        final Game thisGame = this.game;
        
        ActionListener buttonActionListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) {
                    
                    thisGame.getGameFrame().dispose();                       
                    thisGame.destroyLevel(thisGame.getLevel(thisGame.getLevelIndex())); 
                    BrainKilla.endGame();
                    
                }
            };        

        new AudioManager("yousuck.mp3").play();
        this.game.getGameFrame().addSubPanel(new EndLevelPanel(this, this.game.getGameFrame(), new Color(0, 0, 0), "youlose_image.png",508,433,new Color(255, 90, 44, 250), "button_thend.jpg",140,60, buttonActionListener));
    }

    public void endLevelWon() {
        
        this.endLevel();
        
        final Game thisGame = this.game;
        String buttonFilename = "button_backtoreality.jpg";
        
        game.gameLevelMoveNext();
        
        if (!game.gameLevelEof())
            buttonFilename = "button_nextlevel.jpg";
        
        ActionListener buttonActionListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) {
                    
                    thisGame.destroyLevel(thisGame.getLevel(thisGame.getLevelIndex()-1));     
                    
                    if (!thisGame.gameLevelEof()) {
                                              
                        thisGame.startNextLevel();                        
                    }     
                    else {
                        
                        thisGame.getGameFrame().dispose();                       
                        BrainKilla.endGame();                
                    }
                }
            };
        
        this.game.getGameFrame().addSubPanel(new EndLevelPanel(this, this.game.getGameFrame(), Color.PINK, "victory_image.png",504,433,new Color(255, 90, 44, 250), buttonFilename,140,60, buttonActionListener));
    }


    public void playSound() {
        
        soundGameLevel.loop();
    }

    public void stopSound() {
        
        soundGameLevel.stop();
    }
    
    public void pauseSound() {
        
        soundGameLevel.pause();
    }

    public void startSound(String songpath) {

        soundGameLevel = new AudioManager(songpath);
    }
}
