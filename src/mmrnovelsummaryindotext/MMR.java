/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmrnovelsummaryindotext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import textprocessing.Sentence;

/**
 *
 * @author Fiqih Amrullah
 */
public class MMR 
{
    public static double LAMBDA = 0.7;
    private SimilarityCounter sim;
    private ArrayList<Sentence> newSentences;
    private List<Sentence> wholelistsentences;
    private ArrayList<Integer> wholeidxsentences;
            
    public MMR(SimilarityCounter sim,List<Sentence> wholelistsentences) //menggunakan objek similarity, seluruh kalimat
    {
        this.sim = sim; //objek similarity
        newSentences = new ArrayList<Sentence>();
        wholeidxsentences = new ArrayList<Integer>(); //perlu semua kalimat
        this.wholelistsentences = wholelistsentences;        
    }
    
    
    public ArrayList<Sentence> getNewSentences()
    {
       int lowest = Integer.MAX_VALUE;
       int latest = Integer.MAX_VALUE;
       
       for(int i=0;i<wholeidxsentences.size();i++)
       {
           System.out.println(wholeidxsentences.get(i).intValue());
          
       }
       
      // System.out.println("==================TER-------ELIMINASI=========================");
       for(int i=0;i<wholelistsentences.size();i++)
       {
           if (wholelistsentences.get(i).isEliminated())
           {
             System.out.println(String.valueOf(i));
              newSentences.add(wholelistsentences.get(i));
           }
          
          
       }
        
        
       return newSentences;
    }
    
    
    private int getMaxSimilarity()
    {
        int imax=0; //kalau max mmr = 0 berhenti
        double max=0.0; //sim2 = kemiripan kalimat dengan kalimat yg dipilih
        for(int i=1;i<wholelistsentences.size();i++)
        {
             if (wholelistsentences.get(i).isEliminated()) 
             {
                 continue;
             }
             
             for(int j=0;j<wholeidxsentences.size();j++)
             {
                 double simval = sim.getSimilarityRevelance(i-1, wholeidxsentences.get(j).intValue()-1); 
                 if (max < simval) 
                 {
                     max = simval;
                     imax = wholeidxsentences.get(j).intValue();
                 }
             }
        }
        
        return imax;
    }
    
    
    public void countRelevenaces()
    {
        int summarizedSentence = 0;
        double valsummarizedSentence=0.0; 
        double mmr[] = new double[wholelistsentences.size()-1];
        
        double maxmmr = 0.0;
        int idxmaxmmr =0;
        
        int iterations = 0;
        
        while(true) 
        {
            System.out.println("-----------------------------------------------");
            System.out.println("Iterasi " + String.valueOf(iterations+1));
            if (iterations==0)
            {
                valsummarizedSentence =0;
            }
            maxmmr=0.0;
            System.out.println("Max MMR Reset -> " + maxmmr);
            int currentSelectedTemp=0;
            for(int i=1;i<wholelistsentences.size();i++)
            {
                if (wholelistsentences.get(i).isEliminated()) continue;
                
                if (iterations>0)
                {
                    valsummarizedSentence = sim.getSimilarityRevelance(i-1, summarizedSentence-1);
                }
                
                mmr[i-1] = LAMBDA * sim.getSimWithQuery(i-1) - (1-LAMBDA) * valsummarizedSentence; 
                System.out.println("MMR " + i + " : " + mmr[i-1]);
                if (maxmmr < mmr[i-1]) //kalau kalimat yg dibanding lebih besar kalimat sebelumnya maka yg dipilih kalimat sebelumnya
                {
                    maxmmr = mmr[i-1];
                    currentSelectedTemp = i;
                }

            }
            summarizedSentence = currentSelectedTemp;
            System.out.println("MaxMMR: " + maxmmr );
            
             if (maxmmr==0)
            {
                System.out.println("----------------------------*********** SELESAI *********-----------------------------------");
                break;
            }
            
            wholeidxsentences.add(summarizedSentence);
           // newSentences.add(wholelistsentences.get(summarizedSentence));
            wholelistsentences.get(summarizedSentence).setEliminated(true);
            System.out.println("Kalimat  :" + summarizedSentence);
           
            
            if (iterations>0)
            {
               summarizedSentence = getMaxSimilarity(); 
            }
            
            iterations++;            
           
            
        }
        
        
    }
        
         
}
