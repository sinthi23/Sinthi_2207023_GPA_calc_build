package com.example.calculator_builder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResultData {

    // Observable list to store courses
    private static final ObservableList<Course> courses = FXCollections.observableArrayList();
    private static double gpa = 0.0;
    private static double totalCredits = 0.0;

    // Method to set data (call this before opening Result scene)
    public static void setData(ObservableList<Course> courseList, double calculatedGPA, double credits) {
        courses.setAll(courseList);  // replace old data with new
        gpa = calculatedGPA;
        totalCredits = credits;

        System.out.println("📌 ResultData updated: " + courses.size() + " courses, GPA: " + gpa);
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
