module com.example.calculator_builder {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.logging;


    opens com.example.calculator_builder to javafx.fxml;
    exports com.example.calculator_builder;
}