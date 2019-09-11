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
package com.question;

import com.answer.time;
import com.connect.DatabaseConnection;
import com.index.indexPage;
import com.index.indexPageExtraFunction;
import com.index.recentQuestionPojo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class getAllQuestion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        List<recentQuestionPojo> list = new ArrayList<>();
        DatabaseConnection dc = null;
        try {
            dc = DatabaseConnection.getInstance();
        } catch (SQLException ex) {
            Logger.getLogger(getAllQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        indexPageExtraFunction function = new indexPageExtraFunction();
        indexPage index = new indexPage();
        time time = new time();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int pageNo = getInput(request.getParameter("p"));
        int recordPerPage = 20;
        float totalNumberOfpage = 0;
        int startPage = (pageNo * recordPerPage) - recordPerPage;

        try {
            String sql = "select q.q_id,q.question,q.vote,q.total_view,q.posted_time,q.updated_time as date,"
                    + "user.id,user.firstname,user.username,user.user_type,user.higher_edu from question q inner join newuser user "
                    + "on user.id = q.id ORDER BY q.q_id limit ?,?";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, startPage);
            ps.setInt(2, recordPerPage);
            rs = ps.executeQuery();
            while (rs.next()) {
                int totalView = rs.getInt("q.total_view");
                String date = rs.getString("date");
                int questionId = rs.getInt("q.q_id");
                int days = time.showTime(questionId);
                String question = rs.getString("q.question");
                int vote = rs.getInt("q.vote");
                String fullName = rs.getString("user.firstname");
                String userName = rs.getString("user.username");
                String userType = rs.getString("user.user_type");
                String higherEdu = rs.getString("user.higher_edu");
                int userId = rs.getInt("user.id");
                int totalAnswer = index.totalAnswer(questionId);
                function.updateQuestionView(questionId);
                recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, days, questionId, question, vote, fullName, userName, userType, higherEdu, userId, totalAnswer);
                list.add(recentQuestionPojo);
            }
            sql = "select count(*) as cnt from question";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalNumberOfpage = rs.getInt("cnt");
            }
            /*
            Though not as convenient, it is also possible to find out how many rows a nonscrollable result set has. The following example shows one way to determine the number of rows.

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM EMPLOYEES");
            rs.next();
                int count = rs.getInt(1);
            System.out.println("XYZ, Inc. has " + count + " employees");

                    ResultSet rs2 = stmt.executeQuery(	"SELECT LAST_NAME, FIRST_NAME FROM EMPLOYEES");
                        while (rs2.next()) {
	. . . // retrieve first and last names of each employee
}           
             */
            totalNumberOfpage = totalNumberOfpage / recordPerPage;
            int newnumber = (int) totalNumberOfpage;
            if (totalNumberOfpage > newnumber) {
                totalNumberOfpage = totalNumberOfpage + 1;
            }
        } catch (SQLException msg) {
            try {
                throw msg;
            } catch (SQLException ex) {
                Logger.getLogger(getAllQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(getAllQuestion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
            request.setAttribute("list", list);
            request.setAttribute("tab", "all");
            request.setAttribute("totalNumberOfpage", (int) totalNumberOfpage);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

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
}
