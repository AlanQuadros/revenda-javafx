package revenda.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/revenda?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "12345678";

    public Connection connection;

    public Database() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
