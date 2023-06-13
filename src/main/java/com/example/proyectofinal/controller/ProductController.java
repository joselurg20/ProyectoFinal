package com.example.proyectofinal.controller;

import com.example.proyectofinal.App;
import com.example.proyectofinal.model.dao.ProductDAO;
import com.example.proyectofinal.model.domain.Product;
import com.example.proyectofinal.model.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
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

    @FXML
    private Button borrar;

    @FXML
    private TextField Txtfecha;

    @FXML
    private Button guardar;

    @FXML
    private TextField Txtid;

    @FXML
    private TextField Txtnombre;

    @FXML
    private TextField Txtpais;

    @FXML
    private TextField Txtprecio;
    @FXML
    private TextField txtFitrarnombre;
    @FXML
    private Button editar;

    private ObservableList<Product> product;
    @FXML
    private ObservableList<Product> filtroProduct = FXCollections.observableArrayList();
    ProductDAO productDAO = new ProductDAO();


    /**
     * Método de inicialización del controlador.
     * @throws SQLException Si ocurre un error durante la obtención de productos de la base de datos.
     */
    public void initialize() throws SQLException {
        product = FXCollections.observableArrayList();
        this.col_id.setCellValueFactory(new PropertyValueFactory("id"));
        this.col_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.col_pais.setCellValueFactory(new PropertyValueFactory("pais_origen"));
        this.col_fecha.setCellValueFactory(new PropertyValueFactory("fecha_caducidad"));
        this.col_precio.setCellValueFactory(new PropertyValueFactory("precio"));
        generateTable();

        tableProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectProduct();
            }
        });
    }

    /**
     * Genera la tabla de productos y la muestra en la interfaz.
     * @throws SQLException Si ocurre un error durante la obtención de productos de la base de datos.
     */
    private void generateTable() throws SQLException {
        List<Product> aux = productDAO.findAll();
        product.setAll(aux);
        this.tableProducts.setItems(product);
    }

    /**
     * Método de acción para el botón "back".
     * Redirige a la vista "userview".
     * @throws IOException Si ocurre un error durante la redirección a la vista "userview".
     */
    @FXML
    public void setButton() throws IOException {
        App.setRoot("windows");
    }

    /**
     * Método de acción para el botón "btn_purchse".
     * Redirige a la vista "viewPurchase" si se selecciona un producto en la tabla.
     * @throws IOException Si ocurre un error durante la redirección a la vista "viewPurchase".
     */
    @FXML
    public void btnPurchase() {
        Product selectedProduct = tableProducts.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                String rutaFXML = getClass().getResource("/com/example/proyectofinal/consulta.fxml").toExternalForm();
                FXMLLoader loader = new FXMLLoader(new URL(rutaFXML));
                Parent root = loader.load();
                Consulta consulta = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    void save (ActionEvent event)  {
            int id = Integer.parseInt(Txtid.getText());
            String nombre = Txtnombre.getText();
            String pais = Txtpais.getText();
            LocalDate fecha = LocalDate.parse(Txtfecha.getText());
            double precio = Double.parseDouble(Txtprecio.getText());

            Product product = new Product();
            product.setId(id);
            product.setNombre(nombre);
            product.setPais_origen(pais);
            product.setFecha_caducidad(fecha);
            product.setPrecio(precio);
            clear();

            try {
                ProductDAO productDAO = new ProductDAO();
                productDAO.save(product);
                generateTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    @FXML
    void delete(ActionEvent event) {
        // Obtener el producto seleccionado en la tabla
        Product selectedProduct = tableProducts.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            try {
                // Eliminar el producto de la base de datos
                productDAO.delete(selectedProduct);

                // Eliminar el producto de la lista observable y actualizar la tabla
                product.remove(selectedProduct);
                tableProducts.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void update(ActionEvent event) {
        Product selectedProduct = tableProducts.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            // Obtener los nuevos valores de los campos de texto
            int id = Integer.parseInt(Txtid.getText());
            String nombre = Txtnombre.getText();
            String pais = Txtpais.getText();
            LocalDate fecha = LocalDate.parse(Txtfecha.getText());
            double precio = Double.parseDouble(Txtprecio.getText());

            // Actualizar los valores del producto seleccionado
            selectedProduct.setId(id);
            selectedProduct.setNombre(nombre);
            selectedProduct.setPais_origen(pais);
            selectedProduct.setFecha_caducidad(fecha);
            selectedProduct.setPrecio(precio);

            try {
                // Actualizar el producto en la base de datos
                ProductDAO productDAO = new ProductDAO();
                productDAO.update(selectedProduct);

                // Actualizar la tabla
                tableProducts.refresh();
                clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void selectProduct() {
        // Obtener el producto seleccionado en la tabla
        Product selectedProduct = tableProducts.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            // Rellenar los campos de texto con los datos del producto seleccionado
            Txtid.setText(String.valueOf(selectedProduct.getId()));
            Txtnombre.setText(selectedProduct.getNombre());
            Txtpais.setText(selectedProduct.getPais_origen());
            Txtfecha.setText(selectedProduct.getFecha_caducidad().toString());
            Txtprecio.setText(String.valueOf(selectedProduct.getPrecio()));
        }
    }
    @FXML
    private void filtrarNombre (KeyEvent event){
        String filtroNombre = this.txtFitrarnombre.getText();
        if (filtroNombre.isEmpty()){
            this.tableProducts.setItems(product);
        }else{
            this.filtroProduct.clear();
            for (Product p : this.product) {
                if (p.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) {
                    this.filtroProduct.add(p);
                }
            }
            this.tableProducts.setItems(filtroProduct);
        }
    }


    @FXML
    void clear() {
        Txtid.clear();
        Txtnombre.clear();
        Txtpais.clear();
        Txtfecha.clear();
        Txtprecio.clear();
        tableProducts.getSelectionModel().clearSelection();
    }
}
