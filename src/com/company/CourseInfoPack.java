package com.company;

public class CourseInfoPack {
    Integer courseId = null;
    Integer teacherId= null;
    Integer average = null;
    CourseInfoPack(Integer id, Integer teacherId, Integer average){
        this.courseId = id;
        this.teacherId = teacherId;
        this.average = average;
    }
}
