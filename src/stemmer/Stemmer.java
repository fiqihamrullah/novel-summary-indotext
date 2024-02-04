/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stemmer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsastrawi.morphology.DefaultLemmatizer;
import jsastrawi.morphology.Lemmatizer;

/**
 *
 * @author Fiqih Amrullah
 */
public class Stemmer
{
   Set<String> dictionary = new HashSet<String>();
   
    public Stemmer() 
    {
           dictionary = new HashSet<String>();
           
           InputStream in = Lemmatizer.class.getResourceAsStream("/root-words.txt");
          BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = br.readLine()) != null) 
            {
                dictionary.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Stemmer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public String stem(String fullword) 
    {
       
 
      
        Lemmatizer lemmatizer = new DefaultLemmatizer(dictionary);    
        
        return lemmatizer.lemmatize(fullword);
    }
    
}
