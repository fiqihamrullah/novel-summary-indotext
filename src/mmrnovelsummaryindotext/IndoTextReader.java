/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmrnovelsummaryindotext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import textprocessing.IndoText;
 

/**
 *++
 * @author Fiqih Amrullah
 */
public class IndoTextReader 
{
     public  static IndoText readContent(File file)  
    {
        IndoText objtext = new IndoText();
        String content =  "";
        try {
            FileReader fReader = new FileReader(file);
            int charx=fReader.read(); 
            while(charx!=-1)
            {
             // System.out.print(charx);System.out.print(" "); 
               if (charx==13) 
               {
                  charx=fReader.read(); 
                  continue;    
               }else if (charx==10) 
               {
                 //content += "---";  
               }
               content += (char) charx;
               charx=fReader.read(); 
            }
        }  catch (FileNotFoundException ex) 
        {
            
        }catch (IOException ex) 
        {
           
        }      
        System.out.println("Total Kata :" + content.length());  
        String strsplit[] = content.split("[\\r\\n]+");
       
         
        objtext.setJudul(strsplit[0]);
        content = content.substring(strsplit[0].length()+1);
        objtext.setIsiArtikel(content);
        
        return objtext;
            
    }
}
