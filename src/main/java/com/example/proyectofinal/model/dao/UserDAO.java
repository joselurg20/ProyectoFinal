package com.example.proyectofinal.model.dao;

import com.example.proyectofinal.interfaces.DAO;
import com.example.proyectofinal.model.connection.ConnectionMySQL;
import com.example.proyectofinal.model.domain.Product;
import com.example.proyectofinal.model.domain.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<Users> {
    private final static String FINDALL = "select * from usuario";
    private final static String FINDBYID ="select * from usuario where dni = ?";
    private final static String INSERT = "insert into usuario (dni,nombre,apellido) values (?,?,?)";
    private final static String UPDATE= "update usuario set nombre = ?, apellido= ? where dni= ?" ;
    private final static String DELETE = "delete from usuario where dni = ?" ;

    private Connection conn;
    public UserDAO(Connection conn){
        this.conn = conn;
    }

    public UserDAO() {
        this.conn= ConnectionMySQL.getConnect();
    }

    @Override
    public List<Users> findAll() throws SQLException {
        List<Users> result = new ArrayList();
        try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
            try(ResultSet res = pst.executeQuery()){
                while(res.next()) {
                    Users u = new Users();
                    u.setDNI(res.getString("dni"));
                    u.setFirst_name(res.getString("nombre"));
                    u.setLast_name(res.getString("apellidos"));
                    result.add(u);
                }
            }
        }
        return result;
    }

    @Override
    public Users findById(String dni) throws SQLException {
        Users result = null;
        try(PreparedStatement pst=this.conn.prepareStatement(FINDBYID)){
            pst.setString(1, dni);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()) {
                    result = new Users();
                    result.setDNI(res.getString("dni"));
                    result.setFirst_name(res.getString("nombre"));
                    result.setLast_name(res.getString("apellidos"));
                }
            }
        }
        return result;
    }

    @Override
    public Users save(Users entity) throws SQLException {
        Users result = new Users();
        if (entity != null) {
            Users u = findById(entity.getDNI());
            if (u == null) {
                //insertar
                try (PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                    pst.setString(1, entity.getDNI());
                    pst.setString(2, entity.getFirst_name());
                    pst.setString(3, entity.getLast_name());
                    pst.executeUpdate();
                }
            }
            //actualiza
        } else {
            try(PreparedStatement pst=this.conn.prepareStatement(UPDATE)) {
                pst.setString(1, entity.getFirst_name());
                pst.setString(2, entity.getLast_name());
                pst.setString(3, entity.getDNI());
                pst.executeUpdate();
            }
            result=entity;
        }
        return result;
    }

        @Override
        public void delete (Users entity) throws SQLException {

        }

        @Override
        public void close () throws Exception {

        }
    }
