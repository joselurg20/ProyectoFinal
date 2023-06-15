package com.example.proyectofinal.model.dao;

import com.example.proyectofinal.interfaces.DAO;
import com.example.proyectofinal.model.connection.ConnectionMySQL;
import com.example.proyectofinal.model.domain.Product;
import com.example.proyectofinal.model.domain.Purchase;
import com.example.proyectofinal.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO implements DAO<Purchase> {
    private final static String FINDALL = "select * from carrito order by fecha_compra ";
    private final static String INSERT = "INSERT INTO carrito (dni_usuario, id_producto, cantidad, fecha_compra) VALUES (?, ?, ?, ?) ";
    private final static String SHOW = "SELECT u.nombre, u.Apellido, p.nombre as Producto, c.cantidad FROM carrito c JOIN usuario u ON u.dni = c.dni_usuario JOIN producto p ON c.id_producto = p.id_producto WHERE p.nombre = '?' ";
    private Connection conn;
    public PurchaseDAO(Connection conn){
        this.conn = conn;
    }
    public PurchaseDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }


    /**
     * Obtiene todas las compras del carrito.
     * @return Una lista de compras.
     * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
     */
    @Override
    public List<Purchase> findAll() throws SQLException {
        List<Purchase> result = new ArrayList();
        try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
            try(ResultSet res = pst.executeQuery()){
                while(res.next()) {
                    Purchase pur = new Purchase();
                    UserDAO udao = new UserDAO(this.conn);
                    User u = udao.findById(res.getString("dni_usuario"));
                    pur.setU(u);
                    ProductDAO pdao = new ProductDAO(this.conn);
                    Product p = pdao.findById(res.getString("id_producto"));
                    pur.setP(p);
                    pur.setCantidad(res.getInt("cantidad"));
                    pur.setFecha_compra(res.getDate("fecha_compra").toLocalDate());
                    result.add(pur);
                }
            }
        }
        return result;
    }

    @Override
    public Purchase findById(String id) throws SQLException {
        // Método no implementado en el código proporcionado
        return null;
    }

    /**
     * Guarda una compra en el carrito.
     * @param entity La compra a guardar.
     * @return La compra guardada.
     * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
     */
    @Override
    public Purchase save(Purchase entity) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
            pst.setString(1, entity.getU().getDni());  // Assuming 'getDni()' returns the user's dni
            pst.setInt(2, entity.getP().getId());  // Assuming 'getIdProducto()' returns the product's id
            pst.setInt(3, entity.getCantidad());
            pst.setDate(4, java.sql.Date.valueOf(entity.getFecha_compra()));
            pst.executeUpdate();
        }
        return entity;
    }

    /**
     * Elimina una compra del carrito.
     * @param entity La compra a eliminar.
     * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
     */
        @Override
            public void delete(Purchase entity) throws SQLException {
                String DELETE = "DELETE FROM carrito WHERE dni_usuario = ? AND id_producto = ?";
                try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
                pst.setString(1, entity.getU().getDni());  // Assuming 'getDni()' returns the user's dni
                pst.setInt(2, entity.getP().getId());  // Assuming 'getIdProducto()' returns the product's id
                pst.executeUpdate();
            }
        }

        public void show (Purchase entity) throws SQLException{
            try (PreparedStatement pst = this.conn.prepareStatement(SHOW)) {
                pst.setString(1,entity.getP().getNombre());
            }

        }



    @Override
    public void close() throws Exception {
        conn.close();
    }
}
