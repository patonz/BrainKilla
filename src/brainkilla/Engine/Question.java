
package brainkilla.Engine;

/**
 *
 * @author Jacopo Carletti
 */
public class Question {
    
    private String questions;
    private String answer;
    private String suggestion;
   

    public Question(String questions,String answer,String suggestion) 
    {
        this.questions = questions;
        this.answer = answer;
        this.suggestion = suggestion;
    }
    
    public String getQuestions()
    {
        
        return questions;
        
    }
    
    public String getAnswer()
    {
        
        return answer;
        
    }
    
    public String getSuggestion()
    {
        
        return suggestion;
        
    }
    
    @Override
    public String toString() {
        
        return "question: " + getQuestions() + ", answer: " + getAnswer() + ", suggestion: " + getSuggestion();
    }
   
}
