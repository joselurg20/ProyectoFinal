package com.example.proyectofinal.model.dao;

import com.example.proyectofinal.interfaces.DAO;
import com.example.proyectofinal.model.connection.ConnectionMySQL;
import com.example.proyectofinal.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    private final static String FINDALL = "select * from usuario";
    private final static String FINDBYID = "select * from usuario where dni = ?";
    private final static String INSERT = "insert into usuario (dni,nombre,Apellido) values (?,?,?)";
    private final static String UPDATE = "update usuario set nombre = ?, Apellido = ? where dni= ?";
    private final static String DELETE = "delete from usuario where dni = ?";

    private Connection conn;

    /**
     * Constructor de la clase UserDAO que recibe una conexión existente.
     * @param conn Conexión a la base de datos.
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Constructor de la clase UserDAO que crea una nueva conexión.
     */
    public UserDAO() {
        this.conn = ConnectionMySQL.getConnect();
    }
    /**
     * Obtiene todos los usuarios de la base de datos.
     * @return Una lista de usuarios.
     * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
     */
    @Override
    public List<User> findAll() throws SQLException {
        List<User> result = new ArrayList<>();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
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
    /**
     * Busca un usuario por su DNI.
     * @param dni El DNI del usuario a buscar.
     * @return El usuario encontrado, o null si no se encuentra ningún usuario con el DNI especificado.
     * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
     */
    @Override
    public User findById(String dni) throws SQLException {
        User result = null;
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYID)) {
            pst.setString(1, dni);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result = new User();
                    result.setDni(res.getString("dni"));
                    result.setNombre(res.getString("nombre"));
                    result.setApellido(res.getString("apellido"));
                }
            }
        }
        return result;
    }
    /**
     * Guarda un usuario en la base de datos.
     * @param entity El usuario a guardar.
     * @return El usuario guardado.
     * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
     */
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
    /**
     * Elimina un usuario de la base de datos.
     * @param entity El usuario a eliminar.
     * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
     */
    @Override
    public void delete(User entity) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement(DELETE)) {
            pst.setString(1, entity.getDni()); // Suponiendo que el objeto User tiene un método getDni() que devuelve el DNI del usuario
            pst.executeUpdate();
        }
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }

    public void update(User entity) throws SQLException {
        User result = new User();
        if (entity != null) {
            User u = findById(entity.getDni());
            if (u == null) {
                // Actualizar usuario existente
                try (PreparedStatement pst = this.conn.prepareStatement(UPDATE)) {
                    pst.setString(1, entity.getNombre());
                    pst.setString(2, entity.getApellido());
                    pst.setString(3, entity.getDni());
                    pst.executeUpdate();
                }
                result = entity;
            }

        }
    }
}
