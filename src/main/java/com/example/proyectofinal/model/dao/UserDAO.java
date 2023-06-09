package com.example.proyectofinal.model.dao;

import com.example.proyectofinal.App;
import com.example.proyectofinal.interfaces.DAO;
import com.example.proyectofinal.model.connection.ConnectionMySQL;
import com.example.proyectofinal.model.domain.Product;
import com.example.proyectofinal.model.domain.User;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    private final static String FINDALL = "select * from usuario";
    private final static String FINDBYID ="select * from usuario where dni = ?";
    private final static String INSERT = "insert into usuario (dni,nombre,Apellido) values (?,?,?)";
    private final static String UPDATE= "update usuario set nombre = ?, Apellido = ? where dni= ?" ;
    private final static String DELETE = "delete from usuario where dni = ?" ;

    private Connection conn;
    public UserDAO (Connection conn){
        this.conn = conn;
    }
    public UserDAO (){
        this.conn=ConnectionMySQL.getConnect();
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> result = new ArrayList<>();
        try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
            try(ResultSet res = pst.executeQuery()){
                while(res.next()) {
                    User u = new User();
                    u.setDni(res.getString("dni"));
                    u.setNombre(res.getString("nombre"));
                    u.setApellido(res.getString("apellido"));

                    result.add(u);
                }
            }
        }
        return result;
    }

    @Override
    public User findById(String dni) throws SQLException {
        User result = null;
        try(PreparedStatement pst=this.conn.prepareStatement(FINDBYID)){
            pst.setString(1, dni);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()) {
                    result = new User();
                    result.setDni(res.getString("dni"));
                    result.setNombre(res.getString("nombre"));
                    result.setNombre(res.getString("apellido"));
                }
            }
        }
        return result;
    }

    @Override
    public User save(User entity) throws SQLException {
        User result = new User();
        if (entity != null) {
            User u = findById(entity.getDni());
            if (u == null) {
                // Insertar nuevo usuario
                try (PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                    pst.setString(1, entity.getDni());
                    pst.setString(2, entity.getNombre());
                    pst.setString(3, entity.getApellido());
                    pst.executeUpdate();
                }
            }
        }
        return result;
    }

    public void update(User entity) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement(UPDATE)) {
            pst.setString(1, entity.getNombre());
            pst.setString(2, entity.getApellido());
            pst.setString(3, entity.getDni());
            pst.executeUpdate();
        }
    }

    @Override
    public void delete (User entity) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement(DELETE)) {
            pst.setString(1, entity.getDni()); // Suponiendo que el objeto User tiene un método getDni() que devuelve el DNI del usuario
            pst.executeUpdate();
        }
    }

    @Override
    public void close () throws Exception {
        this.close();
    }

    public boolean existsUser(String dni) throws SQLException {
        // Realiza una consulta SQL para verificar si existe un usuario con el DNI dado
        String query = "SELECT COUNT(*) FROM usuario WHERE dni = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
        return false;
    }

}