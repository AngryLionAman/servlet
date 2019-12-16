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
package com.comments;

import com.string.validateInput;
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
public class saveProfileComment extends HttpServlet {

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

        String comments = input.getInputString(request.getParameter("comments"));

        int session_userid = input.getInputInt(request.getParameter("session_userid"));

        int OnCommentUserId = input.getInputInt(request.getParameter("OnCommentUserId"));

        String message = null;

        onProfileCommentClass commentClass = new onProfileCommentClass();

        if (comments != null && session_userid != 0 && OnCommentUserId != 0) {
            try {
                if (!commentClass.SaveCommentOfProfie(session_userid, OnCommentUserId, comments)) {
                    if (!commentClass.CreateNotificationOfProfileComment(session_userid, OnCommentUserId)) {
                        //Do nothing
                    } else {
                        message = "Comment has been save but notification not created";
                    }
                } else {
                    message = "Comment not saved, Please try again";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(saveProfileComment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Got Some error, Please try again";
        }
        request.setAttribute("message", message);
        request.setAttribute("id", OnCommentUserId);
        request.getRequestDispatcher("profile").forward(request, response);
    }
}
