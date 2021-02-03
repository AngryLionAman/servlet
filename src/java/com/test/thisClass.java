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
package com.test;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author AngryLion
 */
public class thisClass {
    
    public static void main(String[] agrs) throws SQLException, ClassNotFoundException {
        
        DatabaseConnection connection = new DatabaseConnection();
        
        try (Connection con = DatabaseConnection.makeConnection()) {
            
            if (con != null) {
                System.out.println("Connection is not null 1 :- " + con);
                con.close();
            }
            
            if (con != null) {
                System.out.println("Connection is not null 2 :- " + con);
            }
            
            if (!con.isClosed()) {
                System.out.println("Connection is not Closed :- " + con);
            }
            
        } catch (Exception msg) {
            System.out.println("Exception occred :- " + msg);
        } finally {
            System.err.print("this is finaly block");
        }
        
    }
}
