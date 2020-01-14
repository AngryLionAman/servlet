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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author AngryLion
 */
public class JNDI_ConnectionPool {

    private static DataSource dataSource;
    private static Connection dbConnect;

    /**
     *
     * @throws NamingException
     */
    public JNDI_ConnectionPool() throws NamingException {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            dataSource = (DataSource) envCtx.lookup("jdbc/mydatabase");
            try {
                dbConnect = dataSource.getConnection();
                System.out.println("com.connect.JNDI_ConnectionPool.<init>()" + dbConnect);
            } catch (SQLException msg) {
                Logger.getLogger(JNDI_ConnectionPool.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (NamingException msg) {
            Logger.getLogger(JNDI_ConnectionPool.class.getName()).log(Level.SEVERE, null, msg);
        }
    }

    /**
     *
     * @return
     */
    public static Connection connect() {

        if (dbConnect == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/mydatabase");

                try {
                    dbConnect = dataSource.getConnection();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            } catch (NamingException e) {
                System.out.println(e);
            }
        }

        return dbConnect;
    }

}
