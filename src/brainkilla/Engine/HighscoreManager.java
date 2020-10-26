
package brainkilla.Engine;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jacopo Carletti
 */
public class HighscoreManager {

    protected static final String filename = "highscores.txt";
    protected static final String path = "content";
    
    private static ArrayList<Highscore> highscoreList = new ArrayList<>();
    
    //Caricamento Arraylist con nome, punteggio, tempo trascorso, indovinelli risolti
    public static boolean load() {
       
        if (highscoreList.size()>0){
            
            highscoreList.clear();            
        }
        
        
        Scanner fileScan;        
        
        try {
            
            fileScan = new Scanner(new File(path + "\\" + filename));
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        int i = 1;

        String playerName = "";
        int scores = 0;
        int totalSecondsElapsed = 0;
        int questionsResolved = 0;

        while (fileScan.hasNextLine()) {

            try {

                switch(i) {

                    case 1:
                        playerName = fileScan.nextLine();
                        break;

                    case 2:
                        scores = Integer.parseInt(fileScan.nextLine());
                        break;

                    case 3:
                        totalSecondsElapsed = Integer.parseInt(fileScan.nextLine());
                        break;

                    case 4: 
                        questionsResolved = Integer.parseInt(fileScan.nextLine());
                }

                if (i == 4) {

                    highscoreList.add(new Highscore(playerName, scores, totalSecondsElapsed, questionsResolved));

                    playerName = "";
                    scores = 0;
                    totalSecondsElapsed = 0;
                    questionsResolved = 0;
                    
                    i = 1;
                }
                else
                    i++;



            } catch (Exception ex) {

                Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        
        fileScan.close();        
        Collections.sort(highscoreList);
         
        return true; 
   
    }
    
    
    public static ArrayList<Highscore> getHighscoreList() {
        
        return highscoreList;
        
    }
    
    public static int getScoreList(int i) {
        
        int sortedscore = 0;
        
        if(i<highscoreList.size()){
            
            Highscore thisHighscore = highscoreList.get(i);
            sortedscore = thisHighscore.getScores();
            
        }        
                    
        return sortedscore;
           
    }
    
    public static String getPlayerList(int i) {
        
        String playername = "";
        
        if(i<highscoreList.size()){
            
        Highscore thisHighscore = highscoreList.get(i);
        playername = thisHighscore.getPlayerName();
        
        }
         
        return playername;
                
    }
    
    public static int getTotalsecondsList(int i) {
        
        int totalsecond=0;
             
        if(i<highscoreList.size()){
            
        Highscore thisHighscore = highscoreList.get(i);
        totalsecond = thisHighscore.getTotalSecondsElapsed();
            
        }
        
        return totalsecond;
                
    }
    
    //Convesione da sistema decimale a sistema sessagesimale
    public static String getMinutesList(int i) {
        
        String totalminutes="";
        
        if(getTotalsecondsList(i)>60){
        
             if(getTotalsecondsList(i)%60<10){
             totalminutes=(getTotalsecondsList(i)/60) + ":0" + ((getTotalsecondsList(i)%60));}
             else if(getTotalsecondsList(i)%60>10){
                 totalminutes=(getTotalsecondsList(i)/60) + ":" + ((getTotalsecondsList(i)%60));
             }
        }
              
             else if (getTotalsecondsList(i)<10){
            totalminutes="00:0" + getTotalsecondsList(i);
        }
             else if (getTotalsecondsList(i)>10){
                 totalminutes="00:" + getTotalsecondsList(i);
             }
            
        
        return totalminutes;
    }
    
    public static int getWardendList(int i) {
        
        int questionsolved = 0;
        
        if(i<highscoreList.size()){
            
        Highscore thisHighscore = highscoreList.get(i);
        questionsolved = thisHighscore.getQuestionsResolved();
        
        }
        
        return questionsolved;
             
    }
    
    //Scrittura su file dei valori record (highscores.txt)
    public static boolean addHighscore(Highscore highscore) {
        
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        
        if (!new File(path).exists()) {
            
            new File(path).mkdir();
        }
        
        try {
            
            fileWriter = new FileWriter(path + "\\" + filename, true);
            bufferedWriter = new BufferedWriter(fileWriter);

        } catch (IOException ex) {
            
            Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
        
        try {
            
            bufferedWriter.write(highscore.getPlayerName());
            bufferedWriter.newLine();
            bufferedWriter.write("" + highscore.getScores());
            bufferedWriter.newLine();
            bufferedWriter.write("" + highscore.getTotalSecondsElapsed());
            bufferedWriter.newLine();
            bufferedWriter.write("" + highscore.getQuestionsResolved());
            bufferedWriter.newLine();
            bufferedWriter.close();
            
        } catch (IOException ex) {
            
            Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }

        
        try {
            
            fileWriter.close();
            
        } catch (IOException ex) {
            
            Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
        
        return true;
    }
    
}
