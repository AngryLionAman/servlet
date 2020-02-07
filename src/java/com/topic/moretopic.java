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
package com.topic;

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
public class moretopic extends HttpServlet {

    private int getInput(String pageNo) {
        int pageno = 1;
        if (pageNo == null) {
            return 1;
        }
        if (pageNo.isEmpty()) {
            return 1;
        }
        if (!pageNo.isEmpty()) {
            pageno = Integer.parseInt(pageNo);
        }
        return pageno;
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

            allTopic topic = new allTopic();
            getTotalnumberOfColum colum = new getTotalnumberOfColum();
            validateInput input = new validateInput();

            int pageNo = input.getInputInt(request.getParameter("p"));

            if (pageNo < 1) {
                pageNo = 1;
            }

            int recordPerPage = 30;
            DatabaseConnection connection = new DatabaseConnection();
            try (Connection con = DatabaseConnection.makeConnection()) {
                List<allTopicPojo> list = topic.topic(con, pageNo, recordPerPage);
                int totalNumberOfpage = colum.totalNumberOfColumnOfTopic(con,recordPerPage);

                request.setAttribute("list", list);
                request.setAttribute("totalNumberOfpage", totalNumberOfpage);
                request.getRequestDispatcher("FollowMoreTopic.jsp").forward(request, response);

            } catch (Exception ex) {
                try {
                    throw ex;
                } catch (Exception ex1) {
                    Logger.getLogger(moretopic.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(moretopic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
