package com.example.proyectofinal.model.dao;

import com.example.proyectofinal.interfaces.DAO;
import com.example.proyectofinal.model.domain.Users;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements DAO<Users> {
    private final static String FINDALL = "select * from usuario";
    private final static String FINDBYID = "select * form usuario where dni = ?";
    private final static String INSERT = "insert into usuario (dni,nombre,apellido) values (?,?,?)";
    private final static String UPDATE= "update usuario set nombre = ?, apellido= ? where dni= ?" ;
    private final static String DELETE = "delete from usuario where dni = ?" ;

    private Connection conn;
    public UserDAO(Connection conn){
        this.conn = conn;
    }


    @Override
    public List<Users> findAll() throws SQLException {

        return null;
    }

    @Override
    public Users dinfById(String id) throws SQLException {
        return null;
    }

    @Override
    public Users save(Users entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Users entity) throws SQLException {

    }

    @Override
    public void close() throws Exception {

    }
}
