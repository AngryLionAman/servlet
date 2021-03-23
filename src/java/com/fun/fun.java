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
public class fun extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        validateInput input = new validateInput();
        FunClassFile file = new FunClassFile();
        FunHelpingFunction function = new FunHelpingFunction();

        int pageNo = input.getInputInt(request.getParameter("p"));
        String category = input.getInputString(request.getParameter("category"));
        String type = input.getInputString(request.getParameter("type"));
        String basedOn = input.getInputString(request.getParameter("basedOn"));

        int recordPerPage = 30;

        List<funPojo> funData = null;
        int totalNumberOfpage = 0;

        List<String> funBasedOn = null;
        List<String> funCategory = null;
        List<String> funType = null;

        String message = null;
        String gotException = null;

        if (request.getAttribute("message") != null) {
            message = (String) request.getAttribute("message");
        }

        try {
            if (category != null) {

                funData = file.getFunDataByCategory(category);

            } else if (type != null) {

                funData = file.getFunDataByType(type);

            } else if (basedOn != null) {

                funData = file.getFunDataByBasedOn(basedOn);

            } else {

                funData = file.getRandomFunData(pageNo, recordPerPage);
                totalNumberOfpage = file.totalNumberOfpage(recordPerPage);

            }

            funBasedOn = function.getFunBasedOn();
            funCategory = function.getFunCategory();
            funType = function.getFunType();

        } catch (Exception msg) {
            gotException = "Not null";
            Logger.getLogger(fun.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("gotException", gotException);

            request.setAttribute("funDataByCategory", funData);
            request.setAttribute("totalNumberOfpage", totalNumberOfpage);

            request.setAttribute("funBasedOn", funBasedOn);
            request.setAttribute("funCategory", funCategory);
            request.setAttribute("funType", funType);

            request.setAttribute("message", message);

            request.getRequestDispatcher("fun.jsp").forward(request, response);
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(fun.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(fun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
