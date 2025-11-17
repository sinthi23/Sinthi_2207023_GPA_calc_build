package com.example.calculator_builder;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalculatorController {

    @FXML private TextField courseNameField;
    @FXML private TextField courseCodeField;
    @FXML private TextField creditField;
    @FXML private TextField teacher1Field;
    @FXML private TextField teacher2Field;
    @FXML private ComboBox<String> gradeComboBox;
    @FXML private Button addCourseButton;
    @FXML private Button calculateButton;



    private ObservableList<Course> courses = FXCollections.observableArrayList();
    private double totalCredits = 0;
    private final double TARGET_CREDITS = 15.0;

    @FXML
    public void initialize() {
        System.out.println("CalculatorController initialized!");


        gradeComboBox.setItems(FXCollections.observableArrayList(
                "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"
        ));




        calculateButton.setDisable(true);
    }

    @FXML
    private void handleAddCourse() {
        try {

            String courseName = courseNameField.getText().trim();
            String courseCode = courseCodeField.getText().trim();
            double credit = Double.parseDouble(creditField.getText().trim());
            String teacher1 = teacher1Field.getText().trim();
            String teacher2 = teacher2Field.getText().trim();
            String grade = gradeComboBox.getValue();


            if (courseName.isEmpty()) {
                showAlert("Error", "Please enter course name");
                return;
            }
            if (grade == null) {
                showAlert("Error", "Please select a grade");
                return;
            }


            Course course = new Course(courseName, courseCode, (int) credit, teacher1, teacher2, grade);
            courses.add(course);

            totalCredits += credit;


            clearFields();


            if (totalCredits >= TARGET_CREDITS) {
                calculateButton.setDisable(false);
                showInfoAlert("Ready!", "You've reached " + TARGET_CREDITS + " credits! You can now calculate your GPA.");
            }

            showInfoAlert("Success", "Course added successfully!\nTotal credits: " + totalCredits + " / " + TARGET_CREDITS);

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for credits");
        } catch (Exception e) {
            showAlert("Error", "Please check your input values: " + e.getMessage());
        }
    }

    @FXML
    private void handleCalculateGPA() {
        try {
            if (courses.isEmpty()) {
                showAlert("Error", "Please add at least one course before calculating GPA");
                return;
            }


            double gpa = calculateRealGPA();


            showGPAResults(gpa);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while calculating GPA");
        }
    }

    private double calculateRealGPA() {
        double totalGradePoints = 0;
        double totalCredits = 0;

        for (Course course : courses) {
            double gradePoints = convertGradeToPoints(course.getGrade());
            double credits = course.getCredit();
            totalGradePoints += gradePoints * credits;
            totalCredits += credits;
        }

        return totalCredits > 0 ? totalGradePoints / totalCredits : 0.0;
    }

    private double convertGradeToPoints(String grade) {
        switch (grade) {
            case "A+": return 4.0;
            case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "D+": return 1.3;
            case "D": return 1.0;
            case "F": return 0.0;
            default: return 0.0;
        }
    }

    private void showGPAResults(double gpa) {
        try {
            // Store data in ResultData
            ResultData.setData(courses, gpa, totalCredits);

            // Navigate to result scene
            Main.showResultScene();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Cannot display results: " + e.getMessage());
        }
    }

    private void clearFields() {
        courseNameField.clear();
        courseCodeField.clear();
        creditField.clear();
        teacher1Field.clear();
        teacher2Field.clear();
        gradeComboBox.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}