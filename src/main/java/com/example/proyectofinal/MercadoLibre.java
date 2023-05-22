package com.example.proyectofinal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MercadoLibre {

    @FXML
    public Button button;

    /**
     * pagina principal y solo sirve para mostrar una vista
     * @throws IOException
     */
    @FXML
    public void setButton () throws IOException{
        App.setRoot("userview");
    }
}
