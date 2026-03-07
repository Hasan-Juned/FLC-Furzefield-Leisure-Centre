package com.mycompany.flc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeisureCentreSystem {
    
    private List<Member> members;
    private List<Booking> bookings;
    private TimeTable timeTable;
    private Scanner input; /// this takes input from user or member
    
    /// Constructor
    public LeisureCentreSystem(){
        members = new ArrayList<>();
        bookings = new ArrayList<>();
        timeTable = new TimeTable();
        input = new Scanner(System.in);
    }
    
    public void startMenu(){
        System.out.println("System Started...");
    }
    
    /// this method responsibility is being created 48 lessons 
    private void createTimeTableData(){
        String[] exerciseTypes = {"Yoga", "Zumba", "Box Fit", "Aquacise"};
        double[] prices ={10.0, 12.0, 15.0, 11.0};
        
        String[] days = {"Saturday", "Sunday"};
        String[] timeSlots = {"Morning", "Afternoon", "Evening"};
        
        int lessonCouter = 1;
        
        for(int i=1; i<=8; i++){
            for(String day: days){
                for (int j = 0; j < timeSlots.length; j++) {
                    
                    String lessonId = "L" + lessonCouter;
                    
                    String exercise = exerciseTypes[(lessonCouter - 1) % exerciseTypes.length];
                    
                    double price = prices[(lessonCouter - 1) % prices.length];
                    
                    Lesson lesson = new Lesson(lessonId, exercise, day +" W"+ i,timeSlots[j],price);
                    
                    timeTable.addLesson(lesson);
                    lessonCouter++;
                }
            }
        }
    }

    /// this is main function which is the starting point of the code entry
    public static void main(String[] args) {
        
        LeisureCentreSystem ob = new LeisureCentreSystem();
        ob.startMenu();
        
        
   
    
    }
}
