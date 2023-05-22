package com.example.proyectofinal.controller;

import com.example.proyectofinal.App;
import com.example.proyectofinal.model.dao.ProductDAO;
import com.example.proyectofinal.model.domain.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
    @FXML
    private TableColumn col_fecha;

    @FXML
    private TableColumn col_id;

    @FXML
    private TableColumn col_nombre;

    @FXML
    private TableColumn col_pais;

    @FXML
    private TableColumn col_precio;

    @FXML
    private TableView<Product> tableProducts;

    @FXML
    private Button back;

    @FXML
    private Button btn_purchse;


    private ObservableList<Product> product;
    ProductDAO productDAO = new ProductDAO();


    public void initialize() throws SQLException {

        product = FXCollections.observableArrayList();
        this.col_id.setCellValueFactory(new PropertyValueFactory("id"));
        this.col_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.col_pais.setCellValueFactory(new PropertyValueFactory("pais_origen"));
        this.col_fecha.setCellValueFactory(new PropertyValueFactory("fecha_caducidad"));
        this.col_precio.setCellValueFactory(new PropertyValueFactory("precio"));
        generateTable();

    }

    private void generateTable() throws SQLException {
        List<Product> aux = productDAO.findAll();
        product.setAll(aux);
        this.tableProducts.setItems(product);
    }

    @FXML
    public void setButton () throws IOException {
        App.setRoot("userview");
    }

    @FXML
    public void btnPurchase () throws IOException{
        App.setRoot("viewPurchase");
    }
}
