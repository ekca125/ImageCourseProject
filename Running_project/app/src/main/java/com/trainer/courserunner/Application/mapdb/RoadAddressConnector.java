package com.trainer.courserunner.Application.mapdb;

import java.sql.Connection;
import java.sql.SQLException;

public class RoadAddressConnector {
    private static String address = "trainer0528.database.windows.net";
    private static String port = "1443";
    private static String database = "DrawRunner";
    private static String username = "appguest"; //서버접속용 게스트 ID PW
    private static String password = "xD6oaEUn4AXa";
    //
    private static Connection connection = null;

    public static void startConnection() {
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection(username, password, database, address);
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
