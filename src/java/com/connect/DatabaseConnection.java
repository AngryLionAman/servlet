package com.connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Aman Kumar
 */
public class DatabaseConnection {

    private static DataSource dataSource;
    private static Connection dbConnect;

    public DatabaseConnection() throws SQLException, ClassNotFoundException {
        System.out.println("\nConstructor is initilizing....");
        ConnectionCode();
        System.out.println("Constructor is initilized");
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static Connection makeConnection() throws SQLException {
        System.out.println("Create connection is processing.. ");
        if (dbConnect == null || dbConnect.isClosed()) {
            ConnectionCode();
            System.out.println("connection is null\n ");
        } else {
            System.out.println("connection is not null\n ");
        }
        return dbConnect;
    }

    private static void ConnectionCode() {

        try {
            Context initCtx = new InitialContext();
            System.out.println("initCtx obj created");
            //Context envCtx = (Context) initCtx.lookup("java:comp/env");
            dataSource = (DataSource) initCtx.lookup("java:comp/env/jdbc/mydatabase");
            System.out.println("dataSource valuse has been asigned");

            try {
                dbConnect = dataSource.getConnection();
                System.out.println("dbConnect has been created");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NamingException msg) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, msg);
        }
    }
}
