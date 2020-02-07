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
package com.optionalQuestion;

import com.connect.DatabaseConnection;
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
public class displayOptionalQuestion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        validateInput input = new validateInput();
        DisplayOptionalQuestionClassFile file = new DisplayOptionalQuestionClassFile();
        supportingFunction function = new supportingFunction();

        List<optionalQuestionPojo> optionalQuestionByLimit = null;
        List<String> onTopicName = null;
        List<Integer> totalNumberOfOption = null;

        int totalNoOfPage = 0;

        DatabaseConnection connection = new DatabaseConnection(); 
        try (Connection con = DatabaseConnection.makeConnection()) {
            final int recordPerPage = 30;

            int pageNo = 0;
            try {
                pageNo = input.getInputInt(request.getParameter("p"));
            } catch (Exception msg) {
                Logger.getLogger(displayOptionalQuestion.class.getName()).log(Level.SEVERE, null, msg);
            }

            int numOfoption = 0;
            try {
                numOfoption = input.getInputInt(request.getParameter("option"));
            } catch (Exception msg) {
                Logger.getLogger(displayOptionalQuestion.class.getName()).log(Level.SEVERE, null, msg);
            }

            String basedOn = null;
            try {
                basedOn = input.getInputString(request.getParameter("onTopic"));
            } catch (Exception msg) {
                Logger.getLogger(displayOptionalQuestion.class.getName()).log(Level.SEVERE, null, msg);
            }

            try {
                onTopicName = function.onTopicName(con);
            } catch (ClassNotFoundException | SQLException msg) {
                Logger.getLogger(displayOptionalQuestion.class.getName()).log(Level.SEVERE, null, msg);
            }

            try {
                totalNumberOfOption = function.totalNumberOfOption(con);
            } catch (ClassNotFoundException | SQLException msg) {
                Logger.getLogger(displayOptionalQuestion.class.getName()).log(Level.SEVERE, null, msg);
            }

            if (numOfoption != 0) {
                optionalQuestionByLimit = file.getOptionalQuestionByNoOfPage(con,numOfoption, pageNo, recordPerPage);
                totalNoOfPage = file.getTotalNoOfPageByNoOfPage(con,recordPerPage, numOfoption);
            } else if (basedOn != null && !"all".equalsIgnoreCase(basedOn)) {
                optionalQuestionByLimit = file.getOptionalQuestionByBasedOn(con,basedOn, pageNo, recordPerPage);
                totalNoOfPage = file.getTotalNoOfPageByBasedOn(con,recordPerPage, basedOn);
            } else {
                optionalQuestionByLimit = file.getOptionalQuestionByLimit(con,pageNo, recordPerPage);
                totalNoOfPage = file.getTotalNoOfPage(con,recordPerPage);
            }

        } catch (ClassNotFoundException | SQLException msg) {
            Logger.getLogger(displayOptionalQuestion.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("totalNumberOfOption", totalNumberOfOption);
            request.setAttribute("onTopicName", onTopicName);
            request.setAttribute("list", optionalQuestionByLimit);
            request.setAttribute("totalNumberOfpage", (int) totalNoOfPage);
            request.getRequestDispatcher("optionalq.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(displayOptionalQuestion.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(displayOptionalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
