/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.followmoretopic;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class totalQuestion {

    public int totalQestion(int topicId) throws SQLException, ClassNotFoundException, Exception {
        DatabaseConnection connection = new DatabaseConnection();
        Connection com = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            com = connection.getConnection();
            String sql = "select count(q.question_id)as cnt from topic t left join question_topic_tag q on q.tag_id = t.unique_id where t.unique_id =?";
            ps = com.prepareStatement(sql);
            ps.setInt(1, topicId);
            rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt("cnt");

            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (com != null) {
                try {
                    com.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
        }
        return count;
    }

}
