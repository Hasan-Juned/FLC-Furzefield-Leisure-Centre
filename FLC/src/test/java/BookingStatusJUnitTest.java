/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.flc.Booking;
import com.mycompany.flc.BookingStatus;
import com.mycompany.flc.Lesson;
import com.mycompany.flc.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Hassan
 */
public class BookingStatusJUnitTest {
    
    public BookingStatusJUnitTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
   
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testBookingStatus(){
        
        Member m1 = new Member("M1", "Alice");
        Lesson lesson = new Lesson("L1", "Yoga", "Saturday", "Morning", 10);
        
        Booking booking = new Booking("B1", m1, lesson); 
        
        booking.setStatus(BookingStatus.ATTENDED); /// refactored from "attended" string to enum ATTENDED to maintain clean code
        
        assertEquals(BookingStatus.ATTENDED, booking.getStatus()); /// here fetch booking status from Booking class and by follwoing method assertquals will be tested
                
        
    }
}
