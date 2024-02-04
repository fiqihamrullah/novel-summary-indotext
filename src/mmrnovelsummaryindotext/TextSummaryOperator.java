/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmrnovelsummaryindotext;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import textprocessing.IndoText;
import textprocessing.Sentence;
import textprocessing.TokenList;
 

/**
 *
 * @author Fiqih Amrullah
 */
public class TextSummaryOperator 
{
    private IndoText txtArticle;
    private List<Sentence> listsentences;
    private int jkalimat_teringkas;
    
    private JTextArea jtxthasilpreprocess,jtxtLogTokenizing,jTxtLogFiltering,jTxtLogStemming;
    
    public TextSummaryOperator(IndoText txtArticle,JTextArea jtxtpreprocess,JTextArea jtxtLogTokenizing,JTextArea jTxtLogFiltering,JTextArea jTxtLogStemming) 
    {
        this.txtArticle = txtArticle;
        this.jtxthasilpreprocess = jtxtpreprocess;
        this.jtxtLogTokenizing = jtxtLogTokenizing;              
        this.jTxtLogFiltering = jTxtLogFiltering;
        this.jTxtLogStemming = jTxtLogStemming;
    }
    
    
    private void addToLog(String txt)
    {
       String preTxt = jtxthasilpreprocess.getText();
       preTxt += txt + "\n";
       jtxthasilpreprocess.setText(preTxt);
      
    }
    
    private void addToLogFiltering(String txt)
    {
       String preTxt = jTxtLogFiltering.getText();
       preTxt += txt + "\n";
       jTxtLogFiltering.setText(preTxt);
      
    }
    
    private void addToLogStemming(String txt)
    {
       String preTxt = jTxtLogStemming.getText();
       preTxt += txt + "\n";
       jTxtLogStemming.setText(preTxt);
      
    }
    
     private void addToLogToken(String txt)
    {
       String preTxt = jtxtLogTokenizing.getText();
       preTxt += txt + "\n";
       jtxtLogTokenizing.setText(preTxt);
      
    }
    
    private void showSentences()
    {
        addToLog("------------------------------------------------");
        for(int i=0;i<listsentences.size();i++)
       {
           addToLog(String.valueOf(i) + "." +  listsentences.get(i).getText());
       }
    }
    
    private void showTokens()
    {
       int tottoken =0;
       addToLogToken("------------------------------------------------");
       for(int i=0;i<listsentences.size();i++)
       {
           addToLogToken("Kalimat Ke-" + String.valueOf(i+1));
           TokenList tokenlist = listsentences.get(i).getTermsList();
           for(int j=0;j<tokenlist.getTotalTerm();j++)
           {
               addToLogToken("Token:" + tokenlist.getTermAt(j).getTerm());
               tottoken++;
           }
       }
       addToLogToken("Total Token: " + tottoken);
       addToLogToken("");addToLogToken("");addToLogToken("");
    }
    
    
    private void showTokensStemming()
    {
       int tottoken =0;
       addToLogStemming("------------------------------------------------");
       for(int i=0;i<listsentences.size();i++)
       {
           addToLogStemming("Kalimat Ke-" + String.valueOf(i+1));
           TokenList tokenlist = listsentences.get(i).getTermsList();
           for(int j=0;j<tokenlist.getTotalTerm();j++)
           {
               addToLogStemming("Token:" + tokenlist.getTermAt(j).getTerm());
               tottoken++;
           }
       }
       addToLogStemming("Total Token: " + tottoken);
       addToLogStemming("");addToLogStemming("");addToLogStemming("");
    }
    
    
    private void showTokensFiltering()
    {
       int tottoken =0;
       addToLogFiltering("------------------------------------------------");
       for(int i=0;i<listsentences.size();i++)
       {
           addToLogFiltering("Kalimat Ke-" + String.valueOf(i+1));
           TokenList tokenlist = listsentences.get(i).getTermsList();
           for(int j=0;j<tokenlist.getTotalTerm();j++)
           {
               addToLogFiltering("Token:" + tokenlist.getTermAt(j).getTerm());
               tottoken++;
           }
       }
       addToLogFiltering("Total Token: " + tottoken);
       addToLogFiltering("");addToLogFiltering("");addToLogFiltering("");
    }
    
    private void preProcess()
    {
       Sentence sentence = new Sentence();         
       listsentences = TextPreProcessing.splitArticle(txtArticle);  
       System.out.println("Total Kalimat : " + listsentences.size());
       addToLog("Total Kalimat : " + listsentences.size());     
       showSentences();
       TextPreProcessing.caseFoldingSentences(listsentences);
       System.out.println("CaseFolding Kalimat");
       addToLog("CaseFolding Kalimat");
       showSentences();
       TextPreProcessing.tokenizeSentence(listsentences);       
       System.out.println("Tokenizing Kalimat...");
       addToLogToken("Tokenizing Kalimat...");
       showTokens();
       TextPreProcessing.filterSentence(listsentences);
       System.out.println("Filtering Kalimat...");
       addToLogFiltering("Filtering Kalimat...");
       showTokensFiltering();
       TextPreProcessing.stemSentences(listsentences);
       System.out.println("Stemming Kalimat...");       
       addToLogStemming("Stemming Kalimat...");
       showTokensStemming();
    }

    public int getNumberOfSummarizedText() 
    {
        return jkalimat_teringkas;
    }
    
    
    
    public IndoText summarizeArticle()
    {
        //Pra Proses
      preProcess();  
      
      // Menghitung Similarity
      SimilarityCounter sim = new SimilarityCounter(listsentences);
      sim.countSimilarity();
      
      // Menghitung MMR
      MMR mmr = new MMR(sim, listsentences);
      mmr.countRelevenaces();
      
       //Membentuk Kalimat Baru / Ringkasan Baru
      ArrayList<Sentence> newsentences = mmr.getNewSentences();
      IndoText article = new IndoText();
      jkalimat_teringkas = newsentences.size();
      String txt="";
      for(int i=0;i<newsentences.size();i++)
      {       
          Sentence sentence =newsentences.get(i);
          txt += sentence.getUpCaseText();
          txt +="."; 
      }
        
      System.out.println("Total Kata Teringkas:" + txt.length());
      article.setIsiArtikel(txt);
      return article;    
    }
    
}
