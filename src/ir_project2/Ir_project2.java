package ir_project2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.lang.Object;
import java.nio.charset.Charset;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.PostingsEnum;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;



public class Ir_project2
{
static String[]words;// array to store terms
static HashMap<String,LinkedList> hm = new HashMap<String, LinkedList>();// creation of hashmap
 static String path,input,output;
 

    public static void main(String[] args) throws IOException
    {    
        daat obj= new daat();
        taat obj1 =new taat();
        path=args[0];
        output=args[1];
        input=args[2];
        
          FileReader fis = new FileReader(input);// reading input file
          Scanner sc = new Scanner(fis);
     PrintWriter out = new PrintWriter(new FileWriter(output)); 
        
     
       
        String []language= new String[]{"text_nl", "text_fr", "text_de", "text_ja", "text_ru", "text_pt", "text_es","text_es", "text_it", "text_da", "text_no", "text_sv"}; // array storing names of language field
        FileSystem f;
        f = FileSystems.getDefault();
        try
        {
        Directory index= FSDirectory.open(f.getPath(path,new String[0]));
        IndexReader indexreader= DirectoryReader.open(index);
               
        MultiFields multifields = null;
        Fields fields = MultiFields.getFields(indexreader);
        for (int i=0; i<12; i++)  // loop to iterate through all given languages
        {
        Terms term_test = fields.terms(language[i]);
         TermsEnum term = term_test.iterator();
         
       BytesRef x;
       while((x = term.next())!=null)
                
       {
         
      PostingsEnum postings= multifields.getTermDocsEnum(indexreader,language[i],x,PostingsEnum.FREQS);
     
      LinkedList<Integer> linkedlist = new LinkedList<Integer>();
     
            int j;
            while((j = postings.nextDoc())!= PostingsEnum.NO_MORE_DOCS) // loop until no more documents are there in the posting
            {
                linkedlist.add(j); //
             }// end of first while
            hm.put(x.utf8ToString(),linkedlist);// storing the term and its posting in hashmap
            
          
         }//end of second while
      
        }//end of for
       
 String l;
  
  while(sc.hasNextLine()){ //loop until the last line of given input file
    l=sc.nextLine();  
    String[] words = l.split(" "); // storing the terms received seperately in an array
   
   
    GetPostings(words, out); 
    obj1.TaatAnd(words,out);
    obj1.TaatOr(words,out);
    obj.daatAnd(words,out);
    obj.daatOr(words,out);
       }  //end of while  
     }// end of try block
    
        catch(Exception e)
                {
                       e.printStackTrace();
                       System.out.println("The exception caught is :" + e);
                }
        out.close();// closing of out
    }// end of main
    
  static  void GetPostings(String words[], PrintWriter out) throws IOException
    {
        LinkedList<Integer> temp = new LinkedList<Integer>(); //creating a temporary linklist
          
          for(int b=0;b< words.length; b++)// loop till number of terms in the array word
        { 
            out.println("GetPostings");

               out.println(words[b]);  // printing the word
               out.print("Postings List: ");
               temp=hm.get(words[b]); //storing the posting of term in linked list remp
               String s = temp.toString();
               out.println((s.substring(1, s.length()-1)).replace(",", " ")); // to remove brackets and commas while displaying link list
        }   //end of for
      }  


}// end of class

             
  
    

    
    


