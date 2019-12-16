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
package com.help;

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
public class help extends HttpServlet {

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
        
        String fullName = input.getInputString(request.getParameter("fullName"));
        String Email = input.getInputString(request.getParameter("Email"));
        String query = input.getInputString(request.getParameter("query"));
        
        String message = null;
        
        helpClassFile file = new helpClassFile();
        
        if(fullName != null && Email != null && query != null){
            try {
                if(!file.SaveHelp(fullName, Email, query)){
                    message = "Your queary has been posted successfully, Our team will get back to you";
                }else{
                    message = "Query not saved, Please try again";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(help.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            message = "Bad argument plase try again";
        }
        
        request.setAttribute("message", message);
        request.getRequestDispatcher("help.jsp").forward(request, response);
    }

}
