package com.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aman Kumar
 */
public class DatabaseConnection {

    String jdbcUrl = "";
    //String jdbcUrl = "jdbc:mysql://localhost/bharat?user=root&password=&useUnicode=true&characterEncoding=utf-8";

    //private static final String URL = "";
    //private static final String USERNAME = "";
    //private static final String PASSWORD = "";
    //private static final String URL = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    //private static final String USERNAME = "root";
    //private static final String PASSWORD = "";
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(jdbcUrl);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
