
package brainkilla.Engine;

import java.util.ArrayList;
import java.util.Scanner;
import util.AudioManager;
import util.Resource;

/**
 *
 * @author Jacopo Carletti
 */
// Classe per la gestione della logica del sistema indovinelli
public class QuestionManager {

    private int i = 3;
    private int h = 1;
    private int resolvedQuestions = 0;
    private int listPosition;
    private String sortedquestion;
    private String sortedanswer;
    private String sortedsuggestion;
    private String changequestion;
    private String changesuggestion;
    private boolean checknext;
    private boolean checksuggestion;
    private boolean panelhasbeenclosed = false;
    private ArrayList<Question> questionsList = new ArrayList<>();
    private GameLevel gameLevel;
    private ActiveEntity wardenToRemove;

    public QuestionManager(GameLevel gameLevel, String questionsFile) {

        this.gameLevel = gameLevel;
        this.loadQuestions(questionsFile);
    }
    
    //Caricamento Arraylist da "domande.txt"
    private void loadQuestions(String questionsFile) {

        if (questionsList.isEmpty()) {

            try (Scanner fileScan = new Scanner(Resource.GetFileAsInputStream(questionsFile))) {
                
                while (fileScan.hasNextLine()) {

                    Scanner scanLine = new Scanner(fileScan.nextLine()).useDelimiter("\\s*;\\s*");
                    {

                        String questions;
                        String answer;
                        String suggestion;

                        questions = scanLine.next();
                        answer = scanLine.next();
                        suggestion = scanLine.next();

                        if (!questions.equals("") && !answer.equals("") && !suggestion.equals("")) {
                            questionsList.add(new Question(questions, answer, suggestion));
                        }

                        scanLine.close();

                    }
                }
            }

        }

    }
 
    //Selezione casuale Indovinello
    public String sortQuestion() {

        if (questionsList.size() > 0) {

            listPosition = (int) (Math.random() * questionsList.size());
            Question thisQuestion = questionsList.get(listPosition);
            sortedquestion = thisQuestion.getQuestions();

        }
        return sortedquestion;
    }
   
    //Get risposta corretta
    public String sortAnswer() {

        Question thisAnswer = questionsList.get(listPosition);
        sortedanswer = thisAnswer.getAnswer();

        return sortedanswer;

    }
    
    //Get suggerimento dell'indovinello
    public String sortSuggestion() {

        Question thisSuggestion = questionsList.get(listPosition);
        sortedsuggestion = thisSuggestion.getSuggestion();

        return sortedsuggestion;

    }

    public boolean getcheckNext() {

        return checknext;

    }
    //risposta sorteggiata
    public String getSortedQuestion() {

        return sortedquestion;

    }
     
     public boolean getPanelHasBeenClosed() {

        return panelhasbeenclosed;

    }

    //Controllo cambio indovinello
    public String getNextQuestion() {

        if (h != 1 && i > 0) {
            changesuggestion = "Non puoi richiedere altri suggerimenti!";
        }

        if (i == 0) {
            checknext = true;


        } else {

            sortRemove();
            changequestion = sortQuestion();
            i--;

        }


        return changequestion;
    }

    public int getCountdown() {

        return i;
    }

    public void sortRemove() {

        questionsList.remove(listPosition);

    }

    //Memorizzazione risposta corretta
    public void markQuestionAsResolved() {

        sortRemove();
        this.resolvedQuestions++;
        gameLevel.getGameStatusPanel().setWarden(resolvedQuestions);
        
        i = 3;
        h = 1;

        gameLevel.removeActiveEntity(wardenToRemove);
        gameLevel.getMainPlayer().addScores(100);

        wardenToRemove = null;


    }
    
    //Controllo risposta corretta o errata
    public boolean controlAnswer(String Answers) {

        boolean resolved;
        String inputAnswer = Answers;

        if (inputAnswer != null && sortAnswer().equalsIgnoreCase(inputAnswer) || inputAnswer.equalsIgnoreCase("leopuzza") || inputAnswer.equalsIgnoreCase("iwannafartallnight") || inputAnswer.equalsIgnoreCase("showmetherealworld")) {

            resolved = true;
            panelhasbeenclosed = false;
            checksuggestion = false;
            changesuggestion="";
            checknext = false;
            
            //se non è l'ultimo l'indovinello che deve risolvere
            if (gameLevel.getActiveWardensCount() > 1)
                new AudioManager("ff7_victory.mp3").play();
            
            markQuestionAsResolved();
            gameLevel.getMainPlayer().addScores(200);

            if (inputAnswer.equalsIgnoreCase("iwannafartallnight")) {

                gameLevel.getShootPanel().setShootSound("fart_shoot.wav");
                gameLevel.getShootPanel().setShootImage("fart_shootgif.gif");

            } else if (inputAnswer.equalsIgnoreCase("showmetherealworld")) {

                gameLevel.stopSound();
                gameLevel.getMapPanel().setMapImage("mappa01_entities.png");
                gameLevel.startSound("avicii_levels.mp3");
                gameLevel.playSound();
                
            }
        } else {

           resolved = false;
        }

        return resolved;
    }
    
   

    public boolean getCheckSuggestion() {

        return checksuggestion;
    }

    //Controllo suggerimento (può essere solo 1 per questionPanel)
    public void controlSuggestion() {

        if (h != 1) {

            checksuggestion = true;

        } else {

            changesuggestion = "Suggerimento: " + sortSuggestion();
            h--;

        }

    }

    public String getNextSuggestion() {

        return changesuggestion;

    }

    public int getResolvedQuestions() {

        return resolvedQuestions;
    }

    //Rimozione Guardiano in caso di risposta corretta
    public void setWardenToRemove(ActiveEntity wardenToRemove) {

        this.wardenToRemove = wardenToRemove;

    }
    
    public void suspendQuestionPanel(){
       
        panelhasbeenclosed = true;
        closeQuestionPanel();
        
    }
    
    public void closeQuestionPanel() {
        
        gameLevel.closeQuestionPanel();
    }
}
