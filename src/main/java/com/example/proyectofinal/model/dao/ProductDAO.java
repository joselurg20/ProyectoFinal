package com.example.proyectofinal.model.dao;

import com.example.proyectofinal.interfaces.DAO;
import com.example.proyectofinal.model.connection.ConnectionMySQL;
import com.example.proyectofinal.model.domain.Product;
import com.example.proyectofinal.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Product> {
    private final static String FINDALL = "select * from producto";
    private final static String FINDBYID = "select * from producto where id_producto = ?";
    private final static String INSERT = "insert into producto (id_producto, nombre, pais_origen, fecha_caducidad, precio) values (?,?,?,?,?)";
    private final static String UPDATE= "update producto set nombre = ?, pais_origen = ?,fecha_caducidad = ?,precio = ?  where id_producto= ?" ;
    private final static String DELETE = "delete from producto where id_producto = ?" ;


    private Connection conn;

    public ProductDAO(Connection conn){
        this.conn = conn;
    }

    public ProductDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    /**
     *  metodo que te muestra todos los productos de la base de datos metidos a fuego
     * @return result
     * @throws SQLException
     */
    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> result = new ArrayList<>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)){
            try(ResultSet res = pst.executeQuery()){
                while(res.next()) {
                    Product p = new Product();
                    p.setId(res.getInt("id_producto"));
                    p.setNombre(res.getString("nombre"));
                    p.setPais_origen(res.getString("pais_origen"));
                    p.setFecha_caducidad(res.getDate("fecha_caducidad").toLocalDate());
                    p.setPrecio(res.getDouble(5));
                    result.add(p);
                }
            }
        }
        return result;
    }

    /**
     * metedo para encontrar un producto por id
     * @param id
     * @return producto
     * @throws SQLException
     */

    @Override
    public Product findById(String id) throws SQLException {
        Product product = null;
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYID)) {
            pst.setString(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    // Si hay un resultado, crea una instancia de Product y establece sus propiedades utilizando los valores de la fila actual del ResultSet
                    product = new Product();
                    product.setId(res.getInt("id_producto"));
                    product.setNombre(res.getString("nombre"));
                    product.setPais_origen(res.getString("pais_origen"));
                    product.setFecha_caducidad(res.getDate("fecha_caducidad").toLocalDate());
                    product.setPrecio(res.getDouble("precio"));
                }
            }
        }
        return product;
    }

    @Override
    public Product save(Product entity) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
            pst.setInt(1, entity.getId());
            pst.setString(2, entity.getNombre());
            pst.setString(3, entity.getPais_origen());
            pst.setDate(4, java.sql.Date.valueOf(entity.getFecha_caducidad()));
            pst.setDouble(5, entity.getPrecio());
            pst.executeUpdate();
        }
        return entity;
    }

    public void update(Product entity) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement(UPDATE)) {
            pst.setString(1, entity.getNombre());
            pst.setString(2, entity.getPais_origen());
            pst.setDate(3, java.sql.Date.valueOf(entity.getFecha_caducidad()));
            pst.setDouble(4, entity.getPrecio());
            pst.setInt(5, entity.getId());
            pst.executeUpdate();
        }
    }



    @Override
    public void delete(Product entity) throws SQLException {
        // Método no implementado en el código proporcionado
        try (PreparedStatement pst = this.conn.prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId()); // Suponiendo que el objeto User tiene un método getDni() que devuelve el DNI del usuario
            pst.executeUpdate();
        }
    }



    @Override
    public void close() throws Exception {
        conn.close();
    }
}
