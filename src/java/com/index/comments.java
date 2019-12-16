/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

import com.connect.DatabaseConnection;
import com.connect.PoolConnection;
import java.util.ArrayList;
import java.util.List;
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

        DatabaseConnection ds = new DatabaseConnection();

        List<commentPojo> list = new ArrayList<>();

        Connection com = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            com = ds.getConnection();
            String sql = "select c.unique_id,c.comments,date_format(c.time,\"%e %b %Y,%h:%i%p\") as date,user.id,user.firstname,user.username from comments c right join newuser user on user.id = c.user_id where c.q_id = ? order by 1 desc";
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
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(comments.class.getName()).log(Level.SEVERE, null, msg);
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
            if (com != null) {
                try {
                    com.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
        }
        return null;
    }
}
