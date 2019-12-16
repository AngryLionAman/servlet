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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 *
 * @author AngryLion
 */
public class SecondPoolConnection {
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_DB_URL = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    //private static final String JDBC_DB_URL = "jdbc:mysql://mysql3000.mochahost.com/cse13316_bharat?useUnicode=true&characterEncoding=utf-8";

    // JDBC Database Credentials
    private static final String JDBC_USER = "root";
    //private static final String JDBC_USER = "cse13316_bharat";
    private static final String JDBC_PASS = null;
    //private static final String JDBC_PASS = "c?GmSPOGpvcX";

    private static GenericObjectPool gPool = null;

    /**
     *
     * @return @throws Exception
     */
    @SuppressWarnings("unused")
    public DataSource setUpPool() throws Exception {
        
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException msg) {
            Logger.getLogger(PoolConnection.class.getName()).log(Level.SEVERE, null, msg);
        }

        // Creates an Instance of GenericObjectPool That Holds Our Pool of Connections Object!
        gPool = new GenericObjectPool();
        gPool.setMaxActive(100);
        gPool.setMaxWait(10000);
        gPool.setMinIdle(10);
        gPool.setMaxIdle(100);

        // Creates a ConnectionFactory Object Which Will Be Use by the Pool to Create the Connection Object!
        ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);

        // Creates a PoolableConnectionFactory That Will Wraps the Connection Object Created by the ConnectionFactory to Add Object Pooling Functionality!
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
        
        return new PoolingDataSource(gPool);
    }

    /**
     *
     * @return
     */
    public GenericObjectPool getConnectionPool() {
        return gPool;
    }

    // This Method Is Used To Print The Connection Pool Status
    /**
     *
     */
    public void printDbStatus() {
        System.out.println("\nMax.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
        //return ("Max.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
    }
}
