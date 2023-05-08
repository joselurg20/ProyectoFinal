package com.example.proyectofinal.model.dao;

import com.example.proyectofinal.interfaces.DAO;
import com.example.proyectofinal.model.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class PoductDAO implements DAO<Product> {
    private final static String FINDALL = "select * from producto";
    private final static String FINDBYID = "select * form producto where id = ?";
    private final static String INSERT = "insert into usuario (id_producto,nombre,pais_origen,fecha_caducidad, precio) values (?,?,?)";
    private final static String UPDATE= "update usuario set nombre = ?, apellido= ? where id= ?" ;
    private final static String DELETE = "delete from usuario where id = ?" ;


    @Override
    public List<Product> findAll() throws SQLException {
        return null;
    }

    @Override
    public Product dinfById(String id) throws SQLException {
        return null;
    }

    @Override
    public Product save(Product entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Product entity) throws SQLException {

    }

    @Override
    public void close() throws Exception {

    }
}
