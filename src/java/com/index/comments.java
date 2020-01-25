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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inquiryhere.com
 */
public class comments {

    /**
     *
     * @param questionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public List<commentPojo> commentsOnQuestion(int questionId) throws SQLException, ClassNotFoundException, Exception {

        List<commentPojo> list = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "select c.unique_id,c.comments,date_format(c.time,\"%e %b %Y,%h:%i%p\") as date,user.id,user.firstname,"
                + "user.username,user.user_type from comments c right join newuser user on user.id = c.user_id "
                + "where c.content_id = ? AND c.comment_type = ? AND approved_by_admin = ? order by 1 desc";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            ps.setString(2, "commet_on_question");
            ps.setBoolean(3, true);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int commentId = rs.getInt("c.unique_id");
                    String comment = rs.getString("c.comments");
                    String time = rs.getString("date");
                    String userType = rs.getString("user_type");
                    int userId = rs.getInt("user.id");
                    String userFullName = rs.getString("user.firstname");
                    String userUserName = rs.getString("user.username");
                    list.add(new commentPojo(commentId, comment, time, userType, userId, userFullName, userUserName));
                }
                return list;
            }
        } catch (Exception msg) {
            Logger.getLogger(comments.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
