module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;


    opens com.example.proyectofinal to javafx.fxml;
    exports com.example.proyectofinal;
    exports com.example.proyectofinal.controller;
    opens com.example.proyectofinal.controller to javafx.fxml;
}