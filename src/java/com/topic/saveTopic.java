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
package com.topic;

import com.connect.DatabaseConnection;
import com.string.validateInput;
import com.user.saveNewUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class saveTopic {

    void SaveTopicByTopicIdAndUserId(String userid, String[] topicName) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = DatabaseConnection.getInstance();

        validateInput input = new validateInput();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dc.getConnection();
            String sql = "insert into topic_followers_detail(topic_id,user_or_followers_id) values(?,?)";
            for (String obj : topicName) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, input.getInputInt(obj));
                ps.setInt(2, input.getInputInt(userid));
                ps.execute();
            }

        } catch (SQLException msg) {
            Logger.getLogger(saveTopic.class.getName()).log(Level.SEVERE, null, msg);
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
    }
}
