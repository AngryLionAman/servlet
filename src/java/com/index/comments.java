/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

import com.connect.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class comments {

    public List<commentPojo> commentsOnQuestion(int questionId) throws SQLException, ClassNotFoundException, Exception {
        List<commentPojo> list = new ArrayList<>();
        DatabaseConnection connection = new DatabaseConnection();
        Connection com = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            com = connection.getConnection();
            String sql = "select c.unique_id,c.comments,date_format(c.time,\"%e %b %Y,%h:%i%p\") as date,user.id,user.firstname,user.username from comments c right join newuser user on user.id = c.user_id where c.q_id = ?";
            ps = com.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int commentId = rs.getInt("c.unique_id");
                String comment = rs.getString("c.comments");
                String time = rs.getString("date");
                int userId = rs.getInt("user.id");
                String userFullName = rs.getString("user.firstname");
                String userUserName = rs.getString("user.username");
                list.add(new commentPojo(commentId, comment, time, userId, userFullName, userUserName));
            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
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
            if (com != null ) {
                try {
                    com.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            } else {
            }
        }
        return list;
    }
}
