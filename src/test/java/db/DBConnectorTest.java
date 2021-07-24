package db;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DBConnectorTest {

    @Test
    void getConnection() {
        DBConnector dbConnector = new DBConnector();
        try {
            assertTrue(dbConnector.getConnection() != null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}