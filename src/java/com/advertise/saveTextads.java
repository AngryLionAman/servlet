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
package com.advertise;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author AngryLion
 */
public class saveTextads {

    /**
     *
     * @param promoterName
     * @param title
     * @param desc
     * @param link
     * @param days
     * @return
     * @throws SQLException
     */
    public boolean textAds(String promoterName, String title, String desc, String link, int days) throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        boolean execute = false;
        try {
            con = dc.getConnection();
            String sql = "insert into textads(title,description,link,promoted_by,days)values(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setString(3, link);
            ps.setString(4, promoterName);
            ps.setInt(5, days);
            execute = ps.execute();
        } catch (SQLException msg) {
            throw msg;
        } finally {
            
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException err) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException err) {

                }
            }

        }
        return execute;
    }
}
