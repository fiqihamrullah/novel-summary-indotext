/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmrnovelsummaryindotext;

import java.util.ArrayList;
import java.util.List;
import stemmer.Stemmer;
import textprocessing.IndoText;
import textprocessing.Sentence;
import textprocessing.StopWordList;
import textprocessing.Token;
import textprocessing.TokenList;
 

/**
 *
 * @author Fiqih Amrullah
 */
public class TextPreProcessing
{
      private  static Sentence caseFolding(Sentence sentence)
      {         
          sentence.setText(sentence.getText().toLowerCase());
          return sentence;
      }
      
      public  static void caseFoldingSentences(List<Sentence> listsentences)
      {         
          for(int i=0;i<listsentences.size();i++)
          {
              listsentences.set(i,caseFolding(listsentences.get(i)));
          }
         
      }
     
      public static List<Sentence> splitArticle(IndoText article)
      {
          List<Sentence> listsentence = new ArrayList<Sentence>();
          String txtarticle = article.getIsiArtikel();
          
          Sentence sentencex = new Sentence();
          sentencex.setText(article.getJudul());
          listsentence.add(sentencex);
          
          String strsentence[] = txtarticle.split("\\.[\\s\\n]"); //Pemecahan Kalimat berdasarkan . dan setelah ada titik ada spasi atau enter
          for(int i=0;i<strsentence.length;i++)
          {
              Sentence sentence = new Sentence();
              /*
               if (i==0) 
              {
                    String fSentence =  strsentence[i].trim();
                    String arrkalimat[] = fSentence.split("[\\r\\n]+"); //Deteksi Judul
                    
                    sentence.setText(arrkalimat[0].trim());
                    listsentence.add(sentence);
                    
                    sentence = new Sentence();
                    sentence.setText(arrkalimat[1].trim());
                    listsentence.add(sentence);
                    continue;
                    
              }*/
             // System.out.println("Panjang Kalimat : " + strsentence[i].trim().length());
              if (strsentence[i].trim().length()>0)
              {
                  sentence.setText(strsentence[i].trim());
                  listsentence.add(sentence);
              }
          }
          
          return listsentence;
          
      }
      
      private static TokenList tokenizing(String strsentence)
      {
          TokenList termlist = new TokenList();
          String token[] = strsentence.split(" ");
          
          for(int i=0;i<token.length;i++)
          {
              if (!token[i].matches(".*\\d+.*"))//jika Angka maka dihapus
              {
                  if (token[i].length()!=0)
                  { 
                      String fintoken = token[i].replaceAll("[^a-zA-Z]", "").toLowerCase();
                     Token term = new Token();
                     term.setTerm(fintoken);
                     termlist.addTerm(term);
                  }            
              }
              
          }
          return termlist;
      }
      
      
      public static  void tokenizeSentence(List<Sentence> listsentences)
      {
          for(int i=0;i<listsentences.size();i++)
          {
              Sentence sentence = listsentences.get(i);
              TokenList termlist = tokenizing(sentence.getText());
              sentence.setTerms(termlist);              
          }
      }
      
      
      public static  void filterSentence(List<Sentence> listsentences)
      {
          StopWordList swl = new StopWordList();
          swl.loadStopWord();
          for(int i=0;i<listsentences.size();i++)
          {
              Sentence sentence = listsentences.get(i);
              TokenList termlist = sentence.getTermsList();
              TokenList newtermlist = new TokenList();
              for(int j=0;j<termlist.getTotalTerm();j++)
              {
                  if (!swl.findStopWord(termlist.getTermAt(j).getTerm())) 
                  {
                      newtermlist.addTerm(new Token(termlist.getTermAt(j).getTerm()));
                  }
              }
              
              sentence.setTerms(newtermlist);
          }
         
      }
      
      public static void stemSentences(List<Sentence> listsentences)
      {
          Stemmer stemmer = new Stemmer();
          StopWordList swl = new StopWordList();
          swl.loadStopWord();
          
           for(int i=0;i<listsentences.size();i++)
          {
              Sentence sentence = listsentences.get(i);
              TokenList termlist = sentence.getTermsList();
              
              for(int j=0;j<termlist.getTotalTerm();j++)
              {
                  Token term = termlist.getTermAt(j);
                  String strterm =term.getTerm();
                  String rootedstrterm = stemmer.stem(strterm);
                  if (!swl.findStopWord(termlist.getTermAt(j).getTerm())) 
                  {
                      term.setTerm(rootedstrterm);
                  }
              }
              
          }
      }
     
     
}
