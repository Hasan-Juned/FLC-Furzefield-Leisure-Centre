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
public class Member {
    
    private String memberId;
    private String name;
    private List<Booking> bookings; // this list will be stored the booking details of members.
    
    // constructor which recieves memberId and name
    
    public Member(String memberId, String name){
        this.memberId = memberId;
        this.name = name;
        this.bookings = new ArrayList<>();
        
    }
    
    /// it is returning the member's name
    public String getName(){
        return name;
    }
    
    /// it is returning the member's id
    public String getMemberId(){
        return memberId;
    }
    
    /// it is returning booking list which are stored / booked by members or users
    public List<Booking> getBookings(){
        return bookings;
    }
    
    /// member can add book the leisure feature via this metthod
    public void addBooking (Booking booking){
        bookings.add(booking);
    }
    
    /// this method's responsibility is to remove booking details
    public void removeBooking(Booking booking){
        bookings.remove(booking);
    }
    
    
    
}
