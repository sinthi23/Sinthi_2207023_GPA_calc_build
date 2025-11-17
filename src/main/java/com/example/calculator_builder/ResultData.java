package com.example.calculator_builder;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ResultData {
    private static ObservableList<Course> courses = FXCollections.observableArrayList();
    private static double gpa = 0.0;
    private static double totalCredits = 0.0;

    public static void setData(ObservableList<Course> courseList, double calculatedGPA, double credits) {
        courses.setAll(courseList);
        gpa = calculatedGPA;
        totalCredits = credits;

        System.out.println("Data stored - Courses: " + courses.size() + ", GPA: " + gpa);
    }

    public static ObservableList<Course> getCourses() {
        return courses;
    }

    public static double getGpa() {
        return gpa;
    }

    public static double getTotalCredits() {
        return totalCredits;
    }
}