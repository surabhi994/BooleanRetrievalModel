package ir_project2;

import static ir_project2.Ir_project2.hm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;


public class daat {
    static void daatAnd(String []words, PrintWriter out) throws IOException
 {
     int compare=0;// variable to store number of comparisions
     int flag=0;
     int len=words.length; // stores the number of terms 
     int pointer[]= new int[len]; // array of pointers declared
     for(int i=0;i<len;i++)
          {
         pointer[i]=0; // initiallizing the value of all pointers to 0
     }
     LinkedList<Integer> temp= new LinkedList<Integer>(); // creation of temp link list
     LinkedList<Integer> daatAnd[]= new LinkedList[len]; // creating an array of link list to store posting list of all terms
     for(int i=0;i<len;i++){
         daatAnd[i]=hm.get(words[i]); // storing all posting of terms in array of link list
     }
      for(;;)
      {
          int least=50000; // initiallizing a value greater than number of documents
          for(int i=0; i<len;i++)
              
          {
              if(pointer[i]>= daatAnd[i].size()) // checking whether if a posting list has been traversed 
              {
                  flag=1;
                  break;
              }
              if(daatAnd[i].get(pointer[i])<least) // checking whether there is a doc id less tha the minimum value
              {
                  least=daatAnd[i].get(pointer[i]); //storing the smaller value in least
              }
              if(daatAnd[i]==null) //if there is a term with no posting
              {
                  flag=1;
                  break;
              }
              
          }
           if(flag==1)
           {
               break; // break out of infinite loop
               
           }
           int counter=0;
           for (int i=0;i<len;i++)
           {
            
            if(daatAnd[i].get(pointer[i])==least) // if there is a document if with the same value as least
            {
                counter++;// incrementing counter
                pointer[i]++; // incrementing pointer
                compare++; // incrementing compare as one comparision has taken place
            }
           }
           if(counter==len) // checking if the min valuedocument occurred in all the posting list
            {
              temp.add(least);  // add the document id to a temp link list
            }
      }//end of infinite loop
      out.println("DaatAnd");
      for(int i=0;i<len;i++)
             {
        out.print(words[i] + " ");
             }
         out.println();
      out.print( "Results: ");
      String s = temp.toString();
out.println((s.substring(1, s.length()-1)).replace(",", " "));// removal of [] and commas while displaying link list
      out.println("Number of documents in results : " + temp.size());
      out.println("Number of comparision: " + compare);
 }
 
    static void daatOr(String []words, PrintWriter out) throws IOException
 {
     int compare=0;// to check for number of comparision
     int flag=0;
     int len=words.length; // storing number if terms in len
     int pointer[]= new int[len];
     for(int i=0;i<len;i++)
          {
         pointer[i]=0; //initiallizing all pointer to 0
     }
     LinkedList<Integer> temp= new LinkedList<Integer>(); // creation of temp linklist
     LinkedList<Integer> daatAnd[]= new LinkedList[len]; // creation on array of link list
     for(int i=0;i<len;i++){
         daatAnd[i]=hm.get(words[i]); // storing posting of all terms in daatAnd[i]
     }
      for(;;)
      {
        
               int counter=0;
               int least=50000; //initiallizing value of min with a value greater than number of docs
          for(int i=0; i<len;i++)
              
          {
              if(!(pointer[i]>= daatAnd[i].size()))
              {
                  if(daatAnd[i].get(pointer[i])<least) // if doc id is less than minimum value
                  {
                    least=daatAnd[i].get(pointer[i]);
                  }
              }
              else
              {
                    counter++;
              }            
              
          }
           
           if(counter==len)
           {
               break;  // checking if end of all posting list has been reached then move out of the infinite loop
           }
           for (int i=0;i<len;i++)
           {
            
               if(!(pointer[i]>= daatAnd[i].size())){
                   compare++;
            if(daatAnd[i].get(pointer[i])==least) //if doc id is equal to least value
            {
               
                pointer[i]++;
                compare++;
            }
               }
           }
           temp.add(least);

      }//end of infinite loop

       
       out.println("DaatOr");
      for(int k=0;k<len;k++)
        {
           out.print(words[k] + " ");
        }
      out.println();
      out.print( "Results: ");
      String s = temp.toString();
      out.println((s.substring(1, s.length()-1)).replace(",", " "));
      out.println("Number of documents in results : "+ temp.size());
      out.println("Number of comparision" + compare);    
}  

}
