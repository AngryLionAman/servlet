/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class indexPageExtraFunction {

    public void updateQuestionView(int questionId) throws Exception {
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE question SET total_view = total_view + 1 WHERE q_id = ?";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            ps.execute();
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {
                }
            }
            if (con != null && !con.isClosed()) {
                if (!con.getAutoCommit()) {
                    con.commit();
                    con.setAutoCommit(true);
                }
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
    }

    public int totalFollowersOfTopic(int topicId) throws Exception {
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "select count(topic_id)as cnt from topic_followers_detail where topic_id = ? group by topic_id";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, topicId);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("cnt");
            }
        } catch (SQLException msg) {
            throw msg;
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
            if (con != null && !con.isClosed()) {
                if (!con.getAutoCommit()) {
                    con.commit();
                    con.setAutoCommit(true);
                }
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
        return result;
    }

    public int totalRelatedQuestion(int topicId) throws Exception {
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "select count(*) as cnt from question_topic_tag where tag_id = ? group by tag_id";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, topicId);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("cnt");
            }
        } catch (SQLException msg) {
            throw msg;
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
            if (con != null && !con.isClosed()) {
                if (!con.getAutoCommit()) {
                    con.commit();
                    con.setAutoCommit(true);
                }
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
        return result;
    }
}
