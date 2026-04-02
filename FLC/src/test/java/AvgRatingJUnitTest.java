/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.flc.Lesson;
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
public class AvgRatingJUnitTest {
    
    public AvgRatingJUnitTest() {
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
    public void testAvgRating(){
        Lesson lesson = new Lesson("L1", "Yoga", "Saturday", "Morning", 10);
        
        lesson.addRating(5);
        lesson.addRating(3);
        
        double avg = lesson.calculateAvgRating(); /// here fetch final avg rating from lesson class and stored avg and then by follwoing method assertquals will be tested
        assertEquals(4.0, avg);
    }
}
