package by.javaguru.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static Connection connection;

    static {
        loadDriver();
    }

    private ConnectionManager() {
    }

    public static Connection open() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        PropertiesUtil.getProperty(URL_KEY),
                        PropertiesUtil.getProperty(USER_KEY),
                        PropertiesUtil.getProperty(PASSWORD_KEY));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            System.out.println("registring class");
            Class.forName("org.postgresql.Driver");
            System.out.println("registred class");
        } catch (ClassNotFoundException e) {
            System.out.println("exception");
            throw new RuntimeException(e);
        }
    }
}