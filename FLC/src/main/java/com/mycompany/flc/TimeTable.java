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
public class TimeTable {
    
    private List<Lesson> lessons;
    
    public TimeTable(){
        lessons = new ArrayList<>();
    }
    
    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }
    
    public List<Lesson> getLesson(){
        return lessons;
    }
}
