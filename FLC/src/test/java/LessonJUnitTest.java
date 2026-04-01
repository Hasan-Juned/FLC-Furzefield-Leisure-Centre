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
public class LessonJUnitTest {
    
    public LessonJUnitTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    
    @Test
    public void testLessonCapacity(){
        Lesson lesson = new Lesson("L1", "Yoga", "Saturday", "Morning", 10);
        
        Member m1 = new Member("M1", "A1");
        Member m2 = new Member("M2", "A2");
        Member m3 = new Member("M3", "A3");
        Member m4 = new Member("M4", "A4");
        
        lesson.addBooking(new Booking("B1", m1, lesson));
        lesson.addBooking(new Booking("B2", m1, lesson));
        lesson.addBooking(new Booking("B3", m1, lesson));
        lesson.addBooking(new Booking("B4", m1, lesson));
        
        assertFalse(lesson.hasSpace()); /// prove max member capacity == 4
        
        
        
    }
}
