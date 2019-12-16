/*
 * Copyright 2019 inquiryhere.com.
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
package com.admin;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inquiryhere.com
 */
public class adminUserDetail {

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<adminUserPojo> userDetail(int userid) throws SQLException, ClassNotFoundException {
        List<adminUserPojo> list = new ArrayList<>();
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String v = "select id,username,firstname,email from newuser where id = ?";
            connection = con.getConnection();
            preparedStatement = connection.prepareStatement(v);
            preparedStatement.setInt(1, userid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String fullName = resultSet.getString("firstname");
                String eMail = resultSet.getString("email");
                list.add(new adminUserPojo(userId, userName, fullName, eMail));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException msg) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException msg) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException msg) {
                }
            }

        }
        return list;
    }
}
