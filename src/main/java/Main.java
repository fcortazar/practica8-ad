import java.io.InputStream;
import java.util.Properties;
public class Main {
    public static void main(String[] args) {
        Properties propiedades = new Properties();
        try (InputStream entrada = Main.class.getClassLoader().getResourceAsStream("file.properties")) {
            propiedades.load(entrada);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Obtener valores
        String nombreApp = propiedades.getProperty("nombre_app");
        String version = propiedades.getProperty("version");
        boolean modoDebug = Boolean.parseBoolean(propiedades.getProperty("modo_debug"));

        System.out.println("Nombre de la aplicación: " + nombreApp);
        System.out.println("Versión: " + version);
        System.out.println("Modo debug: " + modoDebug);
    }
}
