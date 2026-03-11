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
    private List<Booking> bookings; /// booking list as Booking class type
    private List<Integer> ratings; /// will be stored ratings of the leisure features.
    
    /// constructor
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
    
    /// get lessonn id
    public String getLessonId(){
        return lessonId;
    }
    
    /// get exercise type
    public String getExerciseType(){
        return exerciseType;
    }
    
    /// get the day
    public String getDay(){
        return day;
    }
    
    /// get the time slot
    public String getTimeSlot(){
        return timeSlot;
    }
    
    /// get the price
    public double getPrice(){
        return price;
       
    }
    
    /// get the capacity
    public int getCapacity(){
        return capacity;
    }
    
    /// get the bookings list
    public List<Booking> getBookings(){
        return bookings;
    }
    
    /// get the ratings of the features.
    public List<Integer> getRatings(){
        return ratings;
    }
    
    public boolean hasSpace(){
        return bookings.size() < capacity; // enforce the max 4 members
    }
    
    /// user can book lesson by this method
    public void addBooking(Booking booking){
        bookings.add(booking);
    }
    
    /// user can remove lesson by this method
    public void removeBooking(Booking booking){
        bookings.remove(booking);
    }
    
    // by this method user can give ratings for a lesson
    public void addRating(int rating){
        ratings.add(rating);
       
    }
    
    /// this method is for overall or avg rating among the all users'
    
    public double calculateAvgRating(){
        if(ratings.isEmpty()){
            return 0;
        }
        int total = 0;
        
        for(int r: ratings){
            total+=r;
        }
        return (double) total/ratings.size();
    }
    
    @Override
    public String toString(){ /// add this for better code/refactoring
        return lessonId + " | "+
                exerciseType + " | "+
                day + " | "+
                timeSlot + " | GBP " + price;
    }
    
    
}
