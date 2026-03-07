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
        
        // calling this method to start creating 48 lessons' time table
        createTimeTableData();
        // calling the member creation method
        createMembers();
    }
    
    @SuppressWarnings("empty-statement")
    public void startMenu(){
//        System.out.println("All Saturday Lessons: ");
//        displayLessons(timeTable.searchByDay("Saturday W1"));
//        
//        System.out.println("\nAll Yoga lessons:");
//        displayLessons(timeTable.searchByExercise("Yoga"));
        
        int choice;
        
        do{
            System.out.println("\n*** Furzefield Leisure Centre System ***");
            System.out.println("1. View Lessons");
            System.out.println("2. Book Lesson");
            System.out.println("0. Exit");
            
            choice = input.nextInt();
            input.nextLine();
            
            switch(choice){
                case 1 -> displayLessons(timeTable.getLesson());
                case 2 -> bookLesson();
            }
            
        }while(choice != 0);
        
        }
        
    
    /// this method responsibility is being created 48 lessons timetable info
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
    
    /// by calling this method, user can see display lessons
    private void displayLessons(List<Lesson> lessons){
        for(Lesson lesson: lessons){
            System.out.println(lesson.getLessonId() + " | " + 
                    lesson.getExerciseType() + " | " +
                    lesson.getDay() + " | "+
                    lesson.getTimeSlot() + " | GBP" +
                    lesson.getPrice());
        }
    }

    /// this method will create members
    private void createMembers(){
        members.add(new Member("M1", "Alice"));
        members.add(new Member("M2", "Bob"));
        members.add(new Member("M3", "Cahrlie"));
        members.add(new Member("M4", "Hui"));
    }
    
    private void bookLesson(){
        System.out.println("Enter Member ID: ");
        String memberId = input.nextLine();
        
        Member selectedMember = null;
        
        for(Member m: members){
            if(m.getMemberId().equalsIgnoreCase(memberId)){
                selectedMember = m;
                break;
            }
        }
        if(selectedMember == null){
            System.out.println("Member not found!");
            return;
        }
        System.out.println("Enter Lesson ID: ");
        String lessonId = input.nextLine();
        
        Lesson lesson = timeTable.findLessonById(lessonId);
        
        if(lesson == null){
            System.out.println("Lesson Not Found!");
            return;
        }
        if(!lesson.hasSpace()){
            System.out.println("This lesson is full");
            return;
        }
        String bookingId = "B" + (bookings.size() + 1);
        
        Booking booking = new Booking(bookingId, selectedMember, lesson);
        
        bookings.add(booking);
        selectedMember.addBooking(booking);
        lesson.addBooking(booking);
        
        System.out.println("Your Booking Successful. Booking ID is: " + bookingId);
        
    }
    
    
    /// this is main function which is the starting point of the code entry
    public static void main(String[] args) {
        
        LeisureCentreSystem ob = new LeisureCentreSystem();
        ob.startMenu();
        
        
   
    
    }
}
