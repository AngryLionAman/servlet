/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

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
     * @param con
     * @param questionId
     * @throws Exception
     */
    public void updateQuestionView(Connection con, int questionId) throws Exception {

        String sql = "UPDATE question SET total_view = total_view + 1 WHERE q_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(indexPageExtraFunction.class.getName()).log(Level.SEVERE, null, msg);
        }
    }

    /**
     *
     * @param con
     * @param topicId
     * @return
     * @throws Exception
     */
    public int totalFollowersOfTopic(Connection con, int topicId) throws Exception {

        String sql = "select count(topic_id)as cnt from topic_followers_detail where topic_id = ? group by topic_id";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
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
     * @param con
     * @param topicId
     * @return
     * @throws Exception
     */
    public int totalRelatedQuestion(Connection con, int topicId) throws Exception {

        String sql = "select count(*) as cnt from question_topic_tag where tag_id = ? group by tag_id";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
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
