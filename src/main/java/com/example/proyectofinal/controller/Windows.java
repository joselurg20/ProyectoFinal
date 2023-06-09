package com.example.proyectofinal.controller;

import com.example.proyectofinal.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Windows {

    @FXML
    private Button logOut;

    @FXML
    private Button vr_products;

    @FXML
    private Button vr_users;

    @FXML
    public void setLogOut() throws IOException {
        App.setRoot("mercadoLibre");
    }
    @FXML
    public void setProducts() throws IOException {
        App.setRoot("listProduct");
    }
    @FXML
    public void setUsers() throws IOException {
        App.setRoot("userview");
    }


}

