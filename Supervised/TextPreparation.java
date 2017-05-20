/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentiment_analysis;

import static edu.stanford.nlp.math.SloppyMath.round;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Kostas
 */
public class TextPreparation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
          MaxentTagger tagger = new MaxentTagger("taggers/english-bidirectional-distsim.tagger"); //Αρχικοποιούμε τον tagger 
          PorterStemmer st = new PorterStemmer();
          HashMap<String,Integer> words_pos = new HashMap<>();
         File[] allfiles = new File("C:\\Users\\Κωστής\\Documents\\data\\train\\pos").listFiles();
         int total_pos=0;
           BufferedReader in = null;
          
           for(File f : allfiles){
             if(f.getName().endsWith(".txt")){
                  in = new BufferedReader(new FileReader(f));
                  StringBuilder sb = new StringBuilder();
                  String s = null;
                  while( (s = in.readLine())!= null ){
                     s=s.replace("<br />", "");
                     s=s.replace("(", "");
                     s=s.replace(")", "");
                     s=s.replace(".", "");
                     s=s.toLowerCase();
                     sb.append(s);
                    
                     
                 }
                 String[] tokenizedTermsOfDocument = sb.toString().replaceAll("[\\W&&[^\\s]]", "").split("\\W+");
                 
              
                 for( String word : tokenizedTermsOfDocument){
                     
                     char[] array = word.toCharArray();
                     for(int i=0;i<array.length;i++){
                         st.add(array[i]);
                     }
                     st.stem();
                     word = st.toString();
                             
                      word = tagger.tagString(word); 
                      
   
                      
                       if(word.contains("_JJ") || word.contains("_RB")||word.contains("_VB")){
                         
                           if(words_pos.containsKey(word)){
                               int temp = words_pos.get(word);
                              temp++;
                              words_pos.replace(word, temp);
                              total_pos++;
                           }else{
                               words_pos.put(word, 1);
                               total_pos++;
                           }
                           
                       }
                 }
                 
             }
           
           }
          
           
           //κάνουμε το ίδιο για τα Negative
         HashMap<String,Integer> words_neg = new HashMap<>();
         File[] allfiles2 = new File("C:\\Users\\Κωστής\\Documents\\data\\train\\neg").listFiles();
         int total_neg=0;
           BufferedReader in2 = null;
           for(File f2 : allfiles2){
             if(f2.getName().endsWith(".txt")){
                  in2 = new BufferedReader(new FileReader(f2));
                  StringBuilder sb2 = new StringBuilder();
                  String s2 = null;
                  while( (s2 = in2.readLine())!= null ){
                     s2=s2.replace("<br />", "");
                     s2=s2.replace("(", "");
                     s2=s2.replace(")", "");
                     s2=s2.replace(".", "");
                     s2=s2.toLowerCase();
                     sb2.append(s2);
                    
                     
                 }
                 String[] tokenizedTermsOfDocument2 = sb2.toString().replaceAll("[\\W&&[^\\s]]", "").split("\\W+");
                 
              
                 for( String word2 : tokenizedTermsOfDocument2){
                     
                     char[] array2 = word2.toCharArray();
                     for(int i=0;i<array2.length;i++){
                         st.add(array2[i]);
                     }
                     st.stem();
                     word2 = st.toString();
                             
                      word2 = tagger.tagString(word2); 
                      
   
                      
                       if(word2.contains("_JJ") || word2.contains("_RB")||word2.contains("_VB")){
                         
                           if(words_neg.containsKey(word2)){
                               int temp2 = words_neg.get(word2);
                              temp2++;
                              words_neg.replace(word2, temp2);
                              total_neg++;
                           }else{
                               words_neg.put(word2, 1);
                               total_neg++;
                           }
                           
                       }
                 }
                 
             }
           
           }
     
          //System.out.println(words_neg);
           //System.out.println(total_neg); 
           int total = total_pos + total_neg;
           
           
           long endTime   = System.currentTimeMillis();
           
           long totalTimeTrain = (endTime - startTime)/1000;
           System.out.print("train time: "+totalTimeTrain+ " seconds ");
           
           
           long startTime2   = System.currentTimeMillis();
          File[] allfiles3 = new File("C:\\Users\\Κωστής\\Documents\\data\\test").listFiles();
          BufferedReader in3 = null;
          // int file_counter=0;
           
           BufferedWriter bw = new BufferedWriter(new FileWriter("predictions.txt"));
            for(File f3 : allfiles3){
                double posibility_pos =(double)total_pos/(double)total;
                double posibility_neg =(double)total_neg/(double)total;
                
                if(f3.getName().endsWith(".txt")){
                    in3 = new BufferedReader(new FileReader(f3));
                  StringBuilder sb3 = new StringBuilder();
                  String s3 = null;
                  while( (s3 = in3.readLine())!= null ){
                     s3=s3.replace("<br />", "");
                     s3=s3.replace("(", "");
                     s3=s3.replace(")", "");
                     s3=s3.replace(".", "");
                     s3=s3.toLowerCase();
                     sb3.append(s3);
                    
                     
                 }
                 String[] tokenizedTermsOfDocument3 = sb3.toString().replaceAll("[\\W&&[^\\s]]", "").split("\\W+");
                     for( String word3 : tokenizedTermsOfDocument3){
                         char[] array3 = word3.toCharArray();
                         for(int i=0;i<array3.length;i++){
                             st.add(array3[i]);
                           }
                           st.stem();
                           word3 = st.toString();
                             
                           word3 = tagger.tagString(word3); 
                           if(word3.contains("_JJ") || word3.contains("_RB")||word3.contains("_VB")){
                              // System.out.println(word3);
                               if(words_pos.containsKey(word3) && words_neg.containsKey(word3)){ //problhma..πρεπει αν υπάρχουν και στα δύο αλλιώς αν έστω και μία λέξη να μην υπάρχει και βάλουμε 0 μηδενίζεται όλη η πιθανότητα
                                  
                                   int frequency_pos = words_pos.get(word3);
                                   posibility_pos = posibility_pos * ((double)frequency_pos/(double)total_pos);
                                   
                                   int frequency_neg = words_neg.get(word3); 
                                    posibility_neg = posibility_neg * ((double)frequency_neg/(double)total_neg);
                                 
                                   
                               }
                               
                               
                               
                             
                           }
                           
                     }
                 
                }
                //System.out.println("posib negative = "+posibility_neg);
               //System.out.println("posib positive = "+posibility_pos);
                 //System.out.println("DOCUMENT : "+f3.getName());
                if(posibility_neg > posibility_pos){
                   // System.out.println("NEGATIVE");
                    String temp = f3.toString().replace("C:\\Users\\Κωστής\\Documents\\data\\test\\","");
                    temp = temp.replace(".txt","");
                    bw.write(temp+ " 0 \n");
                    bw.newLine();
                  
                }else if(posibility_neg < posibility_pos){
                    //System.out.println("POSITIVE");
                    String temp = f3.toString().replace("C:\\Users\\Κωστής\\Documents\\data\\test\\","");
                    temp = temp.replace(".txt","");
                    bw.write(temp+ " 1 \n");
                    bw.newLine();
                }else{
                    String temp = f3.toString().replace("C:\\Users\\Κωστής\\Documents\\data\\test\\","");
                    temp = temp.replace(".txt","");
                    bw.write(temp+ " undefined \n");
                    bw.newLine();
                }
               
               // file_counter++;
                 
                
            }
            bw.close();
             long endTime2   = System.currentTimeMillis();
         long totalTimeTest = (endTime2 - startTime2)/1000;
         
         System.out.print("test time: "+totalTimeTest +" seconds ");
           
    }
    
}
