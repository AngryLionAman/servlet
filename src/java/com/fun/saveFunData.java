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
package com.fun;

import com.string.validateInput;
import java.io.IOException;
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
public class saveFunData extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        validateInput input = new validateInput();
        SaveFunDataClassFile file = new SaveFunDataClassFile();
        String message = "Data not save, Please report to admin at contact us form";

        try {
            int userId = input.getInputInt(request.getParameter("userId"));
            String category = input.getInputString(request.getParameter("category").equalsIgnoreCase("others") ? request.getParameter("category1") : request.getParameter("category"));
            String type = input.getInputString(request.getParameter("type").equalsIgnoreCase("others") ? request.getParameter("type1") : request.getParameter("type"));
            String basedon = input.getInputString(request.getParameter("basedon").equalsIgnoreCase("others") ? request.getParameter("basedon1") : request.getParameter("basedon"));
            String title = input.getInputString(request.getParameter("title"));
            String description = input.getInputString(request.getParameter("description"));

            
            if (description != null) {
                if (!file.saveFunData(userId, title, description, category, basedon, type)) {
                    message = "Data has been saved successfully";
                } else {
                    message = "Data not saved, Please try again";
                }
            } else {
                message = "Description can't be empty";
            }
        } catch (Exception msg) {
            Logger.getLogger(saveFunData.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("fun").forward(request, response);
        }
    }
}
