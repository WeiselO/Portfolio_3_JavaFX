package com.company;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    StudentsView view;
    StudentModel model;

    public StudentController(StudentsView inputView, StudentModel inputModel) throws SQLException {
        this.view = inputView;
        this.model = inputModel;
        this.model.connectToStudentData();
        this.model.CreateStatement();
        this.view.studentIDs = getStudentIDs();
        this.view.courseIDs = getCourseIDs();


        this.view.exitButton.setOnAction(e -> {
            Platform.exit();
            try {
                model.closeStudentDataConnection();
                System.out.println("Program closed succesfully!");
            } catch (SQLException f) {
                System.out.println(f.getMessage());
            }
        });
        //Student Grades Buttons
        this.view.getGradesForStudentsButton.setOnAction(e -> {
            PrintStudentGrades(view.studentIDsComboBox.getValue(), view.studentTextfield);
        });
        this.view.getAverageGradesForStudentsButton.setOnAction(e-> PrintStudentAverage(view.studentIDsComboBox.getValue(),view.studentTextfield));

        //Course Grade Buttons
        this.view.getGradesForCoursesButton.setOnAction(e-> PrintCourseAverage(view.courseIDsComboBox.getValue(),view.courseTextfield));

        view.configure();

    }

    public ObservableList<Integer> getStudentIDs() throws SQLException {
        ArrayList<Integer> students = model.SQLQueryStudentIDs();
        ObservableList<Integer> studentIDs = FXCollections.observableArrayList(students);
        return studentIDs;
    }


    public ObservableList<Integer> getCourseIDs() throws SQLException {
        ArrayList<Integer> courses = model.SQLQueryCourseIDs();
        ObservableList<Integer> courseIDs = FXCollections.observableArrayList(courses);
        return courseIDs;
    }

    public void PrintStudentGrades(Integer ID, TextArea StudentTextfield) {
        try {
            ArrayList<StudentInfoPack> StudentInfoPack = model.QueryForStudentGrades(ID);
            StudentTextfield.clear();
            StudentTextfield.appendText("ID:    Course:    Grade \n");
            for (StudentInfoPack Info : StudentInfoPack) {
                StudentTextfield.appendText(Info.ID + ":    " + Info.course + ":    " + Info.grade+"\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void PrintStudentAverage(Integer ID, TextArea StudentTextfield){
        try{
            StudentInfoPack student = model.QueryForStudentAverage(ID);
            StudentTextfield.clear();
            StudentTextfield.appendText("Student Average Grade: \n");
            StudentTextfield.appendText(student.ID +"\n");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void PrintCourseAverage(Integer CourseID, TextArea courseTextfield){
        try{
            CourseInfoPack course = model.QueryForCourseAverage(CourseID);
            courseTextfield.clear();
            courseTextfield.appendText("Average Grade For This Course: \n");
            if(course.average<=1){
                courseTextfield.appendText("This Course isn´t graded yet");
            } else {
                courseTextfield.appendText(course.average +"\n");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
