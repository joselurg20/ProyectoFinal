import com.example.proyectofinal.model.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoTest {
    @Test
    @DisplayName("crear una producto")
    private void insertarProducto(Connection connection, Product producto) throws SQLException {
        String query = "INSERT INTO productos (id, nombre, precio) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, producto.getId());
        statement.setString(2, producto.getNombre());
        statement.setDouble(3, producto.getPrecio());
        statement.executeUpdate();
    }
    @Test
    @DisplayName("select una producto")
    private Product obtenerProducto(Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM productos WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id1 = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            double precio = resultSet.getDouble("precio");
            return new Product();
        }

        return null;
    }
}
