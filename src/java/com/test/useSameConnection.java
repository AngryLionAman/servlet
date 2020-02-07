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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author AngryLion
 */
public class useSameConnection {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnection connection = new DatabaseConnection();
        System.out.println("Called connection class constructor");
        useSameConnection obj = new useSameConnection();
        System.out.println("Class called");
        try (Connection con = DatabaseConnection.makeConnection()) {
            System.out.println("Connection called");
            obj.userName(con);
            System.out.println("UserName called");
            obj.userFullName(con);
            System.out.println("Full Name caled");
        }
    }

    private void userName(Connection con) throws SQLException {
        String sql = "select id from newuser limit 5;";

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
            }
        }
    }

    private void userFullName(Connection con) throws SQLException {
        String sql = "select firstname from newuser limit 5;";

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getString("firstname"));
            }
        }
    }
}
