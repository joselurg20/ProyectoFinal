module com.example.proyectofinal {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires java.xml.bind;

    opens com.example.proyectofinal.controller to javafx.fxml;
    opens com.example.proyectofinal to java.xml.bind, javafx.fxml;
    opens com.example.proyectofinal.model.connection to java.xml.bind;

    opens com.example.proyectofinal.model.domain to javafx.base;
    exports com.example.proyectofinal;


}