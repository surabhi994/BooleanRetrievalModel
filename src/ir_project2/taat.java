/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir_project2;

import static ir_project2.Ir_project2.hm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author HP-PC
 */
public class taat {
    static void TaatOr(String []words, PrintWriter out) throws IOException
  {
      
      
      int len= words.length; // storing the number of terms in len
     
      LinkedList<Integer> [] taatOr = new LinkedList[len]; //creation of array of link list
      int i=0;
       while(i<len)
           
      {
         taatOr[i] = hm.get(words[i]); // storing posting list of all terms in array of link list
         i++;
      }
          int comp=0;// variable to check no of comparison
      for (int j=0;j<len-1;j++)
      {
      LinkedList<Integer> temp = new LinkedList<Integer>(); // creation of temp link list
          int pointer1=0; int pointer2=0; // pointer 1 keeps track of taatOr[j] and pointer 2keeps track of taatOr[j+1]
             while(pointer1<taatOr[j].size() && pointer2<taatOr[j+1].size())
             { 
                 comp++;// increment as comparision is taking place
                 if (taatOr[j].get(pointer1) < taatOr[j+1].get(pointer2))// if doc id in taatOr[j] is smaller than in taatOr[j+1]
                 {
                     temp.add(taatOr[j].get(pointer1)); 
                     pointer1++; //increment pointer of taatOr[j]
                 } 
                 else if (taatOr[j].get(pointer1).equals(taatOr[j+1].get(pointer2)))// if doc id is same in both
                 {
                      temp.add(taatOr[j].get(pointer1));
                      pointer1++;
                      pointer2++;
                 }
                 else  
                 {
                    temp.add(taatOr[j+1].get(pointer2));// if doc id in taatOr[j+1] is smaller
                     pointer2++; 
                 }
                 
             }
             while(pointer2<taatOr[j+1].size())// if there are remaining elements in taatOr[j+1]
             {
               temp.add(taatOr[j+1].get(pointer2));
                     pointer2++;  
             }
             
             while(pointer1<taatOr[j].size())// if there are remaining ids in in taatOr[j]
             {
               temp.add(taatOr[j].get(pointer1));
                     pointer1++;  
             }
              
              taatOr[j+1]=temp; // storing the combined list of taatOr[j] and taatOr[j+1] in taatOr[j+1]
         }//for
      
      
         out.println("TaatOr");
         for(int k=0;k<len;k++)
             {
        out.print(words[k] + " ");
             }
         out.println();
        out.print("Results: " );
        String s = taatOr[len-1].toString();

out.println((s.substring(1, s.length()-1)).replace(",", " "));
       out.println("Number of documents in result :" + taatOr[len-1].size());
        out.println("Number of comparisions :" + comp);
       
         
      
  }
 
 static void TaatAnd( String []words, PrintWriter out) throws IOException
 {
     
      int len= words.length;
          
      LinkedList<Integer> [] taatAnd = new LinkedList[len];
      int t=0;
      while(t<len)
      {
         taatAnd[t] = hm.get(words[t]); //storing the posting of all terms in array of link list
         t++;
      }
         int comp=0; // varaible to store number of comparision
      for (int i=0;i<len-1;i++)
      {
      LinkedList<Integer> temp = new LinkedList<Integer>();
          int pointer1=0; int pointer2=0; //pointer 1 keeps track of taatOr[j] and pointer 2keeps track of taatOr[j+1]
             while(pointer1<taatAnd[i].size() && pointer2<taatAnd[i+1].size()) // until both list have not been traversed
             {
                 if (taatAnd[i].get(pointer1).equals(taatAnd[i+1].get(pointer2))) // if sam value is there in both taatAnd[i] and taatAnd[i+1]
                 {
                 comp++;
                      temp.add(taatAnd[i].get(pointer1)); 
                      pointer1++;
                      pointer2++;
                 }
                 else if (taatAnd[i].get(pointer1) < taatAnd[i+1].get(pointer2)) //if value in taatAnd[i] is smaller
                 {
                    
                 comp++;
                     pointer1++;
                 }
                 else // if value in taatAnd[i+1] is smaller
                 {
                    
                 comp++;
                     pointer2++; 
                 }
             }
             
             
              taatAnd[i+1]=temp;// storing the combined list of taatAnd[i] and taatAnd[i+1] in taatAnd[i+1]
         }//for
      
      
         out.println("TaatAnd");
        for(int i=0;i<len;i++)
             {
        out.print(words[i] + " ");
             }
         out.println();
        out.print("Results : ");
        String s = taatAnd[len-1].toString();

out.println((s.substring(1, s.length()-1)).replace(",", " "));
        out.println("Number of documents in result :" + taatAnd[len-1].size());
        
        out.println("Number of comparisions :" + comp );
   }
}
