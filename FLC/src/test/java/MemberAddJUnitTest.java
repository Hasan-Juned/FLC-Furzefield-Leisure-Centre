/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.flc.Booking;
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
public class MemberAddJUnitTest {
    
    public MemberAddJUnitTest() {
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

    
    @Test
    public void testMemberAddBooking(){
        Lesson lesson = new Lesson("L1", "Yoga", "Saturday", "Morning", 10);
        
        Member m1 = new Member("M1", "Alice");
        
        Booking booking = new Booking ("B1", m1,lesson);
        
        m1.addBooking(booking);
        
        assertEquals(1, m1.getBookings().size());
        
        
        
        
    }
}
