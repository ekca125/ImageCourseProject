package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector implements AutoCloseable {
    //info
    private static final String url = "jdbc:mysql://192.168.219.128/map_schema";
    private static final String user = "dbuser";
    private static final String password = "dbdgmk3l3ka";
    private Connection connection;

    public DBConnector() {
        connection = null;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }
}
