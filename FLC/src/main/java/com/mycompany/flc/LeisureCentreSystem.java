package com.mycompany.flc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class LeisureCentreSystem {
    
    private List<Member> members;
    private List<Booking> bookings;
    private TimeTable timeTable;
    private Scanner input; /// this takes input from user or member
    private int bookingCounter = 1;
    
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
            
            String inputChoice = input.nextLine();
            
            try{
                choice = Integer.parseInt(inputChoice);
            } catch(NumberFormatException e){ /// here used NumberFormatException for safely exiting from program
                System.out.println("Invalid input. Exiting...");
                return;
            }
            
            switch(choice){
                case 1 -> displayLessons(timeTable.getLesson());
                case 2 -> bookLesson();
                case 3 -> changeOrCancelBooking();
                case 4 -> attendLesson();
                case 5 -> generateMonthlyLessonReport();
                case 6 -> generateChampionReport();
                case 0 -> {
                    System.out.println("Exiting..."); 
                    return;
                }
                default -> {
                        System.out.println("Exiting..."); 
                        return;
                }
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
//            System.out.println(lesson.getLessonId() + " | " + 
//                    lesson.getExerciseType() + " | " +
//                    lesson.getDay() + " | "+
//                    lesson.getTimeSlot() + " | GBP" +
//                    lesson.getPrice());

              System.out.println(lesson); /// refactored from above because it's more clean with the help of toString method
        }
    }

    /// this method will create members
    private void createMembers(){
        /// staticly added the membeer ID and name
        members.add(new Member("M1", "Alice"));
        members.add(new Member("M2", "Bob"));
        members.add(new Member("M3", "Cahrlie"));
        members.add(new Member("M4", "Hui"));
    }
    
    private void bookLesson(){
        System.out.println("Enter Member ID: ");
        String memberId = input.nextLine(); /// takes member id (m1/M1)
        
        Member selectedMember = null; /// initially selected member would be null
        
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
        String lessonId = input.nextLine(); /// it takes lesson id, which is basically number of the exercise type. when user clicked the view lesson that time all available lessons will come and left side the first one is basically lesson id (like L1, L2)
        
        Lesson lesson = timeTable.findLessonById(lessonId);
        
        if(lesson == null){
            System.out.println("Lesson Not Found!");
            return;
        }
        if(!lesson.hasSpace()){
            System.out.println("This lesson is full");
            return;
        }
        
        //String bookingId = "B" + (bookings.size() + 1); /// it is refactored in the following line
       
        String bookingId = "B" + bookingCounter; /// refactored for more looking clean compared to above line. which stores booking ID when booking succeed
        bookingCounter++; /// booking IDs will be updated
        
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
            if(booking.getLesson().getLessonId().equals(lesson.getLessonId()) && booking.getStatus() != BookingStatus.CANCELLED){
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
        
        if(choice == 1){ /// this block is for changing lesson 
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
            selectedBooking.setStatus(BookingStatus.CHANGED); /// refactored here: from "Changed" to using enum
            System.out.println("Booking changed Successfully");
            
        } else if(choice == 2){ /// this lesson is for cancelling booking
            Lesson lesson = selectedBooking.getLesson();
            
            /// following lines will be cancelled booking
            lesson.removeBooking(selectedBooking); 
            selectedBooking.setStatus(BookingStatus.CANCELLED);/// refactored here: from "Cancelled" to using enum
            
            System.out.println("Booking cancelled");
        }
    }
    
    private void attendLesson(){ /// --> a exercise type attendence will be counted and updated when members click on the attendance functionality. And this proccess will be done by this method

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
        
        selectedBooking.setReview(review); /// this method called for setting review - it's not a core part
        selectedBooking.setRating(rating); /// this method called for seeting rating - it's core part of this project
        selectedBooking.setStatus(BookingStatus.ATTENDED); /// refactored here: from "attended" to using enum
        
        Lesson lesson = selectedBooking.getLesson();
        lesson.addRating(rating);
        
        System.out.println("Lesson attended");
    }
    
    // this method will be genearated for the monthly reports
    private void generateMonthlyLessonReport(){
        System.out.println("Monthly Lesson Report\n");
        
        for(Lesson lesson: timeTable.getLesson()){
            
            int attendanceCount = 0;
            
            for(Booking booking: lesson.getBookings()){ /// when differnt booked members attend to do exrcise then this loop will be worked and total attendance will be updated for each exercise type
                if(booking.getStatus() == BookingStatus.ATTENDED){
                    attendanceCount++;
                }
            }
            double avgRating = lesson.calculateAvgRating(); /// by this method avg rating will be stored. For example one member give 5 rating and other give 2. 5+2 = 7 and 7/2 == 3.5 avg rating.
            
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
                if(booking.getStatus() == BookingStatus.ATTENDED){
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
// coding part done