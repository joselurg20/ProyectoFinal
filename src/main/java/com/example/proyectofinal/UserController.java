package com.example.proyectofinal;

import com.example.proyectofinal.model.domain.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserController {
    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_save;

    @FXML
    private TableColumn<Users, String> columSecondName;

    @FXML
    private TableColumn<Users, String> columnDni;

    @FXML
    private TableColumn<Users, String> columnName;

    @FXML
    private TableView<Users> llistUsers;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSecondName;

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }


}
