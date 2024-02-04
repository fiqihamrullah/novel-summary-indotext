/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textprocessing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
public class StopWordList {
     List<String> lststopword;

    public StopWordList() 
    {
        lststopword = new ArrayList<String>();
    }

    public void loadStopWord()
    {
        try
        {
           FileReader freader = new FileReader("stopwordlist.txt");
           int data=0;
           String str="";
           while((data=freader.read())!=-1){              
              if (data==59){
                   lststopword.add(str);

                   str="";
              }else{
                   str += (char)data;
              }
           }
          

        }catch(FileNotFoundException ex){
            
        }catch(IOException ex){

        }

    }

    public boolean findStopWord(String str)
    {
        boolean ada=false;
        for(int i=0;i<lststopword.size();i++)
        {
            if (lststopword.get(i).equals(str)){
                ada=true;
                break;
            }
        }
        return ada;
    }
     

}
