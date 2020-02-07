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

    // private static Database instance;
    public static Connection connection;
    private final String url = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    private String username = "root";
    private String password = null;

    public DatabaseConnection() throws SQLException, ClassNotFoundException {
        System.out.println("com.connect.DatabaseConnection.<init>()" + "Constructor execuated");
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Throws classNotFoundException
            connection = DriverManager.getConnection(url, username, password); //Throws SQLException
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static Connection makeConnection() throws SQLException {
        return connection;
    }
}
