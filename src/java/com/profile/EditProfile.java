/*
 * Copyright 2020 AngryLion.
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
package com.profile;

import com.connect.DatabaseConnection;
import com.string.validateInput;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "EditProfile", urlPatterns = {"/editP"})
public class EditProfile extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            validateInput input = new validateInput();
            EditUserProfileClassFile classFile = new EditUserProfileClassFile();

            String message = null;
            String path = "UpdateUserProfile.jsp";
            int id = 0;

            List<EditProfilePojoClass> profileDataById = null;
            DatabaseConnection connection = new DatabaseConnection();
            try (Connection con = DatabaseConnection.makeConnection()) {

                try {
                    id = input.getInputInt(request.getParameter("id"));
                } catch (Exception msg) {
                    Logger.getLogger(EditProfile.class.getName()).log(Level.SEVERE, null, msg);
                }

                if (id != 0) {
                    profileDataById = classFile.getProfileDataById(con, id);
                } else {
                    message = "Invalid profile url";
                    path = "Error404.jsp";
                }
            } catch (ClassNotFoundException | SQLException msg) {
                Logger.getLogger(EditProfile.class.getName()).log(Level.SEVERE, null, msg);
            } finally {
                request.setAttribute("message", message);
                request.setAttribute("profileDataById", profileDataById);
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
