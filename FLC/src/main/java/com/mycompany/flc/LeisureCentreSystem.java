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
            System.out.println("3. Change/Cancel Booking");
            System.out.println("4. Attend Lesson");
            System.out.println("5. Monthly Lesson Report");
            System.out.println("6. Champion Exercise Report");
            System.out.println("0. Exit");
            
            choice = input.nextInt();
            input.nextLine();
            
            switch(choice){
                case 1 -> displayLessons(timeTable.getLesson());
                case 2 -> bookLesson();
                case 3 -> changeOrCancelBooking();
                case 4 -> attendLesson();
                case 5 -> generateMonthlyLessonReport();
                case 6 -> generateChampionReport();
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
        
        // here checking first, if there has any duplicate value or not. if has then same id membe won't create new booking
        if(hasDuplicateBooking(selectedMember, lesson)){
            System.out.println("You have already booked this lesson");
            return;
        }
        
        
        bookings.add(booking);
        selectedMember.addBooking(booking);
        lesson.addBooking(booking);
        
        System.out.println("Your Booking Successful. Booking ID is: " + bookingId);
        
    }
    
    // this method checks isn't there has any duplicate booking or not, depending on the true/false
    private boolean hasDuplicateBooking(Member member, Lesson lesson){
        for(Booking booking: member.getBookings()){
            if(booking.getLesson().getLessonId().equals(lesson.getLessonId()) && !booking.getStatus().equals("cancelled")){
                return true;
            }
        }
        return false;
    }
    
    /// this method's responsibility is to change or cancel booking
    private void changeOrCancelBooking(){
        System.out.println("Enter Booking ID: ");
        String bookingId = input.nextLine();
        
        Booking selectedBooking = null;
        
        for(Booking b: bookings){
            
            if(b.getBookingId().equalsIgnoreCase(bookingId)){
                selectedBooking = b;
                break;
            }
        }
        if(selectedBooking == null){
            System.out.println("Booking not found");
            return;
        }
        
        System.out.println("1. Change Lesson");
        System.out.println("2. Cancel Booking");
        
        int choice = input.nextInt();
        input.nextLine();
        
        if(choice == 1){
            System.out.println("Enter new Lesson ID: ");
            String newLessonId = input.nextLine();
            
            Lesson newLesson =  timeTable.findLessonById(newLessonId);
            
            if(newLesson == null){
                System.out.println("Lesson not found");
                return;
            }
            
            if(!newLesson.hasSpace()){
                System.out.println("Lesson is full");
                return;
            }
            
            Lesson oldLesson = selectedBooking.getLesson();
            
            oldLesson.removeBooking(selectedBooking);
            newLesson.addBooking(selectedBooking);
            
            selectedBooking.setLesson(newLesson);
            selectedBooking.setStatus("Changed");
            System.out.println("Booking changed Successfully");
            
        } else if(choice == 2){
            Lesson lesson = selectedBooking.getLesson();
            
            lesson.removeBooking(selectedBooking);
            selectedBooking.setStatus("Cancelled");
            
            System.out.println("Booking cancelled");
        }
    }
    
    private void attendLesson(){
        System.out.println("Enter Booking ID: ");
        String bookingId = input.nextLine();
        
        Booking selectedBooking = null;
        
        for(Booking b: bookings){
            if(b.getBookingId().equalsIgnoreCase(bookingId)){
                selectedBooking = b;
                break;
            }
        }
        if(selectedBooking == null){
            System.out.println("Booking not found");
            return;
        }
        System.out.println("Write your review");
        String review = input.nextLine();
        
        System.out.println("Enter rating (1 to 5): ");
        int rating = input.nextInt();
        input.nextLine();
        
        if(rating < 1 || rating > 5){
            System.out.println("Please rate us between 1 - 5");
            return;
        }
        
        selectedBooking.setReview(review);
        selectedBooking.setRating(rating);
        selectedBooking.setStatus("attended");
        
        Lesson lesson = selectedBooking.getLesson();
        lesson.addRating(rating);
        
        System.out.println("Lesson attended");
    }
    
    // this method will be genearated for the monthly reports
    private void generateMonthlyLessonReport(){
        System.out.println("Monthly Lesson Report\n");
        
        for(Lesson lesson: timeTable.getLesson()){
            
            int attendanceCount = 0;
            
            for(Booking booking: lesson.getBookings()){
                if(booking.getStatus().equals("attended")){
                    attendanceCount++;
                }
            }
            double avgRating = lesson.calculateAvgRating();
            
            System.out.println(lesson.getLessonId() + " | " +
                    lesson.getExerciseType() + " | "+
                    lesson.getDay() + " | " +
                    lesson.getTimeSlot() + " | Attendees: "+
                    attendanceCount + " | Avg Rating: "+
                    avgRating);
        }
    }
    
    private void generateChampionReport(){
        double yogaIncome = 0;
        double zumbaIncome = 0;
        double boxfitIncome = 0;
        double aquaIncome = 0;
        
        for(Lesson lesson: timeTable.getLesson()){
            int attendees = 0;
            
            for(Booking booking: lesson.getBookings()){
                if(booking.getStatus().equals("attended")){
                    attendees++;
                }
            }
            double income = attendees * lesson.getPrice();
            
            switch(lesson.getExerciseType()){
                case "Yoga":
                    yogaIncome += income;
                    break;
                case "Zumba":
                    zumbaIncome += income;
                    break;
                case "Box Fit":
                    boxfitIncome += income;
                    break;
                case "Aquacise":
                    aquaIncome += income;
                    break;
            }
        }
        System.out.println("\n\nIncome Report");
        
        System.out.println("Yoga Income: GBP "+yogaIncome);
        System.out.println("Zumba Income: GBP "+zumbaIncome);
        System.out.println("Box Fit Income: GBP "+boxfitIncome);
        System.out.println("Aquacise Income: GBP "+aquaIncome);
        
        double maxIncome = Math.max(Math.max(yogaIncome, zumbaIncome), Math.max(boxfitIncome, aquaIncome));
        
        if(maxIncome == yogaIncome){
            System.out.println("Champion Exercise: Yoga");
        }else if(maxIncome == zumbaIncome){
            System.out.println("Champion Exercise: Zumba");
        }else if(maxIncome == boxfitIncome){
            System.out.println("Champion Exercise: Box Fit");
        }else{
            System.out.println("Champion Exercise: Aquacise");
        }
        
    }
    
    /// this is main function which is the starting point of the code entry
    public static void main(String[] args) {
        
        LeisureCentreSystem ob = new LeisureCentreSystem();
        ob.startMenu();
        
        
   
    
    }
}


/// gpt er last er 2 part coding kore project ses