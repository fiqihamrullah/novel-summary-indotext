/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textprocessing;

import java.util.ArrayList;
import java.util.List;

 
public class Token
{
    private String term;
    
    private int ada;
    private double idf;
   
   

    public Token() 
    {
        
    }
    
 
    public Token(String term)
    {
         this.term = term;
    }
    
    
    
    public void setTerm(String term)
    {
       // System.out.println(term);
        this.term = term;
    }
 

    public String getTerm()
    {
        return term;
    }

 
 
    
}
