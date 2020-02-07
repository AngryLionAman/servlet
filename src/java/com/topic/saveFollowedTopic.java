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
public class saveFollowedTopic extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            
            saveTopic topic = new saveTopic();
            
            DatabaseConnection connection = new DatabaseConnection();
            try (Connection con = DatabaseConnection.makeConnection()) {
                String userid = request.getParameter("userid");
                String[] topicName = request.getParameterValues("MultipleSelectedTopic");
                topic.SaveTopicByTopicIdAndUserId(con, userid, topicName);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(saveFollowedTopic.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                request.getRequestDispatcher("index").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(saveFollowedTopic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
