/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hassan
 */
public class Lesson {
    
    private String lessonId;
    private String exerciseType;
    private String day;
    private String timeSlot;
    private double price;
    private int capacity;
    private List<Booking> bookings;
    private List<Integer> ratings;
    
    public Lesson(String lessonId, String exerciseType, String day, String timeSlot, double price){
        
        this.lessonId = lessonId;
        this.exerciseType = exerciseType;
        this.day = day;
        this.timeSlot = timeSlot;
        this.price = price;
        this.capacity = 4;
        this.bookings = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }
    
    public String getLessonId(){
        return lessonId;
    }
    
    public String getExerciseType(){
        return exerciseType;
    }
    public String getDay(){
        return day;
    }
    
    public String getTimeSlot(){
        return timeSlot;
    }
    public double getPrice(){
        return price;
       
    }
    public int getCapacity(){
        return capacity;
    }
    
    public List<Booking> getBookings(){
        return bookings;
    }
    public List<Integer> getRatings(){
        return ratings;
    }
}
