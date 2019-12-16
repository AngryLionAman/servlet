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

import com.string.validateInput;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AngryLion
 */
@WebServlet(name = "followTopicServlet", urlPatterns = {"/followTopicServlet"})
public class followTopicServlet extends HttpServlet {

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

        validateInput input = new validateInput();

        int topic_id = input.getInputInt(request.getParameter("topicId"));
        int followers_id = input.getInputInt(request.getParameter("userId"));
        String action = input.getInputString(request.getParameter("action"));

        String message = null;

        followTopicClassFile file = new followTopicClassFile();

        if (topic_id != 0 && followers_id != 0 && action != null) {
            try {
                if(!file.FollowTopic(topic_id, followers_id, action)){
                    message = "Action performed successfully";
                }else{
                    message = "Action not performaed";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(followTopicServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Not getting the valid input";
        }
        //System.out.println("com.topic.followTopicServlet.doPost()"+message);
    }
}
