import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public static void agregarUsuario(String nombre, int edad) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "INSERT INTO alumnos (nombre, edad) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setInt(2, edad);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }
    public static void modificarUsuario(int id, String nuevoNombre, int nuevaEdad) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "UPDATE alumnos SET nombre = ?, edad = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(2, nuevoNombre);
                statement.setInt(3, nuevaEdad);
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }
    public static void eliminarUsuario(int id) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "DELETE FROM alumnos WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }
    public static void eliminarTodosLosDatos() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            // Lógica para eliminar todos los datos
            String sql = "DELETE FROM alumnos";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }
    public static void mostrarTodosLosRegistros() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            // Lógica para mostrar todos los registros
            String sql = "SELECT * FROM alumnos";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("\n--- Todos los Registros ---");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nombre = resultSet.getString("nombre");
                        int edad = resultSet.getInt("edad");

                        System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }
}
