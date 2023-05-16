package com.example.proyectofinal.controller;

import com.example.proyectofinal.model.dao.PurchaseDAO;
import com.example.proyectofinal.model.domain.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
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


    private ObservableList<Purchase> purchase;

    PurchaseDAO purchaseDAO = new PurchaseDAO();

    public void initialize() throws SQLException{
        purchase = FXCollections.observableArrayList();
        this.col_dni.setCellValueFactory(new PropertyValueFactory("dni_usuario"));
        this.col_id.setCellValueFactory(new PropertyValueFactory("id_producto"));
        this.col_cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.col_fecha.setCellValueFactory(new PropertyValueFactory("fecha_compra"));
        generateTable();
    }

    private void generateTable() throws SQLException {
        List<Purchase> aux = purchaseDAO.findAll();
        purchase.setAll(aux);
        this.ta_Purchase.setItems(purchase);
    }


}
