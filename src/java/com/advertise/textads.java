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
package com.advertise;

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
public class textads extends HttpServlet {

    private static String getInputString(String parameter) {
        String val;
        if (parameter.isEmpty()) {
            val = null;
        } else {
            val = parameter.trim();
        }
        return val;
    }

    private static int getintInput(String option) {
        int id = 0;
        if (option == null) {
            return 0;
        }
        if (option.isEmpty()) {
            return 0;
        }
        if (!option.isEmpty()) {
            id = Integer.parseInt(option);
        }
        return id;
    }

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

        saveTextads textads = new saveTextads();

        String promoterName = getInputString(request.getParameter("promoter_name"));
        String title = getInputString(request.getParameter("title"));
        String link = getInputString(request.getParameter("link"));
        String desc = getInputString(request.getParameter("description"));
        int days = getintInput(request.getParameter("display_ads_days"));
        if (promoterName != null && title != null && link != null && desc != null && days != 0) {
            try {
                boolean textAds = textads.textAds(promoterName, title, desc, link, days);
                if (!textAds) {
                    request.setAttribute("message", "Data has been saved");
                    request.getRequestDispatcher("Admin/textads.jsp").forward(request, response);
                }else{
                   request.setAttribute("message", "Got the true value ");
                    request.getRequestDispatcher("Admin/textads.jsp").forward(request, response); 
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(textads.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("message", "Some thing is null");
            request.getRequestDispatcher("Admin/textads.jsp").forward(request, response);
        }
    }
}
