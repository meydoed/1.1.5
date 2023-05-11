package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String username = "root";
    private static final String password = "root";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/mydatabase";

    static public Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectionUrl, username, password);
            connection.setAutoCommit(false);
            if (!connection.isClosed()) {
                System.out.println("Database connected!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failure..");
        }
        return connection;
    }
}