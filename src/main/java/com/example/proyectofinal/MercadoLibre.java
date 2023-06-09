package com.example.proyectofinal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MercadoLibre {
    @FXML
    private Label labelUser;

    @FXML
    private Button btn_home;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField userField;


    /**
     * pagina principal y solo sirve para mostrar una vista
     * @throws IOException
     */
    @FXML
    public void setButton () throws IOException{
        if (userField.getText().equals("admin") && passField.getText().equals("admin")){
            labelUser.setText("Correct user and password!");
            labelUser.setTextFill(Color.GREEN);
            App.setRoot("windows");
        }else {
            labelUser.setText("Wrong username or password!");
            labelUser.setTextFill(Color.RED);
        }
    }
}
