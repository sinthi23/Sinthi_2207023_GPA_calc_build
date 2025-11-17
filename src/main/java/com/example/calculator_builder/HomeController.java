package com.example.calculator_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button startButton;

    @FXML
    private void handleStartButton(ActionEvent event) {
        try {

            Parent calculatorRoot = FXMLLoader.load(
                    getClass().getResource("calculator.fxml")
            );


            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();


            Scene scene = new Scene(calculatorRoot);
            stage.setScene(scene);
            stage.setTitle("GPA Calculator - Add Courses");
            stage.show(); // VERY IMPORTANT

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
