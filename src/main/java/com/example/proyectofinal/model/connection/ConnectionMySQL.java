package com.example.proyectofinal.model.connection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL{
    private String file = "conexion.xml";
    private static ConnectionMySQL _newInstance;
    private static Connection con;

    private ConnectionMySQL(){
        ConnexionData dc = loadXML();
        try{
            con = DriverManager.getConnection(dc.getServer()
            + "/" +dc.getDatabase(), dc.getUsername(), dc.getPassword());
        } catch (SQLException e) {
            con = null;
            e.printStackTrace();
        }
    }
    public static Connection getConnect(){
        if (_newInstance== null){
            _newInstance= new ConnectionMySQL();
        }
        return con;
    }

    private ConnexionData loadXML() {
        ConnexionData con = new ConnexionData();
        JAXBContext jaxbContext;
        try{
            jaxbContext=JAXBContext.newInstance(ConnexionData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ConnexionData newR = (ConnexionData) jaxbUnmarshaller.unmarshal(new File(file));
            con = newR;
        }catch (JAXBException e){
            e.printStackTrace();
        }
        return con;
    }
}