package com.example.calculator_builder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        showHomeScene();
        primaryStage.setTitle("GPA Calculator");
        primaryStage.show();
    }

    public static void showHomeScene() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("home.fxml")));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home - GPA Calculator");
    }

    public static void showCalculatorScene() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("calculator.fxml")));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GPA Calculator");
    }

    // SIMPLE VERSION - Use this one with ResultData class
    public static void showResultScene() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("result.fxml")));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GPA Calculation Results");
    }
    public static void main(String[] args) {
        launch(args);
    }
}