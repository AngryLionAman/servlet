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
package com.mail;

import com.connect.DatabaseConnection;
import com.string.validateInput;
import java.io.IOException;
import java.sql.Connection;
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
@WebServlet(name = "hideMail", urlPatterns = {"/hideMail"})
public class hideMail extends HttpServlet {

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

        int userId = input.getInputInt(request.getParameter("sessionUserId"));
        String message = null;

        try {

            String action = input.getInputString(request.getParameter("action"));
            hideMailClassFile hide_Mail = new hideMailClassFile();

            DatabaseConnection connection = new DatabaseConnection();
            if (userId != 0 && action != null) {
                try (Connection con = DatabaseConnection.makeConnection()) {

                    if (!hide_Mail.HideMail(con, userId, action)) {
                        message = "Mail " + action + " successfully";
                    } else {
                        message = "Action not performed, Please try again";
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(hideMail.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                message = "Bad argument, Got some problem please try again";
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(hideMail.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.setAttribute("message", message);
            request.setAttribute("id", userId);
            request.getRequestDispatcher("profile").forward(request, response);
        }
    }

}
