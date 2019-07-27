/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.followmoretopic;

import com.connect.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class totalQuestion {

    public int totalQestion(int topicId) throws SQLException {
        database obj = new database();
        int count;
        try (Connection com = obj.connect()) {
            String sql = "select count(q.question_id)as cnt from topic t left join question_topic_tag q on q.tag_id = t.unique_id where t.unique_id =?";
            try (PreparedStatement ps = com.prepareStatement(sql)) {
                ps.setInt(1, topicId);
                try (ResultSet rs = ps.executeQuery()) {
                    count = 0;
                    while (rs.next()) {
                        count = rs.getInt("cnt");
                    }
                }
            }

        }
        obj.disconnect();
        return count;
    }

}
