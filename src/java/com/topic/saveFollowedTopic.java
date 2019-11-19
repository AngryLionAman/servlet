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

import java.io.IOException;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userid = request.getParameter("userid");
        String[] topicName = request.getParameterValues("MultipleSelectedTopic");

        saveTopic topic = new saveTopic();
        try {
            topic.SaveTopicByTopicIdAndUserId(userid, topicName);
            response.sendRedirect("index.jsp?ref=f_t");
            //request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(saveFollowedTopic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
