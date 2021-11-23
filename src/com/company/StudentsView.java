package com.company;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import javax.swing.*;

public class StudentsView {
    GridPane startview;

    //Buttons
    Button exitButton;
    Button getGradesForStudentsButton;
    Button getAverageGradesForStudentsButton;
    Button getGradesForCoursesButton;
    //Labels
    Label studentIDLabel;
    Label courseLabel;
    //Drop down menus
    ComboBox<Integer> studentIDsComboBox;
    ComboBox<Integer> courseIDsComboBox;
    //Textfields
    TextArea studentTextfield;
    TextArea courseTextfield;



    //Lists
    ObservableList<Integer> studentIDs;
    ObservableList<Integer> courseIDs;



    //constructor
    public StudentsView(){
        startview=new GridPane();
        CreateView();
    }
    private void CreateView(){
        startview.setMinSize(300,200);
        startview.setPadding( new Insets(10,10,10,10));
        startview.setHgap(5);
        startview.setVgap(5);

        // View for student area
        //Top of view
        studentIDLabel = new Label("Student ID: ");
        startview.add(studentIDLabel, 1,1);

        studentIDsComboBox = new ComboBox<>();
        startview.add(studentIDsComboBox,2,1);

        getGradesForStudentsButton = new Button("Get Grades");
        startview.add(getGradesForStudentsButton,3,1);

        getAverageGradesForStudentsButton = new Button("Get Average Grades");
        startview.add(getAverageGradesForStudentsButton,4,1);

        studentTextfield = new TextArea();
        startview.add(studentTextfield,1,2,15,30);

        // VIew for course are
        courseLabel = new Label("Course ID: ");
        startview.add(courseLabel,1,33);

        courseIDsComboBox = new ComboBox<>();
        startview.add(courseIDsComboBox,2,33);

        getGradesForCoursesButton = new Button("Get Average Grades");
        startview.add(getGradesForCoursesButton,3,33);

        courseTextfield = new TextArea();
        startview.add(courseTextfield,1,34,15,30);

        exitButton=new Button("Exit");
        startview.add(exitButton,1,65);
    }

    public void configure(){
        studentIDsComboBox.setItems(studentIDs);
        studentIDsComboBox.getSelectionModel().selectFirst();

        courseIDsComboBox.setItems(courseIDs);
        courseIDsComboBox.getSelectionModel().selectFirst();

    }

    public Parent asParent(){
        return  startview;
    }

}

