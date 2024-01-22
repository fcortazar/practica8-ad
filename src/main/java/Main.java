import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            // Realiza operaciones con la base de datos utilizando la conexión.
            // Por ejemplo, ejecuta consultas SQL, actualiza registros, etc.
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores, por ejemplo, registrar o mostrar un mensaje de error.
        }
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();

            // Leer la elección del usuario
            System.out.print("Ingrese la opción deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            // Procesar la elección del usuario
            switch (opcion) {
                case 1:
                    agregarUsuario();
                    break;
                case 2:
                    modificarUsuario();
                    break;
                case 3:
                    eliminarUsuario();
                    break;
                case 4:
                    eliminarTodosLosDatos();
                    break;
                case 5:
                    mostrarTodosLosRegistros();
                    break;
                case 6:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Agregar Usuario");
        System.out.println("2. Modificar Usuario");
        System.out.println("3. Eliminar Usuario");
        System.out.println("4. Eliminar Todos los Datos");
        System.out.println("5. Mostrar Todos los Registros");
        System.out.println("6. Salir");
    }

    private static void agregarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Agregar Usuario ---");
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del usuario: ");
        int edad = scanner.nextInt();

        UsuarioDAO.agregarUsuario(nombre, edad);
        System.out.println("Usuario agregado exitosamente.");
    }

    private static void modificarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Modificar Usuario ---");
        System.out.print("Ingrese el ID del usuario a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        System.out.print("Ingrese el nuevo nombre del usuario: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Ingrese la nueva edad del usuario: ");
        int nuevaEdad = scanner.nextInt();

        UsuarioDAO.modificarUsuario(id, nuevoNombre, nuevaEdad);
        System.out.println("Usuario modificado exitosamente.");
    }

    private static void eliminarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Eliminar Usuario ---");
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id = scanner.nextInt();

        UsuarioDAO.eliminarUsuario(id);
        System.out.println("Usuario eliminado exitosamente.");
    }
    private static void eliminarTodosLosDatos() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Eliminar Todos los Datos ---");
        System.out.print("¿Está seguro de que desea eliminar todos los datos? (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();

        if (respuesta.equals("S")) {
            // Lógica para eliminar todos los datos
            // Puedes implementar esta lógica en UsuarioDAO o en una clase separada según tus preferencias.
            // Aquí, simplemente mostramos un mensaje de ejemplo.
            UsuarioDAO.eliminarTodosLosDatos();
            System.out.println("Todos los datos han sido eliminados.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    private static void mostrarTodosLosRegistros() {
        // Lógica para mostrar todos los registros
        // Puedes implementar esta lógica en UsuarioDAO o en una clase separada según tus preferencias.
        // Aquí, simplemente mostramos un mensaje de ejemplo.
        UsuarioDAO.mostrarTodosLosRegistros();
    }
}
