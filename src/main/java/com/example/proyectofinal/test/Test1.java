package com.example.proyectofinal.test;

import com.example.proyectofinal.model.dao.ProductDAO;
import com.example.proyectofinal.model.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class Test1 {
    public static void main(String[] args) throws SQLException {

       /* Users u = new Users("2","Alejandro","Espejo");

        UserDAO userDAO = new UserDAO();
         List<Users> users = userDAO.findAll();

        for (Users u1:users
             ) {
            System.out.println(u1);

        }

        */ProductDAO pdao = new ProductDAO();
        List<Product> products = pdao.findAll();
        for (Product p : products
             ) {
            System.out.println(p);

        }







    }
}
