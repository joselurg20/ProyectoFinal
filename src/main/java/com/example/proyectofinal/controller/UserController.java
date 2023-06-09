package com.example.proyectofinal.controller;

import com.example.proyectofinal.App;
import com.example.proyectofinal.model.dao.ProductDAO;
import com.example.proyectofinal.model.dao.UserDAO;
import com.example.proyectofinal.model.domain.Product;
import com.example.proyectofinal.model.domain.User;
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
import java.time.LocalDate;
import java.util.List;

public class UserController {
    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_save;
    @FXML
    private Button btn_compra;
    @FXML
    private TableColumn columSecondName;

    @FXML
    private TableColumn columnDni;

    @FXML
    private TableColumn columnName;

    @FXML
    private TableView<User> llistUsers;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSecondName;

    @FXML
    private Button editar;

    @FXML
    private  Button back;
    @FXML
    private ObservableList<User> user;
    UserDAO userDAO = new UserDAO();

    public void initialize() throws SQLException{

        user = FXCollections.observableArrayList();
        this.columnDni.setCellValueFactory(new PropertyValueFactory("Dni"));
        this.columnName.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columSecondName.setCellValueFactory(new PropertyValueFactory("Apellido"));

        generateTable();

        llistUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectUser();
            }
        });

    }
    @FXML
    public void generateTable() throws SQLException{
        List<User> aux = userDAO.findAll();
        user.setAll(aux);
        this.llistUsers.setItems(user);
    }
    @FXML
    public void setButton() throws IOException {
        App.setRoot("windows");
    }

    @FXML
    void save(ActionEvent event) {
        String dni = txtDni.getText();
        String nombre = txtName.getText();
        String apellido = txtSecondName.getText();

        User user = new User();
        user.setDni(dni);
        user.setNombre(nombre);
        user.setApellido(apellido);
        clearFields();

        try {
            UserDAO userDAO = new UserDAO();
            userDAO.save(user);
            generateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        User selectedUser = llistUsers.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                // Eliminar el usuario de la base de datos
                userDAO.delete(selectedUser);
                // Eliminar el usuario de la lista observable
                user.remove(selectedUser);
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar el error de la consulta SQL
            }
        }
    }

    @FXML
    void update(ActionEvent event) {
        User selectedUser = llistUsers.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            String dni = txtDni.getText();
            String nombre = txtName.getText();
            String apellido = txtSecondName.getText();

            // Actualizar los datos del usuario seleccionado
            selectedUser.setDni(dni);
            selectedUser.setNombre(nombre);
            selectedUser.setApellido(apellido);

            try {
                UserDAO userDAO = new UserDAO();
                userDAO.update(selectedUser); // Actualizar en la base de datos
                generateTable(); // Actualizar la tabla
                clearFields(); // Limpiar los campos de texto
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void selectUsers(ActionEvent event){
        User selectedUser = llistUsers.getSelectionModel().getSelectedItem();
        if (selectedUser != null){
            try {
                App.setRoot("viewPurchase");
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void selectUser(){
        User selectedItem = llistUsers.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Rellenar los campos de texto con los datos del producto seleccionado
            txtDni.setText(selectedItem.getDni());
            txtName.setText(selectedItem.getNombre());
            txtSecondName.setText(selectedItem.getApellido());

        }

    }

    private void clearFields() {
        txtDni.clear();
        txtName.clear();
        txtSecondName.clear();

    }




}
