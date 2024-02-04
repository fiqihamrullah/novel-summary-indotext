/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stemmer;

import java.util.HashMap;
import java.util.Iterator;
import textprocessing.Token;

/**
 *
 * @author Fiqih Amrullah
 */
public class TesStem 
{
     
    public static void main(String args[])
    {
        HashMap<String,Token> maphasil = new HashMap<String, Token>();
        Stemmer stemmer = new Stemmer();
        String hasil= stemmer.stem("berhutang");
        System.out.println(hasil);
        Token token = new Token("Liza");
        Token token2 = new Token("Fiqih");
        maphasil.put("tes", token);
        maphasil.put("tes2", token2);
        for(Token k:maphasil.values())
        {
           // k.setTerm("Yuna");
            System.out.println(k.getTerm());    
        }
         
        for (String key : maphasil.keySet())
        {
            Token k = maphasil.get(key);
             k.setTerm("Yuna");
        }
        System.out.println("-------------------------");
       
        
        
         for(Token k:maphasil.values())
        {
            
            System.out.println(k.getTerm());    
        }
         /*
        Iterator it = maphasil.entrySet().iterator();
        while (it.hasNext()) {
            
        }*/
      
    }
}
