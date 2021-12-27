package db;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBConnectorTest {

    @Test
    void getConnection() {
        DBConnector dbConnector = new DBConnector();
        try {
            assertNotNull(dbConnector.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}