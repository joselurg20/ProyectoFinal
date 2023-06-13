package com.example.proyectofinal.controller;

import com.example.proyectofinal.model.dao.ProductDAO;
import com.example.proyectofinal.model.dao.PurchaseDAO;
import com.example.proyectofinal.model.dao.UserDAO;
import com.example.proyectofinal.model.domain.Product;
import com.example.proyectofinal.model.domain.Purchase;
import com.example.proyectofinal.model.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;
import java.util.List;

public class Consulta {

    @FXML
    private TableColumn col_cant;

    @FXML
    private TableColumn col_dni;

    @FXML
    private TableColumn col_fecha;

    @FXML
    private TableColumn col_pro;

    @FXML
    private TableView<Purchase> ta_Purchase;

    @FXML
    private TextField buscador;

    private ObservableList<Purchase> purchase  = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Purchase> filtropurch = FXCollections.observableArrayList();

    PurchaseDAO purchaseDAO = new PurchaseDAO();


    public void initialize() throws SQLException {
        purchase = FXCollections.observableArrayList();
        this.col_dni.setCellValueFactory(new PropertyValueFactory("u"));
        this.col_pro.setCellValueFactory(new PropertyValueFactory("p"));
        this.col_cant.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.col_fecha.setCellValueFactory(new PropertyValueFactory("fecha_compra"));
        generateTable();



    }

        private void generateTable() throws SQLException {
            List<Purchase> aux = purchaseDAO.findAll();
            purchase.setAll(aux);
            this.ta_Purchase.setItems(purchase);
        }
        @FXML
        private void filtrarNombre (KeyEvent event){
            String filtroNombre = this.buscador.getText();
            if (filtroNombre.isEmpty()){
                this.ta_Purchase.setItems(purchase);
            }else{
                this.filtropurch.clear();
                for (Purchase p : this.purchase) {
                    if (p.getP().getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) {
                        this.filtropurch.add(p);
                    }
                }
                this.ta_Purchase.setItems(filtropurch);
        }
    }



}
