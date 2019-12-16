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
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author AngryLion
 */
public class sitemap {

    /**
     *
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss+00:00").format(new Date()));
            String dateAndTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "T" + new SimpleDateFormat("HH:mm:ss+00:00").format(new Date());

            con = dc.getConnection();
            String sql = "select q_id,question from question order by rand()";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String question = rs.getString("question").replaceAll(" ", "-");
                int questionId = rs.getInt("q_id");
                System.out.println("\n<url><loc>https://www.inquiryhere.com/Answer.jsp?Id=" + questionId + "&amp;q=" + question + "</loc><lastmod>" + dateAndTime + "</lastmod><priority>0.80</priority></url>");
            }
        } catch (SQLException msg) {
            System.out.println(msg);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
