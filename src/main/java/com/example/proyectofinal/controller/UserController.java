package com.example.proyectofinal.controller;

import com.example.proyectofinal.model.dao.UserDAO;
import com.example.proyectofinal.model.domain.Users;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserController{
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
    private ObservableList <Users> users;
    @FXML
    void delete(ActionEvent event) {

    }
    @FXML
    void save(ActionEvent event) {

    }

    UserDAO udao = new UserDAO();

    public void inizialize() throws SQLException {

        users = FXCollections.observableArrayList();
        this.columnDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        this.columnDni.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnDni.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        generateTable();
    }


    @FXML
    public void generateTable() throws SQLException {
        List<Users> aux = udao.findAll();
        users.setAll(aux);

        this.llistUsers.setItems(users);
    }
}
