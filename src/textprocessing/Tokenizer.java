/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textprocessing;

import java.util.ArrayList;
import java.util.List;

 
public class Tokenizer {
    TokenList lsttoken;
    TokenList lsttokencurrent;
    
    StopWordList stpwlist;
    List<IndoText> lstDoc;

    public Tokenizer()
    {
        lsttoken = new TokenList();
        stpwlist = new StopWordList();
        stpwlist.loadStopWord();
   
    }
    
    

    public void setTokenList(TokenList lstTokenList)
    {
        lsttoken = lstTokenList;
    }

    

    public void tokenize(IndoText doc)
    {
       
      String[]  t =  doc.getIsiArtikel().toLowerCase().replaceAll("[\\W&&[^\\s]]","").split("\\W+");
      lsttokencurrent = new TokenList();
      for(int i=0;i<t.length;i++)
      {
        if (t[i].length()>1)
        {
            if(!stpwlist.findStopWord(t[i]))
           {
             t[i] = t[i].toLowerCase();
             t[i] = removeReadMark(t[i]);
             lsttokencurrent.addTerm(new Token(t[i]));
             Token tkn = lsttoken.checkTerm(t[i]);
             if (tkn==null)
             {             
                 lsttoken.addTerm(new Token(t[i]));   
             
             } 
            }
         }
      }

    }

   private String removeReadMark(String text)
   {
       String rm[] = new String[]{".",",","?","!",";","'","/","(",")"};
       char tb = text.charAt(text.length()-1);
       boolean foundmark=false;
       for(int i=0;i<rm.length;i++){
           if (tb==rm[i].toCharArray()[0]){
                foundmark=true;
                break;
           }
       }
       if (foundmark){
           text = text.substring(0, text.length()-1);
       }
       return text;
   }
   
    public TokenList getCurrentTokenList()
    {
        return lsttokencurrent;
    }
    
    public TokenList getTokenList()
    {
        return lsttoken;
    }
    
}