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

    private static DatabaseConnection instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    private String username = "root";
    private String password = null;

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public DatabaseConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Throws classNotFoundException
            this.connection = DriverManager.getConnection(url, username, password); //Throws SQLException
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static synchronized DatabaseConnection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
