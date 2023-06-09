package com.example.proyectofinal.controller;

import com.example.proyectofinal.model.dao.ProductDAO;
import com.example.proyectofinal.model.dao.PurchaseDAO;
import com.example.proyectofinal.model.dao.UserDAO;
import com.example.proyectofinal.model.domain.Product;
import com.example.proyectofinal.model.domain.Purchase;
import com.example.proyectofinal.model.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PurchaseController {

    @FXML
    private TableColumn col_cantidad;

    @FXML
    private TableColumn col_dni;

    @FXML
    private TableColumn col_fecha;

    @FXML
    private TableColumn col_id;

    @FXML
    private TableView<Purchase> ta_Purchase;
    @FXML
    private ComboBox<User> chooser;
    @FXML
    private ComboBox<Product> chspro;

    @FXML
    private Button save;
    @FXML
    private Button delete;
    @FXML
    private Spinner<Integer> cant;
    private ObservableList<Purchase> purchase  = FXCollections.observableArrayList();

    PurchaseDAO purchaseDAO = new PurchaseDAO();
    /**
     * El initialize para poder pillar la base de datos
     * @throws SQLException
     */

    public void initialize() throws SQLException {
        purchase = FXCollections.observableArrayList();
        this.col_dni.setCellValueFactory(new PropertyValueFactory("u"));
        this.col_id.setCellValueFactory(new PropertyValueFactory("p"));
        this.col_cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.col_fecha.setCellValueFactory(new PropertyValueFactory("fecha_compra"));
        generateTable();
        UserDAO userDAO = new UserDAO();
        List<User> usuarios = userDAO.findAll();
        chooser.setItems(FXCollections.observableArrayList(usuarios));

        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.findAll();
        chspro.setItems(FXCollections.observableArrayList(products));


        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);

        valueFactory.setValue(1);
        cant.setValueFactory(valueFactory);


    }
    /**
     * te muestra el Array de comprar de la base de datos
     * @throws SQLException
     */
    private void generateTable() throws SQLException {
        List<Purchase> aux = purchaseDAO.findAll();
        purchase.setAll(aux);
        this.ta_Purchase.setItems(purchase);
    }


    /**
     * boton o accion para el boton que guarda y mete al compra
     * @param event
     */

    @FXML
    void save(ActionEvent event) {
        Product productoSeleccionado = chspro.getValue();
        int cantidad = cant.getValue();

        if (productoSeleccionado != null) {
            try {
                // Obtén el usuario seleccionado del ComboBox
                User usuarioSeleccionado = chooser.getSelectionModel().getSelectedItem();

                if (usuarioSeleccionado != null) {
                    // Crea una instancia de la clase Purchase con los valores proporcionados
                    Purchase compra = new Purchase(usuarioSeleccionado, productoSeleccionado, cantidad, LocalDate.now());

                    // Guarda la compra en la base de datos
                    purchaseDAO.save(compra);

                    // Actualiza la tabla de compras
                    generateTable();
                } else {
                    System.out.println("No se ha seleccionado ningún usuario.");
                }
            } catch (SQLException e) {
                System.out.println("Error al guardar la compra en la base de datos: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún producto.");
        }
    }


    @FXML
    void delete(ActionEvent event) {
        Purchase selectedPurchase = ta_Purchase.getSelectionModel().getSelectedItem();
        if (selectedPurchase != null) {
            try {
                // Eliminar el usuario de la base de datos
                purchaseDAO.delete(selectedPurchase);
                // Eliminar el usuario de la lista observable
                purchase.remove(selectedPurchase);
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar el error de la consulta SQL
            }
        }
    }





}
