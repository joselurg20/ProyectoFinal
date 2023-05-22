import com.example.proyectofinal.model.dao.UserDAO;
import com.example.proyectofinal.model.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class MercadoTest {
    static UserDAO user;

    @BeforeAll
    public static void setUpClass(){
        user = new UserDAO();
    }

        @Test
        @DisplayName("crear una persona")
        void testCreatePersona() throws SQLException {
            String query = "";
        }

    @Test
    @DisplayName("insertar una persona")
        private void insertarPersona(Connection connection, User user) throws SQLException {
            String query = "INSERT INTO usuario  (nombre, Apellido) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.executeUpdate();
        }
    @Test
    @DisplayName("select una persona")
        private User obtenerPersona(Connection connection, int id) throws SQLException {
            String query = "SELECT * FROM personas WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            // Ejecutar la consulta y obtener el resultado
            // Aquí deberías tener el código para obtener la persona de la base de datos y crear un objeto Persona con los datos obtenidos
            return null; // Reemplaza esta línea con el código adecuado
        }


}