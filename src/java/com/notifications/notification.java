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
package com.notifications;

import com.connect.DatabaseConnection;
import com.profile.profileDetailClassFile;
import com.string.validateInput;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
public class notification extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");

        displayNotification notification = new displayNotification();
        validateInput input = new validateInput();
        profileDetailClassFile file = new profileDetailClassFile();

        List<notificationPojo> notification1 = null;

        int userId = input.getInputInt(request.getParameter("id"));

        String message = null;

        if (userId != 0) {
            try {
                DatabaseConnection connection = new DatabaseConnection();
                try (Connection con = DatabaseConnection.makeConnection()){
                    if (file.IsUserPresent(con,userId)) {
                        notification1 = notification.notification(con,userId);
                    } else {
                        message = "The id you are looking for notification is not avaliable";
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(notification.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(notification.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(notification.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Bad argument, Please don't access this page directly. This page will work after login";
        }
        request.setAttribute("notification", notification1);
        request.setAttribute("message", message);
        request.getRequestDispatcher("inbox.jsp").forward(request, response);

    }
}
