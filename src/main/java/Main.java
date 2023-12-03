import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static Connection connection;

    public static void main(String[] args) {
        cargarConfiguracion();

        try {
            Class.forName(Configuracion.getProperty("db.driver"));
            connection = DriverManager.getConnection(
                    Configuracion.getProperty("db.url"),
                    Configuracion.getProperty("db.usuario"),
                    Configuracion.getProperty("db.clave")
            );

            while (true) {
                mostrarMenu();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cargarConfiguracion() {
        Properties propiedades = new Properties();
        try (FileReader reader = new FileReader("src/main/resources/file.properties")) {
            propiedades.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Configuracion.setProperties(propiedades);
    }

    // Resto del código...
}
