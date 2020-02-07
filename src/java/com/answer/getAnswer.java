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

    private void updateAnswerCountByOne(Connection con, int answerId) throws SQLException, ClassNotFoundException, Exception {

        try (PreparedStatement ps = con.prepareStatement("UPDATE answer SET total_view = total_view + 1 WHERE a_id =?")) {
            ps.setInt(1, answerId);
            ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(getAnswer.class.getName()).log(Level.SEVERE, null, msg);
        }
    }

    /**
     *
     * @param con
     * @param qId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<getAnswerPojo> getAnswerById(Connection con, int qId) throws SQLException, ClassNotFoundException, Exception {

        List<getAnswerPojo> list = new ArrayList<>();

        String sql = "SELECT ans.Answer_by_id AS userid, user.user_type AS usertype ,user.username AS username,user.firstname AS fullname,"
                + "ans.answer AS answer,ans.a_id AS answerid,ans.total_view AS totalview,ans.vote AS vote FROM newuser user "
                + "RIGHT JOIN answer ans ON user.id = ans.Answer_by_id WHERE q_id = ? AND ans.approved_by_admin = 1 "
                + "AND approved_by_user = 1 ORDER BY vote DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int userId = rs.getInt("userid");
                    String userType = rs.getString("usertype");
                    String userName = rs.getString("username");
                    String fullName = rs.getString("fullname");
                    String answer = rs.getString("answer");
                    int answerId = rs.getInt("answerid");

                    try {
                        updateAnswerCountByOne(con,answerId);
                    } catch (Exception msg) {
                        Logger.getLogger(getAnswer.class.getName()).log(Level.SEVERE, null, msg);
                    }

                    int totalView = rs.getInt("totalview");
                    int vote = rs.getInt("vote");
                    list.add(new getAnswerPojo(userId, userType, userName, fullName, answer, answerId, totalView, vote));
                }
                return list;
            }

        } catch (SQLException msg) {
            Logger.getLogger(getAnswer.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
