package com.example.calculator_builder;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    // TableView and columns
    @FXML private TableView<Course> coursesTable;
    @FXML private TableColumn<Course, String> courseNameColumn;
    @FXML private TableColumn<Course, String> codeColumn;
    @FXML private TableColumn<Course, Integer> creditsColumn;
    @FXML private TableColumn<Course, String> gradeColumn;
    @FXML private TableColumn<Course, String> instructorsColumn;

    // Labels
    @FXML private Label gpaLabel;
    @FXML private Label performanceLabel;
    @FXML private Label totalCoursesCountLabel;
    @FXML private Label totalCreditsLabel;

    // Buttons
    @FXML private Button newCalculationButton;
    @FXML private Button backToHomeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        displayData();
    }

    // Set up TableView columns
    private void setupTableColumns() {
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        instructorsColumn.setCellValueFactory(new PropertyValueFactory<>("teachers"));
    }

    // Populate TableView and Labels
    private void displayData() {
        ObservableList<Course> courses = ResultData.getCourses();
        double gpa = ResultData.getGpa();
        double totalCredits = ResultData.getTotalCredits();

        if (courses.isEmpty()) {
            showEmptyState();
            return;
        }

        // Fill the TableView
        coursesTable.setItems(courses);

        // Update labels
        gpaLabel.setText(String.format("%.2f", gpa));
        totalCoursesCountLabel.setText(String.valueOf(courses.size()));
        totalCreditsLabel.setText(String.format("%.1f", totalCredits));
        performanceLabel.setText(getPerformanceComment(gpa));
    }

    // Reset display if no data
    private void showEmptyState() {
        gpaLabel.setText("0.00");
        performanceLabel.setText("No data available");
        totalCoursesCountLabel.setText("0");
        totalCreditsLabel.setText("0.0");
        coursesTable.getItems().clear();
    }

    // Returns performance comment based on GPA
    private String getPerformanceComment(double gpa) {
        if (gpa >= 3.7) return "Excellent! First Class Performance 🏆";
        else if (gpa >= 3.3) return "Very Good! Keep up the good work 👍";
        else if (gpa >= 2.7) return "Good! Solid performance 💪";
        else if (gpa >= 2.0) return "Satisfactory. Room for improvement 📈";
        else return "Needs improvement. Consider academic support 📚";
    }

    // Button Handlers
    @FXML
    private void handleNewCalculation() {
        try {
            Main.showCalculatorScene();  // Go back to calculator scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToHome() {
        try {
            Main.showHomeScene();  // Go back to home scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
