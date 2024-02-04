/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
  * @author Fiqih Amrullah
 */
public class Sentence 
{
    private String textsentence;
    private int rank; 
    private boolean isEliminated;    
    private TokenList listterms;
    
    private HashMap<String,Integer> currentstats ;

    public Sentence() 
    {
        listterms = new TokenList();
        isEliminated = false;
    }
    
    public void setStats(HashMap<String,Integer> currentstats )
    {
        this.currentstats = currentstats;
    }
    
    public void setTerms(TokenList listterms)
    {
        this.listterms = listterms;   
    }

    public Sentence(String textsentence) 
    {
        this.textsentence = textsentence;
    }

    public void setText(String textsentence) {
        this.textsentence = textsentence;
    }

    public TokenList getTermsList() 
    {
        return listterms;
    } 

    public String getText() 
    {
        return textsentence;
    }
     
    
    public String getUpCaseText() 
    {
        String output = textsentence.substring(0, 1).toUpperCase() + textsentence.substring(1);      
        
        return output;
    }

    public void setEliminated(boolean bEliminated) 
    {
        this.isEliminated = bEliminated;
    }
    
    public boolean isEliminated()
    {
        return isEliminated;
    }
            
    
    
    
}
