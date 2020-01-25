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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inquiryhere.com
 */
public class totalQuestion {

    /**
     *
     * @param topicId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int totalQestion(int topicId) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "select count(q.question_id)as cnt from topic t left join question_topic_tag q on q.tag_id = t.unique_id where t.unique_id =?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, topicId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(totalQuestion.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }
}
