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
package com.test;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author AngryLion
 */
public class testFinalize {

    private Object con;
    testFinalize(){
       Connection con; 
    }
    @Override
    public void finalize() throws Throwable{
        try {
            this.con = null;
        } finally {
            super.finalize();
        }
    }
    public static void main(String[] args) throws SQLException{
        DatabaseConnection dc = DatabaseConnection.getInstance();
        
    }
}
