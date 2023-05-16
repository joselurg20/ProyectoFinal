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
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO implements DAO<Purchase> {
    private final static String FINDALL = "select * from producto";
    private final static String FINDBYID = "select * form producto where id = ?";
    private final static String INSERT = "insert into usuario (id_producto,nombre,pais_origen,fecha_caducidad, precio) values (?,?,?)";
    private final static String UPDATE= "update usuario set nombre = ?, apellido= ? where id= ?" ;
    private final static String DELETE = "delete from usuario where id = ?" ;

    private Connection conn;
    public PurchaseDAO(Connection conn){
        this.conn = conn;
    }
    public PurchaseDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    @Override
    public List<Purchase> findAll() throws SQLException {
        List<Purchase> result = new ArrayList();
        try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
            try(ResultSet res = pst.executeQuery()){
                while(res.next()) {
                    Purchase pur = new Purchase();
                    UserDAO udao = new UserDAO(this.conn);
                    User u = udao.findById(res.getString("dni_usuario"));
                    ProductDAO pdao = new ProductDAO(this.conn);
                    Product p = pdao.findById(res.getString("id_producto"));
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
        return null;
    }

    @Override
    public Purchase save(Purchase entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Purchase entity) throws SQLException {

    }

    @Override
    public void close() throws Exception {

    }
}
