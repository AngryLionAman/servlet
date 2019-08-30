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
package com.topic;

import com.connect.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AngryLion
 */
public class deleteTopic extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        DatabaseConnection dc = null;
        try {
            dc = new DatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(deleteTopic.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            con = dc.getConnection();
            /**
             * ***************
             * deleting the question tag form question_tag_table
             * *************************
             */
            String sql1 = "delete from question_topic_tag where tag_id = ?";
            ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, topicId);
            ps1.execute();
            /*******
             Deleting the followers detail
             ***********************/
            String sql2 = "delete from topic_followers_detail where topic_id = ?";
            ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, topicId);
            ps2.execute();
            /**
             * ************
             * delteting topic from topic table ************
             */

            String sql = "delete from topic where unique_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.execute();

            response.sendRedirect("Admin/topic.jsp?msg=Topic has been deleted");
        } catch (SQLException msg) {
            try {
                throw msg;
            } catch (SQLException ex) {
                Logger.getLogger(deleteTopic.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {

                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException msg) {

                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
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
    }
}
