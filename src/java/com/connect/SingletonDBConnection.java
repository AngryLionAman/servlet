/*
 * Copyright 2019 AngryLion.
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

/**
 *
 * @author AngryLion
 */
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author AngryLion
 */
public class SingletonDBConnection {

    private static volatile SingletonDBConnection singleInstance;
    private static DataSource dataSource;
    private static Connection dbConnect;

    private SingletonDBConnection() {
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

    /**
     *
     * @return
     */
    public static SingletonDBConnection getInstance() {
        SingletonDBConnection instanse = SingletonDBConnection.singleInstance;
        if (instanse == null) {
            synchronized (SingletonDBConnection.class) {
                instanse = SingletonDBConnection.singleInstance;
                if (instanse == null) {
                    SingletonDBConnection.singleInstance = instanse = new SingletonDBConnection();
                }
            }
        }

        return instanse;
    }

    /**
     *
     * @return
     */
    public static Connection getConnInst() {
        try {
            dbConnect = dataSource.getConnection();
        } catch (SQLException e1) {
            System.out.println(e1);
        }

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
