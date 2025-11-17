module com.example.calculator_builder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.calculator_builder to javafx.fxml;
    exports com.example.calculator_builder;
}