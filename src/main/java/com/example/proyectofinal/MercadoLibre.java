package com.example.proyectofinal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MercadoLibre {

    @FXML
    public Button button;

    @FXML
    public void setButton () throws IOException{
        App.setRoot("userview");
    }
}
