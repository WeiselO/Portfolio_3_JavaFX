package com.company;

import java.sql.*;
import java.util.ArrayList;

public class StudentModel {
    Connection connection = null;
    String url = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    StudentModel(String url) {
        this.url = url;
    }

    public void connectToStudentData() throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    public void closeStudentDataConnection() throws SQLException {
        if (connection != null)
            connection.close();
    }

    public void CreateStatement() throws SQLException {
        this.statement = connection.createStatement();
    }

    public ArrayList<Integer> SQLQueryStudentIDs() throws SQLException {
        ArrayList<Integer> StudentIDs = new ArrayList<>();
        String sql = "SELECT StudentID FROM Student;";
        resultSet = statement.executeQuery(sql);
        while (resultSet != null && resultSet.next()) {
            Integer studentID = resultSet.getInt(1);
            StudentIDs.add(studentID);
        }
        return StudentIDs;
    }
    public ArrayList<Integer> SQLQueryCourseIDs() throws SQLException {
        ArrayList<Integer> CourseIDs = new ArrayList<>();
        String sql = "SELECT CourseID FROM Course;";
        resultSet = statement.executeQuery(sql);
        while (resultSet != null && resultSet.next()) {
            Integer courseID = resultSet.getInt(1);
            CourseIDs.add(courseID);
        }
        return CourseIDs;
    }

    public ArrayList<StudentInfoPack>QueryForStudentGrades(Integer ID) throws SQLException{
        ArrayList<StudentInfoPack> StudentInfos = new ArrayList<>();
        String sql ="SELECT Gradenumber,(CourseName ||' '|| CourseYear) AS Course FROM Grade JOIN Course C on C.CourseID = Grade.CourseID WHERE StudentID = ?;";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,ID);
        resultSet=preparedStatement.executeQuery();
        while (resultSet != null && resultSet.next()) {
            String CourseName = resultSet.getString(2);
            Float Grade = resultSet.getFloat(1);
            StudentInfoPack info = new StudentInfoPack(ID, CourseName, Grade);
            StudentInfos.add(info);
        }
        return StudentInfos;
    }
    public StudentInfoPack QueryForStudentAverage(Integer StudentID) throws SQLException{
        String sql =
                "SELECT AVG(Gradenumber) AS Average FROM Grade  WHERE StudentID = ?;";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,StudentID);
        resultSet=preparedStatement.executeQuery();
        Float id= resultSet.getFloat("Average");
        Float grade = null;
        String course = "Grade Average";
        StudentInfoPack student = new StudentInfoPack(StudentID, course, id);
        return student;
    }
    public CourseInfoPack QueryForCourseAverage(Integer courseID) throws SQLException{
        String sql = "SELECT AVG(Gradenumber) AS Average  FROM Grade  WHERE CourseID = ?;";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,courseID);
        resultSet=preparedStatement.executeQuery();
        Float average=resultSet.getFloat("Average");
        Integer teacherId=null;
        CourseInfoPack course = new CourseInfoPack(courseID,teacherId,average);
        return course;
    }


}