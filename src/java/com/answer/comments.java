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
package com.answer;

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
public class comments {
    
    /**
     *
     * @param answerId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<getAnswerCommentPojo> getAnswerCommentByAnswerid(int answerId) throws SQLException, ClassNotFoundException, Exception{
        
        List<getAnswerCommentPojo> list = new ArrayList<>();
        
        DatabaseConnection ds = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT unique_id as commentid,comments,date_format(time,\"%e %b %Y,%h:%i%p\") as date,"
                    + "user_id as commentpostedbyid, newuser.username as username,newuser.firstname as fullname, newuser.user_type AS usertype "
                    + "FROM comments inner join newuser on newuser.id = comments.user_id "
                    + "WHERE content_id = ? AND comment_type = ? AND approved_by_admin = ? AND approved_by_user = ? order by 1 desc";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, answerId);
            ps.setString(2, "commet_on_answer");
            ps.setBoolean(3, true);
            ps.setBoolean(4, true);
            rs = ps.executeQuery();
            while(rs.next()){
               int commentId = rs.getInt("commentid");
               String comments = rs.getString("comments");
               String date = rs.getString("date");
               int commentPostedById = rs.getInt("commentpostedbyid");
               String userName = rs.getString("username");
               String fullName = rs.getString("fullname");
               String userType = rs.getString("usertype");
               
               list.add(new getAnswerCommentPojo(commentId, comments, date, commentPostedById, userName, fullName,userType));
            }
            return list;
        }catch(SQLException msg){
            Logger.getLogger(comments.class.getName()).log(Level.SEVERE, null, msg);
        }finally {
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
        return null;
    }
}
