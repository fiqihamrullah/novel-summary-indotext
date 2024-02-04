/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textprocessing;

/**
 *
  * @author Fiqih Amrullah
 */
    public class IndoText 
    { 
        private String isi_artikel;           
        private String judul;

           public IndoText() 
           {

           }
    
          public IndoText(String isi_artikel) 
          {
              this.isi_artikel = isi_artikel;

          }

        public String getJudul() 
        {
            return judul;
        }

        public void setJudul(String judul) 
        {
            this.judul = judul;
        }    

        public void setIsiArtikel(String isi_artikel)
        {
            this.isi_artikel = isi_artikel;
        }

        public String getIsiArtikel()
        {
            return isi_artikel;
        } 
     }
