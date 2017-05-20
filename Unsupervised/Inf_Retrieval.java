/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf_retrieval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author George 
 */
public class Inf_Retrieval {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        File folder = new File("C:\\Users\\Κωστής\\Documents\\data\\test");
        File[] listOfFiles = folder.listFiles();
        BufferedReader in; 
        listOfFiles = folder.listFiles();
        HashSet<String> pos = new HashSet<>();
        HashSet<String> neg = new HashSet<>();
        HashMap<String,HashSet<String>> index = new HashMap<>();
        HashMap<String,HashSet<String>> file_index = new HashMap<>();
        MaxentTagger tagger = new MaxentTagger("taggers\\english-bidirectional-distsim.tagger");
        double inf = Double.POSITIVE_INFINITY;
        int countio =0;
        for (File file : listOfFiles) 
        {
            //System.out.println(file.toPath());
            String line = "";
            in = new BufferedReader(new FileReader(file.getAbsolutePath()));
            while(in.ready())
            {
              line = line.concat(in.readLine());
            }
            line=line.replace("<br />", "");
                     line=line.replace("(", "");
                     line=line.replace(")", "");
                     line=line.replace(".", "");
                     line=line.replaceAll("\\d", "");
                     line=line.toLowerCase();
                     
            if(line.contains("great") || line.contains("good") || line.contains("excellent") || line.contains("amazing"))
            {
                pos.add(file.toString());
            }
            if(line.contains("poor") || line.contains("bad") || line.contains("suck") || line.contains("terrible") )
            {
                neg.add(file.toString());
            }
            
            
            String[] tokens = line.toLowerCase().split(" ");
            for(int i=0;i<tokens.length-2;i++)
            {
                String a = tokens[i];
                String b = tokens[i+1];
                String c = tokens[i+2];
                String a_t = tagger.tagString(a);
                String b_t = tagger.tagString(b);
                String c_t = tagger.tagString(c);
              
                
                   if (a_t.contains("_JJ"))
                           {
                               if (b_t.contains("_JJ"))
                               {
                                   a = a.concat(" " +b);
                                   if(index.containsKey(a))
                                   {
                                       HashSet<String> temp = index.get(a);
                                       temp.add(file.toString());
                                       index.replace(a, temp);
                                       
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(file.toString());
                                       index.put(a, temp);
                                       
                                   }
                                   
                                   if(file_index.containsKey(file.toString()))
                                   {
                                       HashSet<String> temp = file_index.get(file.toString());
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                                   

                               }
                               if (b_t.contains("_NN") || b_t.contains("_NNS"))
                               {
                                    a = a.concat(" " +b);
                                    if(index.containsKey(a))
                                   {
                                       HashSet<String> temp = index.get(a);
                                       temp.add(file.toString());
                                       index.replace(a, temp);
                                       
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(file.toString());
                                       index.put(a, temp);
                                       
                                   }
                                   
                                   if(file_index.containsKey(file.toString()))
                                   {
                                       HashSet<String> temp = file_index.get(file.toString());
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                                    

                               }
                            }
                    if (a_t.contains("_RB") || a_t.contains("_RBR") || a_t.contains("_RBS"))
                    {
                        if (b_t.contains("_JJ"))
                        {
                             a = a.concat(" " +b);
                             if(index.containsKey(a))
                                   {
                                       HashSet<String> temp = index.get(a);
                                       temp.add(file.toString());
                                       index.replace(a, temp);
                                       
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(file.toString());
                                       index.put(a, temp);
                                       
                                   }
                                   
                                   if(file_index.containsKey(file.toString()))
                                   {
                                       HashSet<String> temp = file_index.get(file.toString());
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                             

                        }
                        if (b_t.contains("_VB") || b_t.contains("_VBD") || b_t.contains("_VBN") || b_t.contains("_VBG") )
                        {
                             a = a.concat(" " +b);
                             if(index.containsKey(a))
                                   {
                                       HashSet<String> temp = index.get(a);
                                       temp.add(file.toString());
                                       index.replace(a, temp);
                                       
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(file.toString());
                                       index.put(a, temp);
                                       
                                   }
                                   
                                   if(file_index.containsKey(file.toString()))
                                   {
                                       HashSet<String> temp = file_index.get(file.toString());
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                            

                        }
                    }
                    if (a_t.contains("_NN") || a_t.contains("_NNS"))
                    {
                        if (b_t.contains("_JJ"))
                        {
                             a = a.concat(" " +b);
                             if(index.containsKey(a))
                                   {
                                       HashSet<String> temp = index.get(a);
                                       temp.add(file.toString());
                                       index.replace(a, temp);
                                       
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(file.toString());
                                       index.put(a, temp);
                                       
                                   }
                                   
                                   if(file_index.containsKey(file.toString()))
                                   {
                                       HashSet<String> temp = file_index.get(file.toString());
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                                   else
                                   {
                                       HashSet<String> temp = new HashSet<>();
                                       temp.add(a);
                                       file_index.put(file.toString(),temp);
                                   }
                             
                        }
                    }

            }
           
            
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("predictions.txt"));
       
        
        for (File file : listOfFiles) 
        {
            double score = 0;
            int pscore = 0;
            int nscore = 0;
            String current_file = file.toString();
            if(!file_index.containsKey(current_file))
            {
              String current_file2 = current_file.replace("C:\\Users\\Κωστής\\Documents\\data\\test\\","");
              current_file2 = current_file2.replace(".txt","");
              bw.write(current_file2+ " " + "unspecified" +"\n");
              bw.newLine();
              continue;
            }
            HashSet<String> phrases = file_index.get(current_file);
            Iterator<String> iterator = phrases.iterator();
            
            while(iterator.hasNext())
            {
                
                String temp = iterator.next();
                HashSet<String> phrase_files = index.get(temp);
                Iterator<String> iterator2 = phrase_files.iterator();
                
                
                pscore = 0;
                nscore = 0;
                while(iterator2.hasNext())
                {
                   
                   String temp2 = iterator2.next();
                   if(pos.contains(temp2))
                   {
                       
                       pscore++;
                   }
                   if(neg.contains(temp2))
                   {
                       
                       nscore++;
                   }
                }
                 if(pscore<4 && nscore <4)
                 {
                      
                 }
                 else
                 {
                 score = score + scoref(pscore,nscore,pos,neg,index,temp);
                 }
                
                
               
            }
            
            if(score <0)
            {
              String current_file2 = current_file.replace("C:\\Users\\Κωστής\\Documents\\data\\test\\","");
              current_file2 = current_file2.replace(".txt","");
              bw.write(current_file2+ " " + 0 +"\n");
              bw.newLine();
            }
            else if(score >0)
            {
              String current_file2 = current_file.replace("C:\\Users\\Κωστής\\Documents\\data\\test\\","");
              current_file2 = current_file2.replace(".txt","");
              bw.write(current_file2+ " " + 1 +"\n");
              bw.newLine();
            }
            else
            {
              String current_file2 = current_file.replace("C:\\Users\\Κωστής\\Documents\\data\\test\\","");
              current_file2 = current_file2.replace(".txt","");
              bw.write(current_file2+ " " + "unspecified" +"\n");
              bw.newLine();  
            }
            
            if(score==(inf *-1) || score==0)
            {
                countio++;
            }
            
        }
        
        bw.close();
        long endTime   = System.currentTimeMillis();
        long totalTimeTest = (endTime - startTime)/1000;
        System.out.println("test time: " + totalTimeTest + " seconds ");
        
    }
    
    public static double scoref(int pscore, int nscore, HashSet<String> pos, HashSet<String> neg, HashMap<String,HashSet<String>> index, String temp2)
    {
        double temp = (pscore * neg.size())/((nscore +0.01) * (pos.size() + 0.01));
        return (Math.log(temp)/Math.log(2));
    }
    
}
