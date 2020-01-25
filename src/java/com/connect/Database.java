/*
 * Copyright 2020 AngryLion.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
public class Database {

    private static Database instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    private String username = "root";
    private String password = null;

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Database() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Throws classNotFoundException
            connection = DriverManager.getConnection(url, username, password); //Throws SQLException
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
    public static synchronized Database getInstance() throws SQLException, ClassNotFoundException {
        if (null == instance || instance.getConnection().isClosed()) {
            instance = new Database();
        }
        return instance;
    }
}