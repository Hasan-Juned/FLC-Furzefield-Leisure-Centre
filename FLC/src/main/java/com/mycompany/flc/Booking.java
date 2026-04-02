/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flc;

/**
 *
 * @author Hassan
 */
public class Booking {
    
    private String bookingId;
    private Member member;
    private Lesson lesson;
    private BookingStatus status; /// enum bookingstatus used here
    private String review;
    private int rating;
    
    public Booking(String bookingId, Member member, Lesson lesson){
        this.bookingId = bookingId;
        this.member = member;
        this.lesson = lesson;
        this.status = BookingStatus.BOOKED; /// refactored here
    }
    
    /// below these getter methods are fetching the lesson, status, review and rating of the leisure feature type

    public String getBookingId(){
        return bookingId;
    }
    
    public Member getMember(){
        return member;
    }
    
    public Lesson getLesson(){
        return lesson;
    }
    
    public BookingStatus getStatus(){ // refactor here
        return status;
    }
    
    public String getReview(){
        return review;
    }
    
    public int getRating(){
        return rating;
    }
    
    /// below these setter methods set the lesson, status, review and rating of the leisure feature type
    public void setLesson(Lesson lesson){
        this.lesson = lesson;
    }
    
    public void setStatus(BookingStatus status){ /// refactored here
        this.status = status;
    }
    
    /// setter method for setting review
    public void setReview(String review){
        this.review =review;
    }
    
    /// setter method for setting rating 1-5
    public void setRating(int rating){
        this.rating = rating;
    }
}
