/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

import com.connect.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author inquiryhere.com
 */
public class indexPage {

    public List<recentQuestionPojo> recentPostQuestion() throws Exception {
        database db = new database();
        DataSource dataSource = db.setUpPool();
        List<recentQuestionPojo> list = new ArrayList<>();
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT q.total_view,date(q.posted_time) as date,q.q_id,q.question,q.vote,user.firstname,user.higher_edu,user.ID as u_ide FROM question q RIGHT JOIN newuser user ON user.id = q.id WHERE q.q_id IS NOT NULL AND q.question IS NOT NULL ORDER BY q_id DESC LIMIT 5;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int totalView = rs.getInt("q.total_view");
                String date = rs.getString("date");
                int questionId = rs.getInt("q.q_id");
                String question = rs.getString("q.question");
                int vote = rs.getInt("q.vote");
                String fullName = rs.getString("user.firstname");
                String higherEdu = rs.getString("user.higher_edu");
                int userId = rs.getInt("user.ID");
                recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, questionId, question, vote, fullName, higherEdu, userId);
                list.add(recentQuestionPojo);
            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {
                }
            }
        }

        return list;

    }

}
