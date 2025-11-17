package com.example.calculator_builder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ResultController implements Initializable {

    public Button newCalculationButton;
    @FXML private TableView<Course> coursesTable;
    @FXML private TableColumn<Course, String> courseNameColumn;
    @FXML private TableColumn<Course, String> codeColumn;
    @FXML private TableColumn<Course, Integer> creditsColumn;
    @FXML private TableColumn<Course, String> gradeColumn;
    @FXML private TableColumn<Course, String> instructorsColumn;

    @FXML private Label gpaLabel;
    @FXML private Label performanceLabel;
    @FXML private Label totalCoursesCountLabel;
    @FXML private Label totalCreditsLabel;

//    @FXML private Button newCalculationButton;
//    @FXML private Button backToHomeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("=== RESULT CONTROLLER INITIALIZED ===");


        debugFXMLInjection();

        setupTableView();
        debugTableState();
        loadData();
    }

    private void debugFXMLInjection() {
        System.out.println("=== FXML INJECTION DEBUG ===");
        System.out.println("coursesTable: " + (coursesTable != null ? "INJECTED" : "NULL"));
        System.out.println("courseNameColumn: " + (courseNameColumn != null ? "INJECTED" : "NULL"));
        System.out.println("codeColumn: " + (codeColumn != null ? "INJECTED" : "NULL"));
        System.out.println("creditsColumn: " + (creditsColumn != null ? "INJECTED" : "NULL"));
        System.out.println("gradeColumn: " + (gradeColumn != null ? "INJECTED" : "NULL"));
        System.out.println("instructorsColumn: " + (instructorsColumn != null ? "INJECTED" : "NULL"));

        if (coursesTable != null) {
            System.out.println("TableView has " + coursesTable.getColumns().size() + " columns");
            for (int i = 0; i < coursesTable.getColumns().size(); i++) {
                TableColumn<?, ?> col = coursesTable.getColumns().get(i);
                System.out.println("Column " + i + ": " + col.getText() + " (fx:id: " + (col.getId() != null ? col.getId() : "no fx:id") + ")");
            }
        }
    }

    private void setupTableView() {
        System.out.println("=== SETTING UP TABLE VIEW ===");

        try {

            if (courseNameColumn != null) {
                System.out.println("Using FXML injected columns with LAMBDA");
                courseNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCourseName()));
                codeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCourseCode()));
                creditsColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCredit()).asObject());
                gradeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGrade()));
                instructorsColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTeachers()));
                System.out.println("FXML columns setup completed with LAMBDA");
            }
            else if (coursesTable != null && coursesTable.getColumns().size() >= 5) {
                System.out.println("Setting up columns from TableView directly with LAMBDA");

                TableColumn<Course, String> col1 = (TableColumn<Course, String>) coursesTable.getColumns().get(0);
                TableColumn<Course, String> col2 = (TableColumn<Course, String>) coursesTable.getColumns().get(1);
                TableColumn<Course, Integer> col3 = (TableColumn<Course, Integer>) coursesTable.getColumns().get(2);
                TableColumn<Course, String> col4 = (TableColumn<Course, String>) coursesTable.getColumns().get(3);
                TableColumn<Course, String> col5 = (TableColumn<Course, String>) coursesTable.getColumns().get(4);


                col1.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCourseName()));
                col2.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCourseCode()));
                col3.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCredit()).asObject());
                col4.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGrade()));
                col5.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTeachers()));

                System.out.println("TableView columns setup completed with LAMBDA expressions");
            }

            else if (coursesTable != null) {
                System.out.println("Creating new columns programmatically with LAMBDA");
                coursesTable.getColumns().clear();

                TableColumn<Course, String> courseCol = new TableColumn<>("Course Name");
                courseCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCourseName()));
                courseCol.setPrefWidth(200);

                TableColumn<Course, String> codeCol = new TableColumn<>("Code");
                codeCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCourseCode()));
                codeCol.setPrefWidth(120);

                TableColumn<Course, Integer> creditsCol = new TableColumn<>("Credits");
                creditsCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCredit()).asObject());
                creditsCol.setPrefWidth(80);

                TableColumn<Course, String> gradeCol = new TableColumn<>("Grade");
                gradeCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGrade()));
                gradeCol.setPrefWidth(80);

                TableColumn<Course, String> teachersCol = new TableColumn<>("Instructors");
                teachersCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTeachers()));
                teachersCol.setPrefWidth(200);

                coursesTable.getColumns().add(courseCol);
                coursesTable.getColumns().add(codeCol);
                coursesTable.getColumns().add(creditsCol);
                coursesTable.getColumns().add(gradeCol);
                coursesTable.getColumns().add(teachersCol);

                System.out.println("New columns created and added with LAMBDA");
            }
        } catch (Exception e) {
            System.out.println("ERROR in setupTableView: " + e.getMessage());
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    private void debugTableState() {
        System.out.println("=== TABLE STATE DEBUG ===");
        if (coursesTable != null) {
            System.out.println("Table items: " + coursesTable.getItems().size());
            System.out.println("Table columns: " + coursesTable.getColumns().size());

            for (int i = 0; i < coursesTable.getColumns().size(); i++) {
                TableColumn<Course, ?> col = coursesTable.getColumns().get(i);
                System.out.println("Column " + i + " '" + col.getText() + "' has cell factory: " + (col.getCellValueFactory() != null));
            }

            System.out.println("Table visible: " + coursesTable.isVisible());
            System.out.println("Table managed: " + coursesTable.isManaged());
        }
    }

    private void loadData() {
        try {
            System.out.println("=== LOADING DATA ===");

            ObservableList<Course> courses = ResultData.getCourses();
            double gpa = ResultData.getGpa();
            double totalCredits = ResultData.getTotalCredits();

            System.out.println("From ResultData - Courses: " + courses.size() + ", GPA: " + gpa + ", Credits: " + totalCredits);

            if (courses.isEmpty()) {
                System.out.println("NO COURSES FOUND IN RESULTDATA!");
                showDefaultValues();
                return;
            }


            System.out.println("=== COURSE DETAILS ===");
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                System.out.println("Course " + i + ": " + course.getCourseName() +
                        " | Code: " + course.getCourseCode() +
                        " | Credits: " + course.getCredit() +
                        " | Grade: " + course.getGrade() +
                        " | Teacher1: " + course.getTeacher1() +
                        " | Teacher2: " + course.getTeacher2() +
                        " | Teachers: " + course.getTeachers());
            }


            System.out.println("Setting table items...");
            coursesTable.setItems(courses);
            System.out.println("Table items set. Table now has: " + coursesTable.getItems().size() + " items");


            coursesTable.refresh();
            System.out.println("Table refreshed");


            gpaLabel.setText(String.format("%.2f", gpa));
            totalCoursesCountLabel.setText(String.valueOf(courses.size()));
            totalCreditsLabel.setText(String.format("%.1f", totalCredits));
            performanceLabel.setText(getPerformanceComment(gpa));

            System.out.println("=== DATA LOADING COMPLETE ===");

        } catch (Exception e) {
            System.out.println("ERROR in loadData: " + e.getMessage());
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, e);

            showDefaultValues();
        }
    }

    private void showDefaultValues() {
        gpaLabel.setText("0.00");
        performanceLabel.setText("No data available");
        totalCoursesCountLabel.setText("0");
        totalCreditsLabel.setText("0.0");
    }

    private String getPerformanceComment(double gpa) {
        if (gpa >= 3.7) return "Excellent! First Class Performance 🏆";
        else if (gpa >= 3.3) return "Very Good! Keep up the good work 👍";
        else if (gpa >= 2.7) return "Good! Solid performance 💪";
        else if (gpa >= 2.0) return "Satisfactory. Room for improvement 📈";
        else return "Needs improvement. Consider academic support 📚";
    }

    @FXML
    private void handleNewCalculation() {
        try {
            Main.showCalculatorScene();
        } catch (Exception e) {
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    @FXML
    private void handleBackToHome() {
        try {
            Main.showHomeScene();
        } catch (Exception e) {
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, e);

        }
    }
}