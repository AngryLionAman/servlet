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
package com.search;

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
@WebServlet(name = "saveSearchedQuaryServlet", urlPatterns = {"/saveSearchedQuaryServlet"})
public class saveSearchedQuaryServlet extends HttpServlet {

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

        String query = input.getInputString(request.getParameter("query"));
        int userId = input.getInputInt(request.getParameter("id"));

        String message = null;

        saveSearchedQueryClassFile file = new saveSearchedQueryClassFile();
        if (query != null) {
            try{
                DatabaseConnection  connection = new DatabaseConnection();
                try(Connection con = DatabaseConnection.makeConnection()) {
                    if (!file.SaveSearchedQuery(con,query, userId)) {
                        message = "Queary has been saved";
                    } else {
                        message = "Queary not saved";
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(saveSearchedQuaryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(saveSearchedQuaryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Queary can't be empty";
        }
        //System.out.println("com.search.saveSearchedQuaryServlet.doPost()" + message);
    }
}
