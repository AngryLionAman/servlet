/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

import com.connect.DatabaseConnection;
import com.connect.PoolConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

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

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE question SET total_view = total_view + 1 WHERE q_id = ?";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(indexPageExtraFunction.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
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
    }

    /**
     *
     * @param topicId
     * @return
     * @throws Exception
     */
    public int totalFollowersOfTopic(int topicId) throws Exception {

        PoolConnection pc = new PoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String sql = "select count(topic_id)as cnt from topic_followers_detail where topic_id = ? group by topic_id";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, topicId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("cnt");
            }
        } catch (SQLException msg) {
            Logger.getLogger(indexPageExtraFunction.class.getName()).log(Level.SEVERE, null, msg);
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
     * @param topicId
     * @return
     * @throws Exception
     */
    public int totalRelatedQuestion(int topicId) throws Exception {

        PoolConnection pc = new PoolConnection();
        DataSource ds = pc.setUpPool();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select count(*) as cnt from question_topic_tag where tag_id = ? group by tag_id";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, topicId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("cnt");
            }
        } catch (SQLException msg) {
            Logger.getLogger(indexPageExtraFunction.class.getName()).log(Level.SEVERE, null, msg);
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
}
