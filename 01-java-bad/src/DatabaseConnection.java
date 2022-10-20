import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Source: https://www.geeksforgeeks.org/java-servlet-and-jdbc-example-insert-data-in-mysql/
// This class can be used to initialize the database connection
public class DatabaseConnection {
    protected static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        // Database name to access
        String dbName = "example";
        String dbUsername = "example";
        String dbPassword = "example";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName,
                dbUsername,
                dbPassword);
        return con;
    }
}