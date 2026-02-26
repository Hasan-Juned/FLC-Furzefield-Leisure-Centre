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

    /// this is main function which is the starting point of the code entry
    public static void main(String[] args) {
        
        LeisureCentreSystem ob = new LeisureCentreSystem();
        ob.startMenu();
        
        
   
    
    }
}
