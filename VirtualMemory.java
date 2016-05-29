/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.project.pkg2.virtual.memory;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author memphise
 */
public class VirtualMemory extends Processes  {
    
         static int[][] physicalMemory;
         static int ProcessNo;
         static int [][] disk;
        static ArrayList<Processes> list = new ArrayList<Processes>();
        static Processes pr = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        System.out.println("enter the memory size in pages");
        
        Scanner scannerChoice = new Scanner(System. in); 
        int choice = scannerChoice.nextInt();
        

        //int[] memory = new int[choice];
        physicalMemory = new int[choice][4];
        disk = new int [choice][2];
        //Create an array list
        //Create an array list

        //Create a file
        File file = new File("schedule.txt");
        try {
            //Create a scanner for the file
            Scanner reader = new Scanner(file);

            //read data from the file 
            while (reader.hasNextLine()) {
                ProcessNo++;
                String str = null;
                str = reader.nextLine();
                //Splitting the data and saving in a string array
                String[] tokenz = str.split(",");
                int pId = Integer.parseInt(tokenz[0]);
                int startTime = Integer.parseInt(tokenz[1]);
                int duration = Integer.parseInt(tokenz[2]);
                int size = Integer.parseInt(tokenz[3]);
                
                
                
                File file2;
                int [] page = new int[20];
                file2 = new File("memoryTrace"+pId+".txt");
               
                                Scanner reader2=null;

                try{
                reader2 = new Scanner(file2);
                }
                   catch (FileNotFoundException e) {
            System.out.println("cannot find the file");
                 }
                
                int i = 0;
                int NoPages=0;
                
                
                
                while (reader2.hasNextLine()) {
                    NoPages++;
                   
                    String strx;
                     int str2 ;
                     
                     strx = reader2.nextLine();
                     System.out.println(strx);
                     str2 = Integer.parseInt(strx,16);
                     page[i]= str2/(16*16*16);
                     System.out.println(page[i]);

                     i++; 
                    }
                System.out.println("----------------");
                      
                    
                    pr = new Processes(pId, startTime, duration, size, page,NoPages);
                    list.add(pr);
                    
                    
                    //System.out.println(pId + "xxxx" + startTime + "xxxx" + duration + "xxxx" + size);
                    /*int x=0;
                    while(x < i){
                        System.out.println(list.get(0).getPage()[x]);
                    x++;
                    }
                     System.out.println("----------");*/

                }
                       
            
            
            reader.close();

        }//checking the existence of the file//checking the existence of the file
        catch (FileNotFoundException e) {
            System.out.println("cannot find the file");
        }
       
       
  
                
  
        System.out.print("do you want to simulate sir? ");
        
        Scanner scanner = new Scanner(System. in); 
        String in = scanner.nextLine();
        
        if("yes".equals(in))
            simulator(list);
        else if("no".equals(in))
               System.out.println("ok");
        else
               System.out.println("ha?");

            
          // TODO code application logic here
    }

    public VirtualMemory(int pId, int startTime, int size, int duration,int [] page,int NoPages) {
        super(pId, startTime, size, duration, page,NoPages);
    }
    
   public static void simulator(ArrayList list){
        
       
       ArrayList<Processes> list2 = list;
        
        Collections.sort(list2, new Comparator<Processes>() {
            @Override
            public int compare(Processes p2, Processes p1) {

                return p2.getStartTime() - p1.getStartTime();
            }
        });
        
               System.out.println(list2.get(1).getPageNo());


        int sumTurnaroundTime = 0;
        int sumWaitingTime = 0;
        int count2 = 0;
        double tQuante = 2;
        int i = 0;
        double timer = 0;
        int counter=0;
        
        
        
        
        while (true) {
            
            System.out.println("duration: " + list2.get(i).getDuration());
             if(list2.get(i).getDuration()<=0)
                 break;
              
            int z = 0;
            int cnt = 0;
            for (int c = 0; c <= list2.size() - 1; c++) {
                if (list2.get(c).getDuration() == 0) {
                    cnt++;
                }

            }
            
             if(counter==physicalMemory.length-1){
                counter=0;
            }
            
            if (cnt == list2.size()) {
                break;

            }
            if (i > list2.size() - 1) {
                i = 0;
            }
             int cycle = 0;
             
            
             
              

            if (list2.get(i).getStartTime() <= timer) {//if it's arrived
                if (list2.get(i).getDuration() != 0) {//if it's not done
                    if (list2.get(i).getDuration() > tQuante) {//to subtract;
                        
                       
                        while (tQuante != 0) {
                                                  
                            if(list2.get(i).getPageCounter()==list2.get(i).getPageNo())
                                break;
                            
                            int x = list2.get(i).page[list2.get(i).getPageCounter()];

                            int available = 0;

                            // searching
                            for (int j = 0; j < physicalMemory.length; j++) {
                                if (physicalMemory[j][1] == x && physicalMemory[j][0] == list2.get(i).pId) {
                                    physicalMemory[j][2] = 1;//memory ref.
                                    physicalMemory[j][3] = 1;
                                    list2.get(i).pageCounter++;
                                    System.out.println("av: "+ x);
                                    available = 1;
                                    cycle++;
                                    tQuante--;
                                    break;
                                }
                              
                            }

                            if (available == 0 /*&& /*notfull(physicalMemory[][])*/) {

                               if(isfull(physicalMemory)){
                                    while (physicalMemory[counter][2] == 1) {
                                        physicalMemory[counter][2] = 0;
                                        counter++;
                                        if(counter==physicalMemory.length-1)
                                            counter=0;
                                    }
                                    
                                    //System.out.println(counter);
                                    disk [counter][0] = physicalMemory[counter][0];
                                    disk[counter][1] = physicalMemory[counter][1];
                                    physicalMemory[counter][0] = list2.get(i).pId;
                                    physicalMemory[counter][1] = x;
                                    physicalMemory[counter][2] = 0;
                                    physicalMemory[counter][3] = 1;
                                    list2.get(i).pageCounter++;
                                    tQuante--;
                                    cycle = cycle + 300;
                                    }


                                else{
                                    System.out.println("new: "+x);
                                    physicalMemory[counter][0] = list2.get(i).pId;
                                    physicalMemory[counter][1] = x;
                                    physicalMemory[counter][2] = 0;
                                    physicalMemory[counter][3] = 1;
                                    counter++;
                                    list2.get(i).pageCounter++;
                                    tQuante--;
                                    cycle = cycle + 300;
                               }
                            }
                        }
                }
            }
        }
            if (list2.get(i).getDuration() >= tQuante){
             System.out.println("cycles: "+cycle); 
                        tQuante = 2;
                        double cycleTime=cycle*0.001;
                        double spentTime = tQuante - cycleTime;
                        list2.get(i).setDuration(list2.get(i).getDuration() - spentTime);
                        System.out.println("Spent time: "+spentTime); 
                        timer += tQuante;
                        
                          
            }
             
                        
                         if (list2.get(i).getDuration() < tQuante) {//it's remaining time is less than quantum
                        System.out.println("cycles: "+cycle); 
                        tQuante = 2;
                        double cycleTime=cycle*0.001;
                        double spentTime = tQuante - cycleTime;
                        //list2.get(i).setDuration(list2.get(i).getDuration() - spentTime);
                             
                        double y = (double) list2.get(i).getDuration();
                        timer += y;
                        System.out.println("Spent time: "+y); 
                        list2.get(i).setDuration(0);

                        //list2.get(i).setfT(timer);
                       // list2.get(i).setTurnaroundTime(timer - list2.get(i).getStartTime());
                       // list2.get(i).setWaitTime((int) (list2.get(i).getTurnaroundTime() - list2.get(i).getDuration()));
                        i=0;

                    }
                        
             if (list2.get(i).getDuration() == 0) {
                           // list2.get(i).setfT(timer);
                            //list2.get(i).setTurnaroundTime(timer - list2.get(i).getStartTime());
                           // list2.get(i).setWaitTime((int) (list2.get(i).getTurnaroundTime() - list2.get(i).getDuration()));

                        }
                        i=0;           
        }
}

   
    /*public static void simulator(ArrayList list){
        
        ArrayList<Processes> list2 = list;
        
        Collections.sort(list2, new Comparator<Processes>() {
            @Override
            public int compare(Processes p2, Processes p1) {

                return p2.getStartTime() - p1.getStartTime();
            }
        });
        int sumTurnaroundTime = 0;
        int sumWaitingTime = 0;
        int count2 = 0;
        int tQuante = 2;
        int i = 0;
        int timer = 0;
        int counter=0;
        
        
        while (true) {

            int z = 0;
            int cnt = 0;
            for (int c = 0; c <= list2.size() - 1; c++) {
                if (list2.get(c).getDuration() == 0) {
                    cnt++;
                }

            }
            if (cnt == list2.size()) {
                break;

            }
            if (i > list2.size() - 1) {
                i = 0;
            }
             int cycle = 0;

            if (list2.get(i).getStartTime() <= timer) {//if it's arrived
                if (list2.get(i).getDuration() != 0) {//if it's not done
                    if (list2.get(i).getDuration() > tQuante) {//to subtract;
                        
                       
                        while (tQuante != 0) {
                            int x = list2.get(i).page[list2.get(i).getPageCounter()];

                            int available = 0;

                            // searching
                            for (int j = 0; j < physicalMemory.length; j++) {
                                if (physicalMemory[j][1] == x && physicalMemory[j][0] == list2.get(i).pId) {
                                    physicalMemory[j][2] = 1;//memory ref.
                                    physicalMemory[j][3] = 1;

                                    available = 1;
                                    cycle++;
                                    tQuante--;
                                    break;
                                }
                            }

                            if (available == 0 ) {
                                
/*
                                if(isfull(physicalMemory)){
                                    
                                    while (physicalMemory[counter][2] == 1) {
                                        physicalMemory[counter][2] = 0;
                                        counter++;
                                    }
                                    
                                    disk [counter][0] = physicalMemory[counter][0];
                                    disk[counter][1] = physicalMemory[counter][1];
                                    physicalMemory[counter][0] = list2.get(i).pId;
                                    physicalMemory[counter][1] = x;
                                    physicalMemory[counter][2] = 0;
                                    physicalMemory[counter][3] = 1;
                                    list2.get(i).pageCounter++;
                                    tQuante--;
                                    cycle = cycle + 300;
                                    }


                                else{
                                    
                                    int count=0;
                                    
                                    while (physicalMemory[counter][3] == 1)
                                        count++;
                                    
                                    physicalMemory[count][0] = list2.get(i).pId;
                                    physicalMemory[count][1] = x;
                                    physicalMemory[count][2] = 0;
                                    physicalMemory[count][3] = 1;

                                    list2.get(i).pageCounter++;
                                    tQuante--;
                                    cycle = cycle + 300;
                                }
                            }

                        }

                    }
                    double spentTime=cycle/1000;
                  
                        tQuante = 2;
                        list2.get(i).setDuration(list2.get(i).getDuration() - spentTime);
                        timer += tQuante;
                         
                        
                        
                        
                        
                        if (list2.get(i).getDuration() == 0) {
                            list2.get(i).setfT(timer);
                            list2.get(i).setTurnaroundTime(timer - list2.get(i).getStartTime());
                            list2.get(i).setWaitTime((int) (list2.get(i).getTurnaroundTime() - list2.get(i).getDuration()));

                        }
                        i++;
                    } else {//it's remaining time is less than quantum
                        int x = (int) list2.get(i).getDuration();
                        timer += x;
                        list2.get(i).setDuration(0);

                        list2.get(i).setfT(timer);
                        list2.get(i).setTurnaroundTime(timer - list2.get(i).getStartTime());
                        list2.get(i).setWaitTime((int) (list2.get(i).getTurnaroundTime() - list2.get(i).getDuration()));
                        i++;

                    }
                } else {//the process is done
                    i++;

                }
            /* else {//the process's not arrived
                i = 0;
            }

        }

        int count = 0;
        while (count < list2.size()) {
            sumTurnaroundTime = sumTurnaroundTime + list2.get(count).getTurnaroundTime();
            sumWaitingTime = sumWaitingTime + list2.get(count).getWaitTime();
            count++;
        }

        double avg_waitingTime = (double) sumWaitingTime / list2.size();
        double avg_TurnaroudTime = (double) sumTurnaroundTime / list2.size();



    
        
                
        
       
    }
}*/

    public static boolean isfull(int [][] memory){
        int count =0;
        while(count < memory.length)
                if(memory[count][3] == 1)
                    count++;
                else 
                    return false;
        
        return true;
    }

}