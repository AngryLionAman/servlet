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
package com.answer.user.saveanswer;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class supportingFunction {

    public boolean CreateNotification(int userid, int useridWhoPostedQuestion, int questionid) throws SQLException {
        /*
        @userid - aslo consider as followers_id, Who gave the answer, id of current active user
        @useridWhoPostedQuestion - who asked the question
        @questionid - id of question
         */
        DatabaseConnection dc = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = dc.getConnection();
            String sql = "INSERT INTO notification (user_id,notification_type,followers_id,question_id)VALUES(?,?,?,?)";
            /* In MySql
            @user_id - who will get the notification, mean who posted the questiion
            @notification_type - 
            @followers_id - who is creating notification, mean who gave the answer
             */
            ps = con.prepareStatement(sql);
            ps.setInt(1, useridWhoPostedQuestion);
            ps.setString(2, "got_answer_of_a_question");
            ps.setInt(3, userid);
            ps.setInt(4, questionid);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(supportingFunction.class.getName()).log(Level.SEVERE, null, msg);
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
        return true;
    }
}
