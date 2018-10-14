/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author asadz
 */
public class Schedule {

   public static String[][] course;
   public static int facultytotal=7;
   public static String[][] faculty=new String[facultytotal][6];
    public static String[][] classes;
   public static int totalclasses;
    public static int totalcourses;
    public static String[][] SF01= new String[8][6];
    public static String[][] SF02= new String[8][6];
    public static String[][] SF03= new String[8][6];
    public static String[][] SF04= new String[8][6];
    public static String[][] SF05= new String[8][6];
    public static String[][] FF01= new String[8][6];
    public static String[][] FF02= new String[8][6];
    public static String[][] FF03= new String[8][6];
    public static int fitnesssum=0;
    public static int noteachingbefore=0;
    public static int noteachingafter=0;
    
    
  
    public static void main(String[] args) {
        //readCourses
        readCourses();
        
        readFaculty();
        readClasses();
 
       
        System.out.println("Courses: ");
        for(int i=0;i<12;i++){
            
            for(int j=0;j<7;j++){
                System.out.print(" "+course[i][j]+" ");
            }
            System.out.println();
        }


    //now we will have total arrays equals to number of classes
    //for example if we have 8 classes then 8x5 array of each
    //setting time table structure
    generateStructureOfClasses();
  

//initializing schedule randomly
    //1-SF-01 2-SF-02 3-SF-03 4- SF-04 5-SF-05 6-FF-01 7-FF-02 8-FF-03
    //monday [][1] tuesday[][2] wed[][3] thur[][4] fridy[][5]
    //09[1][] 10[2][] 11[3][] 12[4][] 13[5][] 14[6][] 15[7][]
    
    Random rn=new Random();
    
   int num=0;//represent total courses 
   int row=0;//row of course array
   int col=0; //course name at index 0 of course array
   int credithourscol=3;//credit hours col of course array
   
  while(num<totalcourses){
    
       //getting credit hours
           int credithours=Integer.parseInt(course[row][credithourscol]);
         
           int check=0;
           //looping through total credit hours and save classes in time table
           
           while(check<credithours){
              int classrandom=1 + rn.nextInt(4 - 1 + 1);
            //    System.out.println(classrandom);
             int timerandom=1 + rn.nextInt(7 - 1 + 1);
            //    System.out.println(timerandom);
    
              int daysrandom=1 + rn.nextInt(5 - 1 + 1);
            //    System.out.println(daysrandom);
    
    
    
         if(classrandom==1){
            if(SF01[timerandom][daysrandom]==null){
            SF01[timerandom][daysrandom]=course[row][col];
//            System.out.println("Inserted");
            
            check++;
        }
        else{
            
//            System.out.println("Notttttt");
            continue;
            
        
        }
    }
    if(classrandom==2){
        if(SF02[timerandom][daysrandom]==null){
           SF02[timerandom][daysrandom]=course[row][col];
//            System.out.println("Inserted");
            check++; 
        }
        else{
            
//            System.out.println("Notttttt");
            continue;
            
        
        }
    }
    if(classrandom==3){
         if(SF03[timerandom][daysrandom]==null){
           SF03[timerandom][daysrandom]=course[row][col];
//            System.out.println("Inserted");
            check++;
        }
        else{
            
//            System.out.println("Notttttt");
            continue;
            
        
        }
    }
    if(classrandom==4){
         if(SF04[timerandom][daysrandom]==null){
           SF04[timerandom][daysrandom]=" "+course[row][col]+" ";
//            System.out.println("Inserted");
            check++;
        }
        else{
            
//            System.out.println("Notttttt");
            continue;
            
        
        }
    }
               
          
           
    }
    row++;
    num++;

    
    
   }
  print(); 
  //calculating fitness before crossover and mutation
  
  
  //1-classroomsize 2-useClassroomavailabletime 3-facultymemberoneclass 4-teachingparticulartime
  //5-faculty meeting 6-againstspecificclassroom 7-needequipments   
  //fitness total=70 which means 10 of each function
  
   
  
     //fitnesscalculation
     fitnessCalculation();
  System.out.println("Initial Fitness:"+ (fitnesssum/35)+"---------->");
  fitnesssum=0;
  
    int m1=0;
    int m2=0;
    int m3=0;
    int m4=0;
    int average=0;
        //crossover and mutation
       do{
             int method=1 + rn.nextInt(4 - 1 + 1);
             if(method==1){//Row_reselect (Figure 1b) is a function that randomly picks out one classroom, and
//                            changes the schedule for that classroom with a different classroom, 
                      int classroom1=1 + rn.nextInt(4 - 1 + 1);
                      int classroom2=1 + rn.nextInt(4 - 1 + 1);
                      if(classroom1==classroom2){
                      classroom1=1 + rn.nextInt(4 - 1 + 1);
                      classroom2=1 + rn.nextInt(4 - 1 + 1);
                      }
                      
                      System.out.println(classroom1+classroom2);
                  
                      
                        if((classroom1==1 && classroom2==2)||(classroom1==2 && classroom2==1)){ //sf01-sfo2
                      

                            Arrayswap(SF01,SF02);
                           
                        }
                           
                     
                        
                        if((classroom1==1 && classroom2==3)||(classroom1==3 && classroom2==1)){ //sf01-sfo3
                         Arrayswap(SF01,SF03);
                        }
                        if((classroom1==1 && classroom2==4)||(classroom1==4 && classroom2==1)){ //sf01-sfo4
                        Arrayswap(SF01,SF04);
                        }
                        if((classroom1==2 && classroom2==3)||(classroom1==3 && classroom2==2)){ //sf02-sfo3
                        Arrayswap(SF02,SF03);
                       
                        }
                        if((classroom1==2 && classroom2==4)||(classroom1==4 && classroom2==2)){ //sf02-sfo4
                        Arrayswap(SF02,SF04);
                        
                       
                        }
                        if((classroom1==3 && classroom2==4)||(classroom1==4 && classroom2==3)){ //sf03-sfo4
                            Arrayswap(SF03,SF04);
                           


                            }
                        m1++;
                    
                            
}                            

             
             
             if(method==2){//column_reselect
             //Column_reselect (Figure 1a) is a function that randomly selects two scheduled hours of teaching and swaps all the 
            //classes assigned to these two scheduled hours.
             int col1=1 + rn.nextInt(5 - 1 + 1);
             int col2=1 + rn.nextInt(5 - 1 + 1);
        
             if(col1==col2){
                 
              col1=1 + rn.nextInt(5 - 1 + 1);
              col2=1 + rn.nextInt(5 - 1 + 1);
              
             }
            
             
             String[] temp1=new String[7];
             String[] temp2=new String[7];
             
             //for Sf01
             
             
             for(int k=0;k<temp1.length;k++){
                 temp1[k]=SF01[k+1][col1];
             }
             
             for(int k=0;k<temp2.length;k++){
                 temp2[k]=SF01[k+1][col2];
             }
             
             //swapping rows
             for(int k=1;k<8;k++){
             SF01[k][col2]=temp1[k-1];
             }
             //swapping rows
             for(int k=1;k<8;k++){
             SF01[k][col1]=temp2[k-1];
             }
             
             //for sf02
             
              for(int k=0;k<temp1.length;k++){
                 temp1[k]=SF02[k+1][col1];
             }
             
             for(int k=0;k<temp2.length;k++){
                 temp2[k]=SF02[k+1][col2];
             }
             
             //swapping rows
             for(int k=1;k<8;k++){
             SF02[k][col2]=temp1[k-1];
             }
             //swapping rows
             for(int k=1;k<8;k++){
             SF02[k][col1]=temp2[k-1];
             }
             
             
             //for sf03
             
              for(int k=0;k<temp1.length;k++){
                 temp1[k]=SF03[k+1][col1];
             }
             
             for(int k=0;k<temp2.length;k++){
                 temp2[k]=SF03[k+1][col2];
             }
             
             //swapping rows
             for(int k=1;k<8;k++){
             SF03[k][col2]=temp1[k-1];
             }
             //swapping rows
             for(int k=1;k<8;k++){
             SF03[k][col1]=temp2[k-1];
             }
             
             //for sf04
              for(int k=0;k<temp1.length;k++){
                 temp1[k]=SF04[k+1][col1];
             }
             
             for(int k=0;k<temp2.length;k++){
                 temp2[k]=SF04[k+1][col2];
             }
             
             //swapping rows
             for(int k=1;k<8;k++){
             SF04[k][col2]=temp1[k-1];
             }
             //swapping rows
             for(int k=1;k<8;k++){
             SF04[k][col1]=temp2[k-1];
             }
             
             m2++;
         
}//column_reselect
             
            if(method==3){//Classroom_reselect (Figure 1c) is a function that randomly picks out one classroom
                            //and swaps two classes within that classroom.
             
            int classroom=1 + rn.nextInt(4 - 1 + 1);           
             if(classroom==1){//SFO1
                 
                 int row1,row2,col1,col2;
                 String test1,test2;
                 do{
                  row1=1 + rn.nextInt(7 - 1 + 1);
                  col1=1 + rn.nextInt(5 - 1 + 1);
                  
                  
                  test1=SF01[row1][col1];
                 }while(test1==null);
                 
                 
                 do{
                  row2=1 + rn.nextInt(7 - 1 + 1);
                  col2=1 + rn.nextInt(5 - 1 + 1);
                  test2=SF01[row2][col2];
                 }while(test2==null);
                 
                 
                 String temp1=SF01[row1][col1];
                 String temp2=SF01[row2][col2];
                 SF01[row1][col1]=temp2;
                 SF01[row2][col2]=temp1;
                 
             }
             if(classroom==2){//SFO2
             
                 int row1,row2,col1,col2;
                 String test1,test2;
                 do{
                  row1=1 + rn.nextInt(7 - 1 + 1);
                  col1=1 + rn.nextInt(5 - 1 + 1);
                  
                  
                  test1=SF02[row1][col1];
                 }while(test1==null);
                 
                 
                 do{
                  row2=1 + rn.nextInt(7 - 1 + 1);
                  col2=1 + rn.nextInt(5 - 1 + 1);
                  test2=SF02[row2][col2];
                 }while(test2==null);
               
                 
                 String temp1=SF02[row1][col1];
                 String temp2=SF02[row2][col2];
                 SF02[row1][col1]=temp2;
                 SF02[row2][col2]=temp1;
             
             
             
             }
             if(classroom==3){//SFO3
             
                 int row1,row2,col1,col2;
                 String test1,test2;
                 do{
                  row1=1 + rn.nextInt(7 - 1 + 1);
                  col1=1 + rn.nextInt(5 - 1 + 1);
                  
                  
                  test1=SF03[row1][col1];
                 }while(test1==null);
                 
                 
                 do{
                  row2=1 + rn.nextInt(7 - 1 + 1);
                  col2=1 + rn.nextInt(5 - 1 + 1);
                  test2=SF03[row2][col2];
                 }while(test2==null);
             
                 
                 String temp1=SF03[row1][col1];
                 String temp2=SF03[row2][col2];
                 SF03[row1][col1]=temp2;
                 SF03[row2][col2]=temp1;
             
             
             
             }
             if(classroom==4){//SFO4
             
                 int row1,row2,col1,col2;
                 String test1,test2;
                 do{
                  row1=1 + rn.nextInt(7 - 1 + 1);
                  col1=1 + rn.nextInt(5 - 1 + 1);
                  
                  
                  test1=SF04[row1][col1];
                 }while(test1==null);
                 
               
                 
                 
                 do{
                  row2=1 + rn.nextInt(7 - 1 + 1);
                  col2=1 + rn.nextInt(5 - 1 + 1);
                  test2=SF04[row2][col2];
                 }while(test2==null);
                 
                 
                 
                 String temp1=SF04[row1][col1];
                 String temp2=SF04[row2][col2];
                 SF04[row1][col1]=temp2;
                 SF04[row2][col2]=temp1;
                 
             
             
             }
             
           
             m3++;
             }//Classroom_reselect


                if(method==4){// simply called mutation randomly takes one class from one classroom and
                                //swaps it with a class of a different classroom.
                                
                      int classroom1=1 + rn.nextInt(4 - 1 + 1);
                      int classroom2=1 + rn.nextInt(4 - 1 + 1);
                      if(classroom1==classroom2){
                      classroom1=1 + rn.nextInt(4 - 1 + 1);
                      classroom2=1 + rn.nextInt(4 - 1 + 1);
                      }
                  
                      
                        if((classroom1==1 && classroom2==2)||(classroom1==2 && classroom2==1)){ //sf01-sfo2
                        int row1,row2,col1,col2;
                        String test1,test2;
                        do{
                         row1=1 + rn.nextInt(7 - 1 + 1);
                         col1=1 + rn.nextInt(5 - 1 + 1);


                         test1=SF01[row1][col1];
                        }while(test1==null);



                        do{
                         row2=1 + rn.nextInt(7 - 1 + 1);
                         col2=1 + rn.nextInt(5 - 1 + 1);
                         test2=SF02[row2][col2];
                        }while(test2==null);
                       
                       
                        
                        String temp1=SF01[row1][col1];
                        String temp2=SF02[row2][col2];
                        
                        SF01[row1][col1]=temp2;
                        SF02[row2][col2]=temp1;
                        
                       
                        }
                        if((classroom1==1 && classroom2==3)||(classroom1==3 && classroom2==1)){ //sf01-sfo3
                         int row1,row2,col1,col2;
                        String test1,test2;
                        do{
                         row1=1 + rn.nextInt(7 - 1 + 1);
                         col1=1 + rn.nextInt(5 - 1 + 1);


                         test1=SF01[row1][col1];
                        }while(test1==null);



                        do{
                         row2=1 + rn.nextInt(7 - 1 + 1);
                         col2=1 + rn.nextInt(5 - 1 + 1);
                         test2=SF03[row2][col2];
                        }while(test2==null);
                        
                        
                        String temp1=SF01[row1][col1];
                        String temp2=SF03[row2][col2];
                        
                        SF01[row1][col1]=temp2;
                        SF03[row2][col2]=temp1;
                        
                       
                        }
                        if((classroom1==1 && classroom2==4)||(classroom1==4 && classroom2==1)){ //sf01-sfo4
                        int row1,row2,col1,col2;
                        String test1,test2;
                        do{
                         row1=1 + rn.nextInt(7 - 1 + 1);
                         col1=1 + rn.nextInt(5 - 1 + 1);


                         test1=SF01[row1][col1];
                        }while(test1==null);



                        do{
                         row2=1 + rn.nextInt(7 - 1 + 1);
                         col2=1 + rn.nextInt(5 - 1 + 1);
                         test2=SF04[row2][col2];
                        }while(test2==null);
                        
                        
                        String temp1=SF01[row1][col1];
                        String temp2=SF04[row2][col2];
                        
                        SF01[row1][col1]=temp2;
                        SF04[row2][col2]=temp1;
                        }
                        if((classroom1==2 && classroom2==3)||(classroom1==3 && classroom2==2)){ //sf02-sfo3
                         int row1,row2,col1,col2;
                        String test1,test2;
                        do{
                         row1=1 + rn.nextInt(7 - 1 + 1);
                         col1=1 + rn.nextInt(5 - 1 + 1);


                         test1=SF02[row1][col1];
                        }while(test1==null);



                        do{
                         row2=1 + rn.nextInt(7 - 1 + 1);
                         col2=1 + rn.nextInt(5 - 1 + 1);
                         test2=SF03[row2][col2];
                        }while(test2==null);
                        
                        
                        String temp1=SF02[row1][col1];
                        String temp2=SF03[row2][col2];
                        
                        SF02[row1][col1]=temp2;
                        SF03[row2][col2]=temp1;
                        
                       
                        }
                        if((classroom1==2 && classroom2==4)||(classroom1==4 && classroom2==2)){ //sf02-sfo4
                         int row1,row2,col1,col2;
                        String test1,test2;
                        do{
                         row1=1 + rn.nextInt(7 - 1 + 1);
                         col1=1 + rn.nextInt(5 - 1 + 1);


                         test1=SF02[row1][col1];
                        }while(test1==null);



                        do{
                         row2=1 + rn.nextInt(7 - 1 + 1);
                         col2=1 + rn.nextInt(5 - 1 + 1);
                         test2=SF04[row2][col2];
                        }while(test2==null);
                        
                        
                        String temp1=SF02[row1][col1];
                        String temp2=SF04[row2][col2];
                        
                        SF02[row1][col1]=temp2;
                        SF04[row2][col2]=temp1;
                        
                       
                        }
                        if((classroom1==3 && classroom2==4)||(classroom1==4 && classroom2==3)){ //sf03-sfo4
                         int row1,row2,col1,col2;
                        String test1,test2;
                        do{
                         row1=1 + rn.nextInt(7 - 1 + 1);
                         col1=1 + rn.nextInt(5 - 1 + 1);


                         test1=SF03[row1][col1];
                        }while(test1==null);



                        do{
                         row2=1 + rn.nextInt(7 - 1 + 1);
                         col2=1 + rn.nextInt(5 - 1 + 1);
                         test2=SF04[row2][col2];
                        }while(test2==null);
                        
                        
                        String temp1=SF03[row1][col1];
                        String temp2=SF04[row2][col2];
                        
                        SF03[row1][col1]=temp2;
                        SF04[row2][col2]=temp1;
                        
                       
                        }
                        
                        
                       
                        
                
               m4++;
                }//mutation
                
                
             
    
    fitnessCalculation();
    average=fitnesssum/30;
    System.out.println(average);
    fitnesssum=0;
       }while(average<55);
       
       
        System.out.println("Final Fitness----------->"+(average));
        System.out.println("Row_reselect: "+m1+"\n"+"Column Reselect: "+m2+"\n"+"Classroom_reselect: "+m3+"\n"+"Mutaion: "+m4);
        print();
        
    
    }
    
    
    public static void Arrayswap(String arr1[][],String arr2[][]){
                        String temp1[][]=new String[7][6]; 
                           String temp2[][]=new String[7][6];
                           
                           for(int rows=1;rows<8;rows++){
                               for(int cols=1;cols<6;cols++){
                                   temp1[rows-1][cols-1]=arr1[rows][cols];
                               }
                           }
                           for(int rows=1;rows<8;rows++){
                               for(int cols=1;cols<6;cols++){
                                   temp2[rows-1][cols-1]=arr2[rows][cols];
                               }
                           }
                           
                           //swaping schedule
                           
                           for(int rows=1;rows<8;rows++){
                           
                                 for(int cols=1;cols<6;cols++){
                                 arr1[rows][cols]=temp2[rows-1][cols-1];
                                 }
                           }
                           
                           for(int rows=1;rows<8;rows++){
                           
                                 for(int cols=1;cols<6;cols++){
                                 arr2[rows][cols]=temp1[rows-1][cols-1];
                                 }
                           }
    
    
    }
    
    public static void fitnessCalculation(){
    //facultymeeting tuesday 13-14 room SF01
      if(SF01[5][2]==null && SF01[6][2]==null){
                 
       fitnesssum+=10;
      }
                 
  for(int clas=0;clas<4;clas++){
        
        if(clas==0){//extracting fitness from SF01
           
            for(int rows=1;rows<8;rows++){
              for(int cols=1;cols<6;cols++){
              if(SF01[rows][cols]!=null){
                 
                 String course=SF01[rows][cols];//getting course
//                 System.out.println(course);//course
                 int time=Integer.parseInt(SF01[rows][0]); //time of course
                 String day=SF01[0][cols];//day of course
//                 System.out.println("time"+time+"  day:"+day);
                 
                 //its fitness calculation time man!
                 
                 
                 //classroom size
                 int coursecapacity=getCourseCapacity(course);
                 int classcapacity=Integer.parseInt(classes[0][2]);
               
                 if(classcapacity>=coursecapacity){
                 fitnesssum+=10;
//                 System.out.println("added in f1");
                 
                 }else{
//                     System.out.println("not added in f1");
                 }
                 
                 
                 //useClassroomavailabetime
                 
                 int notafter=Integer.parseInt(classes[0][4]);//not after available
                 int notbefore=Integer.parseInt(classes[0][5]);//not before avaialable
                 
                 if(time>=notbefore && time<=notafter){
                     fitnesssum+=10;
//                     System.out.println("added in f2");
                 }
                 else{
//                 System.out.println("not added in f2");
                 }
                 
                 
                 //teachingparticulartime
                 noteachingbeforeafter(course);
                 if(time>=noteachingbefore && time<=noteachingafter){
                 
                     fitnesssum+=10;
//                     System.out.println("added in f3");
                 }
                 else{
//                     System.out.println("not added in f3");
                 }
                 
               
                 //againstspecificclassroom 
                 String room=TeacherAgainstClassRoom(course);
                if(SF01[0][0].trim().equals(room.trim())){
//                    System.out.println("not added in f4");
                }
                else{
                    fitnesssum+=10;
//                System.out.println("added in f4");
                }
                
                
                //needequipments
                String courseEquipment=returnCourseEquipment(course);
                
                String classEquipment=classes[0][3];

                if("W".equals(courseEquipment.trim()) && "W".equals(classEquipment)){
                    fitnesssum+=10;
                    
                }
                else if("WP".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("WPC".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("W".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("WP".equals(courseEquipment.trim()) && "WP".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("W".equals(courseEquipment.trim()) && "WP".equals(classEquipment)){
                    fitnesssum+=10;
                }
                
                 
                
                 
                 
                 
                 
                 
               }
            
                
              }
          }
        
        }
        
        if(clas==1){
         
        for(int rows=1;rows<8;rows++){
              for(int cols=1;cols<6;cols++){
              if(SF02[rows][cols]!=null){
                 
                 String course=SF02[rows][cols];//getting course
//                 System.out.println(course);//course
                 int time=Integer.parseInt(SF02[rows][0]); //time of course
                 String day=SF02[0][cols];//day of course
//                 System.out.println("time"+time+"  day:"+day);
                 
                 //its fitness calculation time man!
                 
                 
                 //classroom size
                 int coursecapacity=getCourseCapacity(course);
                 int classcapacity=Integer.parseInt(classes[1][2]);
               
                 if(classcapacity>=coursecapacity){
                 fitnesssum+=10;
//                 System.out.println("added in f1");
                 
                 }else{
//                     System.out.println("not added in f1");
                 }
                 
                 
                 //useClassroomavailabetime
                 
                 int notafter=Integer.parseInt(classes[1][4]);//not after available
                 int notbefore=Integer.parseInt(classes[1][5]);//not before avaialable
                 
                 if(time>=notbefore && time<=notafter){
                     fitnesssum+=10;
//                     System.out.println("added in f2");
                 }
                 else{
//                 System.out.println("not added in f2");
                 }
                 
                 
                 //teachingparticulartime
                 noteachingbeforeafter(course);
                 if(time>=noteachingbefore && time<=noteachingafter){
                 
                     fitnesssum+=10;
//                     System.out.println("added in f3");
                 }
                 else{
//                     System.out.println("not added in f3");
                 }
                 
               
                 //againstspecificclassroom 
                 String room=TeacherAgainstClassRoom(course);
                if(SF02[0][0].trim().equals(room.trim())){
//                    System.out.println("not added in f4");
                }
                else{
                    fitnesssum+=10;
//                System.out.println("added in f4");
                }
                
                
                //needequipments
                String courseEquipment=returnCourseEquipment(course);
                
                String classEquipment=classes[1][3];

                if("W".equals(courseEquipment.trim()) && "W".equals(classEquipment)){
                    fitnesssum+=10;
                    
                }
                else if("WP".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("WPC".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("W".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("WP".equals(courseEquipment.trim()) && "WP".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("W".equals(courseEquipment.trim()) && "WP".equals(classEquipment)){
                    fitnesssum+=10;
                }
                }
            }
          }
        
        
        
        
        
        
        }
        if(clas==2){
           
        for(int rows=1;rows<8;rows++){
              for(int cols=1;cols<6;cols++){
              if(SF03[rows][cols]!=null){
                 
                 String course=SF03[rows][cols];//getting course
//                 System.out.println(course);//course
                 int time=Integer.parseInt(SF03[rows][0]); //time of course
                 String day=SF03[0][cols];//day of course
//                 System.out.println("time"+time+"  day:"+day);
                 
                 //its fitness calculation time man!
                 
                 
                 //classroom size
                 int coursecapacity=getCourseCapacity(course);
                 int classcapacity=Integer.parseInt(classes[2][2]);
               
                 if(classcapacity>=coursecapacity){
                 fitnesssum+=10;
//                 System.out.println("added in f1");
                 
                 }else{
//                     System.out.println("not added in f1");
                 }
                 
                 
                 //useClassroomavailabetime
                 
                 int notafter=Integer.parseInt(classes[2][4]);//not after available
                 int notbefore=Integer.parseInt(classes[2][5]);//not before avaialable
                 
                 if(time>=notbefore && time<=notafter){
                     fitnesssum+=10;
//                     System.out.println("added in f2");
                 }
                 else{
//                 System.out.println("not added in f2");
                 }
                 
                 
                 //teachingparticulartime
                 noteachingbeforeafter(course);
                 if(time>=noteachingbefore && time<=noteachingafter){
                 
                     fitnesssum+=10;
//                     System.out.println("added in f3");
                 }
                 else{
//                     System.out.println("not added in f3");
                 }
                 
               
                 //againstspecificclassroom 
                 String room=TeacherAgainstClassRoom(course);
                if(SF03[0][0].trim().equals(room.trim())){
//                    System.out.println("not added in f4");
                }
                else{
                    fitnesssum+=10;
//                System.out.println("added in f4");
                }
                
                
                //needequipments
                String courseEquipment=returnCourseEquipment(course);
                
                String classEquipment=classes[2][3];

                if("W".equals(courseEquipment.trim()) && "W".equals(classEquipment)){
                    fitnesssum+=10;
                    
                }
                else if("WP".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("WPC".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("W".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("WP".equals(courseEquipment.trim()) && "WP".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("W".equals(courseEquipment.trim()) && "WP".equals(classEquipment)){
                    fitnesssum+=10;
                }
                
                 
                
                 
                 
                 
                 
                 
               }
            
                
              }
          }
        
        
        
        
        }
        
        
        
      
        if(clas==3){
        
          for(int rows=1;rows<8;rows++){
              for(int cols=1;cols<6;cols++){
              if(SF04[rows][cols]!=null){
                 
                 String course=SF04[rows][cols];//getting course
//                 System.out.println(course);//course
                 int time=Integer.parseInt(SF04[rows][0]); //time of course
                 String day=SF04[0][cols];//day of course
//                 System.out.println("time"+time+"  day:"+day);
                 
                 //its fitness calculation time man!
                 
                 
                 //classroom size
                 int coursecapacity=getCourseCapacity(course);
                 int classcapacity=Integer.parseInt(classes[3][2]);
               
                 if(classcapacity>=coursecapacity){
                 fitnesssum+=10;
//                 System.out.println("added in f1");
                 
                 }else{
//                     System.out.println("not added in f1");
                 }
                 
                 
                 //useClassroomavailabetime
                 
                 int notafter=Integer.parseInt(classes[3][4]);//not after available
                 int notbefore=Integer.parseInt(classes[3][5]);//not before avaialable
                 
                 if(time>=notbefore && time<=notafter){
                     fitnesssum+=10;
//                     System.out.println("added in f2");
                 }
                 else{
//                 System.out.println("not added in f2");
                 }
                 
                 
                 //teachingparticulartime
                 noteachingbeforeafter(course);
                 if(time>=noteachingbefore && time<=noteachingafter){
                 
                     fitnesssum+=10;
//                     System.out.println("added in f3");
                 }
                 else{
//                     System.out.println("not added in f3");
                 }
                 
               
                 //againstspecificclassroom 
                 String room=TeacherAgainstClassRoom(course);
                if(SF04[0][0].trim().equals(room.trim())){
//                    System.out.println("not added in f4");
                }
                else{
                    fitnesssum+=10;
//                System.out.println("added in f4");
                }
                
                
                //needequipments
                String courseEquipment=returnCourseEquipment(course);
                
                String classEquipment=classes[3][3];

                if("W".equals(courseEquipment.trim()) && "W".equals(classEquipment)){
                    fitnesssum+=10;
                    
                }
                else if("WP".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("WPC".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("W".equals(courseEquipment.trim()) && "WPC".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("WP".equals(courseEquipment.trim()) && "WP".equals(classEquipment)){
                    fitnesssum+=10;

                }
                else if("W".equals(courseEquipment.trim()) && "WP".equals(classEquipment)){
                    fitnesssum+=10;
                }
                
              
                
                 
                 
                 
                 
                 
               }
            
                
              }
          }
          
          

      
  }
}
    
    }
    
    public static String returnCourseEquipment(String coursename){
        String courseEquipment=null;
        for(int i=0;i<totalcourses;i++){
            if(course[i][0].equals(coursename.trim())){
                courseEquipment=course[i][5];
            }
        }
       return courseEquipment;
    }
    public static String TeacherAgainstClassRoom(String coursename){
        String room = null;
        for(int i=0;i<totalcourses;i++){
            
            if(course[i][0].equals(coursename.trim())){
                String facultyID=course[i][2];
                for(int j=0;j<facultytotal;j++){
                if(faculty[j][0].equals(facultyID.trim())){
                    room=faculty[j][2];
                    
                }
                }
               
            }
            
        }
       return room; 
    }       
    
    public static void noteachingbeforeafter(String coursename){
  
        for(int i=0;i<totalcourses;i++){
            
            if(course[i][0].equals(coursename.trim())){
                String facultyID=course[i][2];
                for(int j=0;j<facultytotal;j++){
                if(faculty[j][0].equals(facultyID.trim())){
                    noteachingbefore=Integer.parseInt(faculty[j][3]);
                    noteachingafter=Integer.parseInt(faculty[j][4]);
                }
                }
               
            }
            
        }
        
    }
    
    public static int getCourseCapacity(String coursename){
      
        int capacity = 0;
        for(int i=0;i<totalcourses;i++){
            
            if(course[i][0].equals(coursename.trim())){
                capacity=Integer.parseInt(course[i][4]);
               
            }
            
        }
        return capacity;
   
    }
    
    public static void print(){
    
  System.out.println("Class SF01");
    for(int i=0;i<8;i++){
        for(int j=0;j<6;j++){
        System.out.print("  "+SF01[i][j]+"  ");
            
        }
        System.out.println();
    }
    
     System.out.println("\n\n\nClass SF02");
    for(int i=0;i<8;i++){
        for(int j=0;j<6;j++){
        System.out.print("  "+SF02[i][j]+"  ");
            
        }
        System.out.println();
    }
    
    
     System.out.println("\n\n\nClass SF03");
    for(int i=0;i<8;i++){
        for(int j=0;j<6;j++){
        System.out.print("  "+SF03[i][j]+"  ");
            
        }
        System.out.println();
    }
    
     System.out.println("\n\n\nClass SF04");
    for(int i=0;i<8;i++){
        for(int j=0;j<6;j++){
        System.out.print("  "+SF04[i][j]+"  ");
            
        }
        System.out.println();
    }
        
        
    }
    public static void generateStructureOfClasses(){
         for(int i=0;i<8;i++){
        for(int j=0;j<6;j++){
            
            if(j==0){
                //setting class name at 0x0
            SF01[0][0]="SF01";
            SF02[0][0]="SF02";
            SF03[0][0]="SF03";
            SF04[0][0]="SF04";
            SF05[0][0]="SF05";
            FF01[0][0]="FF01";
            FF02[0][0]="FF02";
            FF03[0][0]="FF03";
            }
            
            if(i==1&&j==0){
            SF01[1][0]="9";
            SF02[1][0]="9";
            SF03[1][0]="9";
            SF04[1][0]="9";
            SF05[1][0]="9";
            FF01[1][0]="9";
            FF02[1][0]="9";
            FF03[1][0]="9";
            
            }
            if(i==2&&j==0){
            SF01[2][0]="10";
            SF02[2][0]="10";
            SF03[2][0]="10";
            SF04[2][0]="10";
            SF05[2][0]="10";
            FF01[2][0]="10";
            FF02[2][0]="10";
            FF03[2][0]="10";
            
            }
            if(i==3&&j==0){
            SF01[3][0]="11";
            SF02[3][0]="11";
            SF03[3][0]="11";
            SF04[3][0]="11";
            SF05[3][0]="11";
            FF01[3][0]="11";
            FF02[3][0]="11";
            FF03[3][0]="11";
            }
             if(i==4&&j==0){
            SF01[4][0]="12";
            SF02[4][0]="12";
            SF03[4][0]="12";
            SF04[4][0]="12";
            SF05[4][0]="12";
            FF01[4][0]="12";
            FF02[4][0]="12";
            FF03[4][0]="12";
            }
              if(i==5&&j==0){
            SF01[5][0]="13";
            SF02[5][0]="13";
            SF03[5][0]="13";
            SF04[5][0]="13";
            SF05[5][0]="13";
            FF01[5][0]="13";
            FF02[5][0]="13";
            FF03[5][0]="13";
            }
               if(i==6&&j==0){
            SF01[6][0]="14";
            SF02[6][0]="14";
            SF03[6][0]="14";
            SF04[6][0]="14";
            SF05[6][0]="14";
            FF01[6][0]="14";
            FF02[6][0]="14";
            FF03[6][0]="14";
            }
                if(i==7&&j==0){
            SF01[7][0]="15";
            SF02[7][0]="15";
            SF03[7][0]="15";
            SF04[7][0]="15";
            SF05[7][0]="15";
            FF01[7][0]="15";
            FF02[7][0]="15";
            FF03[7][0]="15";
            }
            
            if(j==1&&i==0){
            SF01[0][1]="MON";
            SF02[0][1]="MON";
            SF03[0][1]="MON";
            SF04[0][1]="MON";
            SF05[0][1]="MON";
            FF01[0][1]="MON";
            FF02[0][1]="MON";
            FF03[0][1]="MON";
            
            }
            if(j==2&&i==0){
            SF01[0][2]="TUE";
            SF02[0][2]="TUE";
            SF03[0][2]="TUE";
            SF04[0][2]="TUE";
            SF05[0][2]="TUE";
            FF01[0][2]="TUE";
            FF02[0][2]="TUE";
            FF03[0][2]="TUE";
            }
            if(j==3&&i==0){
            SF01[0][3]="WED";
            SF02[0][3]="WED";
            SF03[0][3]="WED";
            SF04[0][3]="WED";
            SF05[0][3]="WED";
           
            FF01[0][3]="WED";
            FF02[0][3]="WED";
            FF03[0][3]="WED";
            }
            if(j==4&&i==0){
            SF01[0][4]="THUR";
            SF02[0][4]="THUR";
            SF03[0][4]="THUR";
            SF04[0][4]="THUR";
            SF05[0][4]="THUR";
            FF01[0][4]="THUR";
            FF02[0][4]="THUR";
            FF03[0][4]="THUR";
            }
            if(j==5&&i==0){
            SF01[0][5]="FRI";
            SF02[0][5]="FRI";
            SF03[0][5]="FRI";
            SF04[0][5]="FRI";
            SF05[0][5]="FRI";
            FF01[0][5]="FRI";
            FF02[0][5]="FRI";
            FF03[0][5]="FRI";
            }
           
           
            
            
        }
    }
    }
    public static void readClasses(){

   String inputfile="F:\\Netbean\\TOCI\\Schedule\\class.txt";
      File file=new File(inputfile);
        int row=-1;
        int col=0;
        try{
            
            try (Scanner inputStream = new Scanner(file)) {
               while(inputStream.hasNext()){
                    String data=inputStream.next();
//                    System.out.println(data);
                if(row==-1){
                   totalclasses=Integer.parseInt(data);
                   classes=new String[totalclasses][6];
                   row++;
                   
                   }
                else{
                    
                   if(col>5){
                       row++;
                       col=0;
                   }
                   
                   
                  
                   
                  
                     classes[row][col]=data;
                    col++;
                 
                   }
               }
            }
                    
            
        } catch(FileNotFoundException e){
        
        }
}
    public static void readFaculty(){

   String inputfile="F:\\Netbean\\TOCI\\Schedule\\faculty.txt";
      File file=new File(inputfile);
        int row=0;
        int col=0;
        try{
            
            try (Scanner inputStream = new Scanner(file)) {
               while(inputStream.hasNext()){
                    String data=inputStream.next();
//                    System.out.println(data);
                   if(col>5){
                       row++;
                       col=0;
                   }
                   
                  
                     faculty[row][col]=data;
                    col++;
                 }
                    
            }
        } catch(FileNotFoundException e){
        
        }
}
    public static void readCourses(){

   String inputfile="F:\\Netbean\\TOCI\\Schedule\\course.txt";
      File file=new File(inputfile);
        int row=-1;
        int col=0;
        try{
            
            try (Scanner inputStream = new Scanner(file)) {
               while(inputStream.hasNext()){
                    String data=inputStream.next();
                    
//                    System.out.println(data);

                if(row==-1){
                totalcourses=Integer.parseInt(data);
                
                course=new String[totalcourses][7];
                row++;
                }
                else{
                   if(col>6){
                       row++;
                       col=0;
                   }
                   
                  
                     course[row][col]=data;
                    col++;
                 }
                    
            }
            }
        } catch(FileNotFoundException e){
        
        }
}
      
      
    
}

