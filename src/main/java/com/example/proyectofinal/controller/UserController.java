package com.example.proyectofinal.controller;

import com.example.proyectofinal.App;
import com.example.proyectofinal.model.dao.ProductDAO;
import com.example.proyectofinal.model.dao.UserDAO;
import com.example.proyectofinal.model.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserController {
    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_compra;

    @FXML
    private TableColumn<User, String> columnDni;

    @FXML
    private TableColumn<User, String> columnName;

    @FXML
    private TableColumn<User, String> columSecondName;

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
    private Button back;

    private ObservableList<User> users;
    private UserDAO userDAO;

    private User selectedUser;

    public void initialize() throws SQLException {
        userDAO = new UserDAO();
        users = FXCollections.observableArrayList();

        columnDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columSecondName.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        generateTable();

        llistUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectUser();
            }
        });
    }

    @FXML
    public void generateTable() throws SQLException {
        List<User> aux = userDAO.findAll();
        users.setAll(aux);
        llistUsers.setItems(users);
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
                userDAO.delete(selectedUser);
                users.remove(selectedUser);
            } catch (SQLException e) {
                e.printStackTrace();
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

            selectedUser.setDni(dni);
            selectedUser.setNombre(nombre);
            selectedUser.setApellido(apellido);

            try {
                userDAO.update(selectedUser);
                generateTable();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   @FXML
    void selectUsers(ActionEvent event) {
        selectedUser = llistUsers.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                // Cargar el archivo FXML de la vista de compra
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/viewPurchase.fxml"));

                // Cargar el nodo raíz y obtener el controlador de la vista de compra
                Parent root = fxmlLoader.load();
                PurchaseController purchaseController = fxmlLoader.getController();

                // Pasar el usuario seleccionado al controlador de la vista de compra
                purchaseController.setSelectedUser(selectedUser);

                // Crear una nueva ventana (Stage) y establecer la escena con el nodo raíz
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void selectUser() {
        User selectedItem = llistUsers.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
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
