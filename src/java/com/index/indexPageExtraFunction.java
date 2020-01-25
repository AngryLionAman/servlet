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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inquiryhere.com
 */
public class indexPageExtraFunction {

    /**
     *
     * @param questionId
     * @throws Exception
     */
    public void updateQuestionView(int questionId) throws Exception {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "UPDATE question SET total_view = total_view + 1 WHERE q_id = ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(indexPageExtraFunction.class.getName()).log(Level.SEVERE, null, msg);
        }
    }

    /**
     *
     * @param topicId
     * @return
     * @throws Exception
     */
    public int totalFollowersOfTopic(int topicId) throws Exception {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "select count(topic_id)as cnt from topic_followers_detail where topic_id = ? group by topic_id";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, topicId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(indexPageExtraFunction.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param topicId
     * @return
     * @throws Exception
     */
    public int totalRelatedQuestion(int topicId) throws Exception {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "select count(*) as cnt from question_topic_tag where tag_id = ? group by tag_id";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, topicId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(indexPageExtraFunction.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }
}
