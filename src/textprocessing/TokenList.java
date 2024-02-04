/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textprocessing;

import java.util.ArrayList;
import java.util.List;
 
public class TokenList
{
    List<Token> lstterm;

    public TokenList()
    {
          lstterm = new ArrayList<Token>();
    }

    public TokenList(List<Token> lstterm) {
        this.lstterm = lstterm;
    }

    public void addTerm(Token term)
    {
        lstterm.add(term);

    }

    public Token getTermAt(int idx)
    {
        return lstterm.get(idx);
    }

    public int getTotalTerm()
    {
        return lstterm.size();
    }

    
    public String[] toStringArray() 
    {
        String[] temp = new String[lstterm.size()];
        for(int i=0;i<temp.length;i++)
        {
            temp[i] = lstterm.get(i).getTerm();        
        }
        return temp;
    }
    
    public Token checkTerm(String strterm )
    {
        Token term=null;
        for(int i=0;i<lstterm.size();i++)
        {
             
            if (strterm.equals(lstterm.get(i).getTerm()) ) 
            {
                term=lstterm.get(i);
                break;
            }
        }
        return term;
    }

     
      
      


}
