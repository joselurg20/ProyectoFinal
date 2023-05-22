package com.example.proyectofinal.controller;

import com.example.proyectofinal.model.dao.PurchaseDAO;
import com.example.proyectofinal.model.domain.Product;
import com.example.proyectofinal.model.domain.Purchase;
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

    @FXML
    private TextField chos_pro;

    @FXML
    private TextField chos_qua;

    @FXML
    private Button save;


    private ObservableList<Purchase> purchase;

    PurchaseDAO purchaseDAO = new PurchaseDAO();
    /**
     * El initialize para poder pillar la base de datos
     * @throws SQLException
     */

    public void initialize() throws SQLException{
        purchase = FXCollections.observableArrayList();
        this.col_dni.setCellValueFactory(new PropertyValueFactory("u"));
        this.col_id.setCellValueFactory(new PropertyValueFactory("p"));
        this.col_cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.col_fecha.setCellValueFactory(new PropertyValueFactory("fecha_compra"));
        generateTable();
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
        int idProducto = Integer.parseInt(chos_pro.getText());
        int cantidad = Integer.parseInt(chos_qua.getText());

        Purchase purchase = new Purchase();
        Product product = new Product();
        product.setId(idProducto);
        purchase.setP(product);
        purchase.setCantidad(cantidad);

        try {
            PurchaseDAO purchaseDAO = new PurchaseDAO();
            purchaseDAO.save(purchase);
            // Realizar cualquier otra acción necesaria después de guardar la compra
            generateTable(); // Actualizar la tabla después de guardar la compra
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar cualquier excepción o mostrar un mensaje de error apropiado
        }
    }






}
