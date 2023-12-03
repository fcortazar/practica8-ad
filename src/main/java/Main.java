import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/file.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scanner scanner = new Scanner(System.in);
        int opcion;
        try (Connection conexion = DriverManager.getConnection(properties.getProperty("mysql.url"), properties.getProperty("mysql.usuario"), properties.getProperty("mysql.contrasena")
        )) {
            Statement useStatement = conexion.createStatement();
            String usePractica8 = "USE practica8";
            useStatement.execute(usePractica8);
            do {
                System.out.println("Menú:");
                System.out.println("1. Dar de alta/modificar/eliminar datos");
                System.out.println("2. Mostrar todos los registros");
                System.out.println("3. Búsqueda");
                System.out.println("4. Búsqueda con filtrado");
                System.out.println("5. Eliminar usuario");
                System.out.println("6. Recuperar último elemento borrado");
                System.out.println("7. Eliminar todos los datos del programa");
                System.out.println("8. Salir");
                System.out.print("Ingrese su opción: ");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        gestionarDatos(conexion, scanner);
                        break;
                    case 2:
                        mostrarRegistros(conexion);
                        break;
                    case 3:
                        buscarRegistro(conexion, scanner);
                        break;
                    case 4:
                        buscarConFiltrado(conexion, scanner);
                        break;
                    case 5:
                        eliminarUsuario(conexion, scanner);
                        break;
                    case 6:
                        recuperarUltimoBorrado(conexion);
                        break;
                    case 7:
                        eliminarTodosLosDatos(conexion);
                        break;
                    case 8:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                        break;
                }
            } while (opcion != 8);
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void gestionarDatos (Connection conexion, Scanner scanner) throws SQLException {
        System.out.println("Menú de Gestión de Datos:");
        System.out.println("1. Dar de alta");
        System.out.println("2. Modificar");
        System.out.println("3. Dar de baja");
        int opcionOperacion = scanner.nextInt();
        switch (opcionOperacion) {
            case 1:
                insertarRegistro(conexion, scanner);
                break;
            case 2:
                actualizarRegistro(conexion, scanner);
                break;
            case 3:
                eliminarRegistro(conexion, scanner);
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida (1-3).");
                break;
        }
    }
    private static void insertarRegistro(Connection conexion, Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Ingrese el nombre del nuevo usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el email del nuevo usuario: ");
        String email = scanner.nextLine();
        Statement statement = conexion.createStatement();
        String consulta = "INSERT INTO empresa.usuarios (nombre, email) VALUES ('" + nombre + "', '" + email + "')";
        statement.executeUpdate(consulta);
        System.out.println("Registro insertado correctamente.");
    }
    private static void actualizarRegistro(Connection conexion, Scanner scanner) throws SQLException {
        System.out.print("Ingrese la ID del usuario a modificar:");
        scanner.nextLine();
        String condicion = scanner.nextLine();
        System.out.print("Ingrese los datos actualizados en el formato 'campo1 = valor1, campo2 = valor2, ...': ");
        String valoresActualizados = scanner.nextLine();
        Statement statement = conexion.createStatement();
        String consulta = "UPDATE usuarios SET " + valoresActualizados + " WHERE " + condicion;
        statement.executeUpdate(consulta);
        System.out.println("Registro actualizado correctamente.");
    }
    private static void eliminarRegistro(Connection conexion, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el ID del usuario a dar de baja empezando por 1: ");
        scanner.nextLine();
        String condicion = scanner.nextLine();
        Statement statement = conexion.createStatement();
        String consulta = "DELETE FROM usuarios WHERE " + condicion;
        statement.executeUpdate(consulta);
        System.out.println("Registro eliminado correctamente.");
    }
    private static void mostrarRegistros(Connection conexion)
            throws SQLException {
        Statement statement = conexion.createStatement();
        String consulta = "SELECT * FROM empresa.usuarios";
        ResultSet resultado = statement.executeQuery(consulta);
        if (!resultado.isBeforeFirst()) {
            System.out.println("No hay usuarios para mostrar.");
        } else {
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Email: " + email);
                System.out.println("--------------------");
            }
        }
    }
    private static void buscarRegistro(Connection conexion, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el ID a buscar: ");
        int idBuscar = scanner.nextInt();
        Statement statement = conexion.createStatement();
        String consulta = "SELECT * FROM usuarios WHERE id = " + idBuscar;
        ResultSet resultado = statement.executeQuery(consulta);
        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            String email = resultado.getString("email");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Email: " + email);
            System.out.println("--------------------");
        }
    }
    private static void buscarConFiltrado(Connection conexion, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el filtro (por ejemplo, 'nombre =\"John\"'): ");
        scanner.nextLine();
        String filtro = scanner.nextLine();
        Statement statement = conexion.createStatement();
        String consulta = "SELECT * FROM usuarios WHERE " + filtro;
        ResultSet resultado = statement.executeQuery(consulta);
        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            String email = resultado.getString("email");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Email: " + email);
            System.out.println("--------------------");
        }
    }
    private static void eliminarUsuario(Connection conexion, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el nombre del usuario a eliminar: ");
        scanner.nextLine();
        String nombreUsuario = scanner.nextLine();
        Statement statement = conexion.createStatement();
        String consulta = "DELETE FROM usuarios WHERE nombre = '" + nombreUsuario + "'";
        int filasAfectadas = statement.executeUpdate(consulta);
        if (filasAfectadas > 0) {
            System.out.println("Usuario '" + nombreUsuario + "' eliminado correctamente.");
        } else {
            System.out.println("No se encontró un usuario con el nombre '" + nombreUsuario + "'.");
        }
    }
    private static void recuperarUltimoBorrado(Connection conexion) throws SQLException {
        Statement statement = conexion.createStatement();
        String consultaRecuperacion = "SELECT * FROM empresa.usuarios WHERE deleted_at IS NOT NULL ORDER BY deleted_at DESC LIMIT 1";
        ResultSet resultadoRecuperacion = statement.executeQuery(consultaRecuperacion);
        if (resultadoRecuperacion.next()) {
            int id = resultadoRecuperacion.getInt("id");
            String nombre = resultadoRecuperacion.getString("nombre");
            String email = resultadoRecuperacion.getString("email");
            String consultaActualizacion = "UPDATE empresa.usuarios SET deleted_at = NULL WHERE id = " + id;
            statement.executeUpdate(consultaActualizacion);
            System.out.println("Elemento recuperado exitosamente");
        } else {
            System.out.println("No hay elementos eliminados para recuperar.");
        }
    }
    private static void eliminarTodosLosDatos(Connection conexion)
            throws SQLException {Statement statement = conexion.createStatement();
        String consulta = "DELETE FROM usuarios";
        statement.execute(consulta);
        System.out.println("Todos los datos han sido eliminados.");
    }
}