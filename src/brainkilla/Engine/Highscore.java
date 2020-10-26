
package brainkilla.Engine;

/**
 *
 * @author Jacopo Carletti
 */
public class Highscore implements Comparable<Highscore>   {
    
    private String playerName;
    private int scores;
    private int totalSecondsElapsed;
    private int questionsResolved;
    
    public Highscore(String playerName) {
        
        this.playerName = playerName;
        this.scores = 0;
        this.totalSecondsElapsed = 0;
        this.questionsResolved = 0;
    }
    
    public Highscore(String playerName, int scores, int totalSecondsElapsed, int questionsResolved) {
        
        this.playerName = playerName;
        this.scores = scores;
        this.totalSecondsElapsed = totalSecondsElapsed;
        this.questionsResolved = questionsResolved;        
    }
    
    public void addScores(int scores) {
        
        this.scores = this.scores + scores;
    }
    
    public void addQuestionsResolved(int questionsResolved) {
        
        this.questionsResolved = this.questionsResolved + questionsResolved;
    }
    
    public void addSecondsElapsed(int seconds) {
        
        this.totalSecondsElapsed = this.totalSecondsElapsed + seconds;
    }
    
    public String getPlayerName() {
        
        return playerName;
    }
    
    public int getTotalSecondsElapsed() {
        
        return totalSecondsElapsed;
    }
    
    public int getScores() {
        
        return scores;
    }
    
    public int getQuestionsResolved() {
        
        return questionsResolved;
    }
    
    @Override
    public int compareTo(Highscore other)
    {
        final int LESS = 1;
        final int EQUAL = 0;
        final int GREATER = -1;
        
        if (scores == other.scores) {
        
            return EQUAL;
            
        } else if (scores < other.scores) {
        
            return LESS;
            
        } else {
        
            return GREATER;
        }
    }    
    
    @Override
    public String toString() {
        
        return "playerName: " + getPlayerName() + ", scores: " + getScores() + ", totalSecondsElapsed: " + getTotalSecondsElapsed() + ", questionsResolved: " + getQuestionsResolved();
    }
}
