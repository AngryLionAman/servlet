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
public class getAnswer {

    private void updateAnswerCountByOne(int answerId) throws SQLException, ClassNotFoundException, Exception {
        
        DatabaseConnection ds = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE answer SET total_view = total_view + 1 WHERE a_id =?";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, answerId);
            ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(getAnswer.class.getName()).log(Level.SEVERE, null, msg);
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
    }

    /**
     *
     * @param qId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<getAnswerPojo> getAnswerById(int qId) throws SQLException, ClassNotFoundException, Exception {
        List<getAnswerPojo> list = new ArrayList<>();
        
        DatabaseConnection ds = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ans.Answer_by_id as userid,user.username as username,user.firstname as fullname,ans.answer as answer,ans.a_id as answerid,ans.total_view as totalview,ans.vote as vote FROM newuser user RIGHT JOIN answer ans on user.id = ans.Answer_by_id where q_id = ? and a_id is not null order by vote desc";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, qId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userName = rs.getString("username");
                String fullName = rs.getString("fullname");
                String answer = rs.getString("answer");
                int answerId = rs.getInt("answerid");
                //updateAnswerCountByOne(answerId); to avoide the exception
                int totalView = rs.getInt("totalview");
                int vote = rs.getInt("vote");
                list.add(new getAnswerPojo(userId, userName, fullName, answer, answerId, totalView, vote));
            }
            return list;
        } catch (SQLException msg) {
           Logger.getLogger(getAnswer.class.getName()).log(Level.SEVERE, null, msg);
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
        return null;
    }
}
