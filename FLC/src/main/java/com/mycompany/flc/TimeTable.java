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
    
    // by this method user can search lesson by day
    public List<Lesson> searchByDay(String day){
        List<Lesson> result = new ArrayList<>();
        for(Lesson lesson: lessons){
            if(lesson.getDay().equalsIgnoreCase(day)){
                result.add(lesson);
            }
        }
        return result;
    }
    
    // by this method user can search lesson by Exercise type
    public List<Lesson> searchByExercise(String exercise){
        List<Lesson> result = new ArrayList<>();
        for(Lesson lesson: lessons){
            if(lesson.getExerciseType().equalsIgnoreCase(exercise)){
                result.add(lesson);
            }
        }
        return result;
    }
    
    // by this method user can search lesson by ID
    public Lesson findLessonById(String lessonId){
        for(Lesson lesson: lessons){
            if(lesson.getLessonId().equalsIgnoreCase(lessonId)){
                return lesson;
            }
        }
        return null;
    }
}
