package com.example.proyectofinal.model.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
public class ConnectionMySQL {
    private String file = "connexion.xml";
    private static ConnectionMySQL _newIntance;
    private static Connection con;

    private ConnectionMySQL(){
        ConnexionData dc = loadXml();
        try {
            con = DriverManager.getConnection(dc.getServer()+"/"+dc.getDatabase(), dc.getUsername(), dc.getPassword());

        }catch (SQLException e){
            con = null;
            e.printStackTrace();
        }
    }

    public static Connection getConnect() {
        if(_newIntance == null ){
            _newIntance = new ConnectionMySQL();
        }
        return con;
    }
    private ConnexionData loadXml() {
        ConnexionData con = new ConnexionData();
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(ConnexionData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ConnexionData newR = (ConnexionData) jaxbUnmarshaller.unmarshal(new File(file));
            con = newR;

        }catch (JAXBException e){
            e.printStackTrace();
        }
        return con;
    }
}
