package com.company;

public class CourseInfoPack {
    Integer courseId = null;
    Integer teacherId= null;
    Float average = null;
    CourseInfoPack(Integer id, Integer teacherId, Float average){
        this.courseId = id;
        this.teacherId = teacherId;
        this.average = average;
    }
}
