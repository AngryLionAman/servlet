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
import java.io.PrintWriter;
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
public class unanswered extends HttpServlet {

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

        try {
            String sql = "select q.q_id,q.question,q.vote,q.total_view,q.posted_time,q.updated_time as date,"
                    + "user.id,user.firstname,user.username,user.user_type,user.higher_edu,(SELECT COUNT(a_id)as cnt FROM answer WHERE q_id = q.q_id group by q_id)as cnt from question q inner join newuser user "
                    + "on user.id = q.id having cnt is null ORDER BY q.q_id";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
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
            request.setAttribute("tab", "unanswered");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
