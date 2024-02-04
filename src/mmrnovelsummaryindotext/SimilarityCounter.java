/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmrnovelsummaryindotext;

import java.util.ArrayList;
import java.util.List;
import textprocessing.Sentence;
import textprocessing.Token;
import textprocessing.TokenList;

/**
 *
 * @author Fiqih Amrullah
 */
public class SimilarityCounter 
{
    private List<Sentence> listsentences;
    private TokenList globaltermlist; //
    private ArrayList<double[]> vecspace; //Bobot yang akan menampung bobot TF-IDF
    private double simwithquery[];
    private double similarityrevelance[][];

    public SimilarityCounter(List<Sentence> listsentences) //tidak membuat objek apapun, tapi mengambil kalimat dari proses sebelumnya
    {
        this.listsentences = listsentences;
        createGlobalToken(); //Membuat token sebanyak term, tetapi tidak boleh berulang termnya. setelah stemming
        
    }
    
    private double tf(String[] doc, String term) //utk menghitung berapa banyak term dalam kalimat
    {
        double n = 0;
    	for(String s:doc)
    		if(s.equalsIgnoreCase(term)) //Mencari term yg sama, tidak peduli huruf besar-kecil, klw ada tambah 1
    			n++;
    	return n;
    }

    public double getSimilarityRevelance(int i,int j) //sim2
    {
        return similarityrevelance[i][j];
    }
    
    public double getSimWithQuery(int i) //sim1
    {
        return simwithquery[i];
    }
    
  static double idf(List<Sentence> listsentence, String term)
  {
    	double n = 0; //banyaknya kalimat yg mengandung term
    	for(int i=0;i<listsentence.size();i++)  
        {
            for(int j=0;j<listsentence.get(i).getTermsList().getTotalTerm();j++) 
            {
                String s = listsentence.get(i).getTermsList().getTermAt(j).getTerm();
                if(s.equalsIgnoreCase(term))
                {
    			 n++;
    			 break;
	    	}
            }            
        }
        
    	return Math.log(listsentence.size()/n); //rumus IDF
    }
    
    private double cosSim(double[] a, double[] b)
    {
    	double dotp=0, maga=0, magb=0;
    	for(int i=0;i<a.length;i++)
        {
    		dotp+=a[i]*b[i]; //Sigma t1i * t2i
    		maga+=Math.pow(a[i],2); //sigma t1i^2 
    		magb+=Math.pow(b[i],2); //sigma t2i^2
    	}
    	maga = Math.sqrt(maga); //akar sigma t1i
    	magb = Math.sqrt(magb); //akar sigma t2i
    	double d = dotp / (maga * magb); //rumus cosSim
    	return d==Double.NaN?0:d;
    }
    
    
    public void countSimilarity()
    {
      vecspace = new ArrayList<double[]>(); 
        
      for(int i=0;i<listsentences.size();i++) 
      {
          double[] d = new double[globaltermlist.getTotalTerm()];
          for(int j=0;j<d.length;j++)
          {
              //w = tf * idf
               double t = tf(listsentences.get(i).getTermsList().toStringArray(),globaltermlist.getTermAt(j).getTerm());
               double id= idf(listsentences,globaltermlist.getTermAt(j).getTerm());
	    	d[j] = t * id;
          }
	  vecspace.add(d); //bobot W
      }
      
      simwithquery = new double[vecspace.size()-1];
      for(int i=1;i<listsentences.size();i++)
      {
         simwithquery[i-1] = cosSim(vecspace.get(0),vecspace.get(i));   //Kemiripan dengan judul
         System.out.println("SimQ  " +  i + ": " + simwithquery[i-1]);
      }
      
      similarityrevelance = new double[vecspace.size()-1][vecspace.size()-1];
      for(int i=1;i<listsentences.size();i++)
      {
         simwithquery[i-1] = cosSim(vecspace.get(0),vecspace.get(i));   
      }
      
      
      for(int i=1;i<listsentences.size();i++)
      {
          for(int j=1;j<listsentences.size();j++)
          {
              
              double measuresim =  cosSim(vecspace.get(i),vecspace.get(j)); //Membuat matriks kemiripan antarkalimat
              similarityrevelance[i-1][j-1] =measuresim; 
              similarityrevelance[j-1][i-1] =measuresim;
              System.out.println("D" + String.valueOf(i) + " Vs " + "D" + String.valueOf(j) + " -> " + measuresim);
              System.out.println("D" + String.valueOf(j) + " Vs " + "D" + String.valueOf(i) + " -> " + measuresim);               
          }
      }
      
       
    }
    
    private void createGlobalToken()
    {
        
        globaltermlist = new TokenList();
        for(int i=0;i<listsentences.size();i++)
        {
            TokenList termlist = listsentences.get(i).getTermsList();
            for(int j=0;j<termlist.getTotalTerm();j++)
            {
                
                 Token tkn = globaltermlist.checkTerm(termlist.getTermAt(j).getTerm());
                 if (tkn==null)
                 {             
                     globaltermlist.addTerm(new Token(termlist.getTermAt(j).getTerm()));   

                 } 
            }
        }
        
    }
    
        
        
    
}
