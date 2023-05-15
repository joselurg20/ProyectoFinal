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
    private final static String FINDBYID = "select * form producto where id = ?";
    private final static String INSERT = "insert into usuario (id_producto,nombre,pais_origen,fecha_caducidad, precio) values (?,?,?)";
    private final static String UPDATE= "update usuario set nombre = ?, apellido= ? where id= ?" ;
    private final static String DELETE = "delete from usuario where id = ?" ;
    
    private Connection conn;
    public ProductDAO(Connection conn){
        this.conn = conn;
    }
    public ProductDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }


    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> result = new ArrayList();
        try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
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

    @Override
    public Product findById(String id) throws SQLException {
        return null;
    }

    @Override
    public Product save(Product entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Product entity) throws SQLException {

    }
    public List<Product> findByAutor(User entity) {
        return null;
    }
    @Override
    public void close() throws Exception {

    }


}
