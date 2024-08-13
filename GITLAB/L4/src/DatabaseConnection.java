import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String LOCAL_DB_URL = "jdbc:mysql://localhost:3306/local_lab4";
    private static final String REMOTE_DB_URL = "jdbc:mysql://34.31.10.87:3306/remote_lab4";
    private static final String LOCAL_DB_USERNAME = "root";
    private static final String LOCAL_DB_PASSWORD = "Jems@1205";
    private static final String REMOTE_DB_USERNAME = "root";
    private static final String REMOTE_DB_PASSWORD = "Bholu@1205";

    public Connection connectToRemoteDatabase() throws SQLException {
        Connection remoteDbConnection = DriverManager.getConnection(REMOTE_DB_URL, REMOTE_DB_USERNAME, REMOTE_DB_PASSWORD);
        return remoteDbConnection;
    }

    public Connection connectToLocalDatabase() throws SQLException {
        Connection localDbConnection = DriverManager.getConnection(LOCAL_DB_URL, LOCAL_DB_USERNAME, LOCAL_DB_PASSWORD);
        return localDbConnection;
    }
}
