/*
 * Copyright 2019 AngryLion.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.notifications;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class displayNotification {

    public int countNotificationByUserId(int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "SELECT COUNT(*) AS cnt FROM notification WHERE user_id = ? AND seen = 0";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("cnt");
            }
        } catch (SQLException msg) {
            Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException msg) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException msg) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException msg) {
            }
        }

        return 0;
    }

    private static void updateSeenStatus(int notification_id)throws SQLException, ClassNotFoundException {
        DatabaseConnection dc = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = dc.getConnection();
            String sql = "UPDATE notification SET seen = 1 WHERE unique_id = ?";
            ps= con.prepareStatement(sql);
            ps.setInt(1, notification_id);
            ps.executeUpdate();
        }catch(SQLException msg){
             Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
        }finally{
            try{
                if(ps != null){
                    ps.close();
                }
            }catch(SQLException msg){                
            }
            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException msg){                
            }
        }
    }
    /**
     *
     * @param UserId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<notificationPojo> notification(int UserId) throws SQLException, ClassNotFoundException {
        List<notificationPojo> list = new ArrayList<>();
        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = dc.getConnection();
            String sql = "SELECT unique_id,user_id,creater_id,content_id,notification_type,seen FROM notification WHERE user_id = ? ORDER BY 1 DESC LIMIT 20";
            ps = con.prepareStatement(sql);
            ps.setInt(1, UserId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int comment_id = resultSet.getInt("unique_id");
                int user_id = resultSet.getInt("user_id");
                int notificationCreatedBy = resultSet.getInt("creater_id");
                int content_id = resultSet.getInt("content_id");
                String notification_type = resultSet.getString("notification_type");
                boolean seen = resultSet.getBoolean("seen");
                list.add(new notificationPojo(comment_id, user_id, notificationCreatedBy, content_id, notification_type, seen));
                try{
                    updateSeenStatus(comment_id);
                }catch(ClassNotFoundException | SQLException msg){
                    
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
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
        return list;
    }
}
