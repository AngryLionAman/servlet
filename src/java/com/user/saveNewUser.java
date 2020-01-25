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
package com.user;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class saveNewUser {

    /**
     *
     * @param fullName
     * @param emailOrMobile
     * @param passWord
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean saveUser(String fullName, String emailOrMobile, String passWord) throws SQLException, ClassNotFoundException {

        /*
        @ Need to create the username before re-start the new connection
        @ if you write this code in the meddle of the code then will get the execption error
        @ Exception Error :- No operations allowed after connection closed
         */
        SupportingFunction function = new SupportingFunction();
        String userName = function.CreateUsername(fullName.trim().replaceAll(" ", ""));

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "insert into newuser(firstname,username,user_type,email,password) values(?,?,?,?,?)";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, userName);
            ps.setString(3, "registered");
            ps.setString(4, emailOrMobile);
            ps.setString(5, passWord);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(saveNewUser.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }
}
