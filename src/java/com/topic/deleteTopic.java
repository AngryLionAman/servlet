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

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));

        try (Connection con = DatabaseConnection.makeConnection()) {
            /**
             * ***************
             * deleting the question tag form question_tag_table
             * *************************
             */

            try (PreparedStatement ps = con.prepareStatement("delete from question_topic_tag where tag_id = ?")) {
                ps.setInt(1, topicId);
                ps.execute();
            }

            /**
             * *****
             * Deleting the followers detail *********************
             */
            try (PreparedStatement ps = con.prepareStatement("delete from topic_followers_detail where topic_id = ?")) {
                ps.setInt(1, topicId);
                ps.execute();
            }
            /**
             * ************
             * delteting topic from topic table ************
             */

            try (PreparedStatement ps = con.prepareStatement("delete from topic where unique_id = ?")) {
                ps.setInt(1, topicId);
                ps.execute();
            }
            
            request.getRequestDispatcher("Admin/topic.jsp").forward(request, response);
            
        } catch (SQLException msg) {
            Logger.getLogger(deleteTopic.class.getName()).log(Level.SEVERE, null, msg);
        }
    }
}
