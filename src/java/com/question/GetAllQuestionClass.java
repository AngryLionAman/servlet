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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class GetAllQuestionClass {

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int TotalNoOfPage() throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int totalNumberOfpage = 0;
        int recordPerPage = 20;
        try {

            con = ds.getConnection();
            String sql = "select count(*) as cnt from question";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalNumberOfpage = rs.getInt("cnt");
            }
            totalNumberOfpage = totalNumberOfpage / recordPerPage;
            int newnumber = (int) totalNumberOfpage;
            if (totalNumberOfpage > newnumber) {
                totalNumberOfpage = totalNumberOfpage + 1;
            }
            return totalNumberOfpage;
        } catch (SQLException msg) {
            Logger.getLogger(GetAllQuestionClass.class.getName()).log(Level.SEVERE, null, msg);
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
        }
        return 0;
    }

    /**
     *
     * @param pageNo
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public List<recentQuestionPojo> AllQuestion(int pageNo) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        List<recentQuestionPojo> list = new ArrayList<>();
        indexPageExtraFunction function = new indexPageExtraFunction();
        indexPage index = new indexPage();
        time time = new time();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(pageNo == 0){
            pageNo = 1;
        }
        int recordPerPage = 20;
        int startPage = (pageNo * recordPerPage) - recordPerPage;

        try {
            String sql = "select q.q_id,q.question,q.vote,q.total_view,q.posted_time,q.updated_time as date,"
                    + "user.id,user.firstname,user.username,user.user_type,user.higher_edu from question q inner join newuser user "
                    + "on user.id = q.id ORDER BY q.q_id DESC limit ?,?";
            con = ds.getConnection();
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
                //function.updateQuestionView(questionId); //Creating some problem
                recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, days, questionId, question, vote, fullName, userName, userType, higherEdu, userId, totalAnswer);
                list.add(recentQuestionPojo);
            }
            return list;
            /*
            Though not as convenient, it is also possible to find out how many rows a nonscrollable result set has. 
            The following example shows one way to determine the number of rows.

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM EMPLOYEES");
            rs.next();
                int count = rs.getInt(1);
            System.out.println("XYZ, Inc. has " + count + " employees");

                    ResultSet rs2 = stmt.executeQuery(	"SELECT LAST_NAME, FIRST_NAME FROM EMPLOYEES");
                        while (rs2.next()) {
	. . . // retrieve first and last names of each employee
}           
             */
        } catch (SQLException msg) {
            Logger.getLogger(GetAllQuestionClass.class.getName()).log(Level.SEVERE, null, msg);
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
        }
        return null;
    }
}
