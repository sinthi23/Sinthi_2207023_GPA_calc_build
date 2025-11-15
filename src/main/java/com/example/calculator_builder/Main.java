package com.example.calculator_builder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Parent root = FXMLLoader.load(Main.class.getResource("home.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home - GPA Calculator");
    }

    public static void showCalculatorScene() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("calculator.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GPA Calculator");
    }

    public static void main(String[] args) {
        launch(args);
    }
}