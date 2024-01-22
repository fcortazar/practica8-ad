import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection getConnection() throws SQLException {
        ConfigReader configReader = new ConfigReader();
        String url = configReader.getDatabaseURL();
        String username = configReader.getDatabaseUsername();
        String password = configReader.getDatabasePassword();

        return DriverManager.getConnection(url, username, password);
    }
}
