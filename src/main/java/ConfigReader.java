import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final String PROPERTIES_FILE = "F:\\Repositorios GitHub\\DI\\practica8-ad\\src\\main\\resources\\file.properties";
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores, por ejemplo, lanzar una excepción o proporcionar valores predeterminados.
        }
    }

    public String getDatabaseURL() {
        return properties.getProperty("db.url");
    }

    public String getDatabaseUsername() {
        return properties.getProperty("db.username");
    }

    public String getDatabasePassword() {
        return properties.getProperty("db.password");
    }
}
