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
package com.blog;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author AngryLion
 */
public class saveBlogClass {

    public boolean saveBlog(String sub, String desc, int id) throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false; 
        try {
            con = dc.getConnection();
            String sql = "insert into blog(subject,`desc`,posted_by) values(?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, sub);
            ps.setString(2, desc);
            ps.setInt(3, id);
            result = ps.execute();
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {

                }
            }
        }
        return result;
    }
}
