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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TextField chos_pro;
    @FXML
    private TextField chos_us;

    @FXML
    private TextField chos_qua;

    @FXML
    private Button save;
    @FXML
    private Button delete;


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
        String strId = chos_pro.getText();
        String strCantidad = chos_qua.getText();

        if (!strId.isEmpty() && !strCantidad.isEmpty()) {
            try {
                int id = Integer.parseInt(strId);
                int cantidad = Integer.parseInt(strCantidad);

                // Obtén el producto seleccionado de la base de datos utilizando el ID
                ProductDAO productDAO = new ProductDAO();
                Product productoSeleccionado = productDAO.findById(String.valueOf(id));

                if (productoSeleccionado != null) {
                    // Obtén el usuario de la base de datos utilizando el DNI
                    String dni = "12345678"; // Aquí debes obtener el DNI del usuario de alguna manera
                    UserDAO userDAO = new UserDAO();
                    User usuario = userDAO.findById(dni);

                    if (usuario != null) {
                        // Crea una instancia de la clase Purchase con los valores proporcionados
                        Purchase compra = new Purchase(usuario, productoSeleccionado, cantidad, LocalDate.now());

                        // Guarda la compra en la base de datos
                        purchaseDAO.save(compra);

                        // Actualiza la tabla de compras
                        generateTable();
                    } else {
                        System.out.println("No se encontró un usuario con el DNI especificado.");
                    }
                } else {
                    System.out.println("No se encontró un producto con el ID especificado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("El ID o la cantidad no son números válidos.");
            } catch (SQLException e) {
                System.out.println("Error al guardar la compra en la base de datos: " + e.getMessage());
            }
        } else {
            System.out.println("El ID o la cantidad están vacíos.");
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
