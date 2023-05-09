package com.example.proyectofinal;

import com.example.proyectofinal.model.domain.Users;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class UserController {
    @FXML
    public TableView tablita;
    @FXML
    public Button boton;

    @FXML
    public void buenas()throws IOException {
        App.setRoot("login");
    }

}
