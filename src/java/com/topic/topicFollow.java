/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topic;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class topicFollow {

    /**
     *
     * @param topicId
     * @param userId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public boolean topicFollw(int topicId, int userId) throws SQLException, ClassNotFoundException, Exception {
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try {
            String sql = "select topic_id from topic_followers_detail where topic_id = ? and user_or_followers_id =?";
            con = connection.getConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, topicId);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                status = true;
            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException msg) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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

        return status;
    }
}
