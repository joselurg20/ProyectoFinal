package com.example.proyectofinal.controller;

import com.example.proyectofinal.App;
import com.example.proyectofinal.model.dao.UserDAO;
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
import java.util.List;

public class UserController {
    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_save;

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
    private Button btn_compra;
    @FXML
    private ObservableList<User> user;
    UserDAO userDAO = new UserDAO();

    public void initialize() throws SQLException{

        user = FXCollections.observableArrayList();
        this.columnDni.setCellValueFactory(new PropertyValueFactory("Dni"));
        this.columnName.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columSecondName.setCellValueFactory(new PropertyValueFactory("Apellido"));

        generateTable();

    }
    @FXML
    public void generateTable() throws SQLException{
        List<User> aux = userDAO.findAll();
        user.setAll(aux);
        this.llistUsers.setItems(user);
    }

    @FXML
    void save(ActionEvent event) {
        // Obtener los valores de los campos de texto
        String dni = txtDni.getText();
        String nombre = txtName.getText();
        String apellido = txtSecondName.getText();

        // Crear un nuevo objeto Users con los valores ingresados
        User newUser = new User(dni, nombre, apellido);

        try {
            // Guardar el nuevo usuario en la base de datos
            userDAO.save(newUser);
            // Agregar el nuevo usuario a la lista observable
            user.add(newUser);

            // Limpiar los campos de texto
            txtDni.clear();
            txtName.clear();
            txtSecondName.clear();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar el error de la consulta SQL
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
    public void setCompra () throws IOException {
        App.setRoot("listProduct");
    }

}
