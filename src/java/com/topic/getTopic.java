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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class getTopic {

    /**
     *
     * @param topicid
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public HashMap<Integer, String> getTopicDetailByRefId(int topicid) throws SQLException, Exception {
        //indexPageExtraFunction function = new indexPageExtraFunction();
        
        DatabaseConnection ds = new DatabaseConnection();

        //List<topicPojo> list = new ArrayList<>();
        HashMap<Integer, String> map = new HashMap<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select DISTINCT t.unique_id,t.topic_name from topic t inner join question_topic_tag qtt on t.unique_id=qtt.tag_id where question_id IN (select question_id from question_topic_tag where tag_id= ? ) and t.unique_id is not null and t.topic_name is not null group by t.unique_id limit 30";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, topicid);
            rs = ps.executeQuery();
            while (rs.next()) {
                String topicName = rs.getString("t.topic_name").trim();
                int topicId = rs.getInt("t.unique_id");
                map.putIfAbsent(topicId, topicName);
            }
            return map;
        } catch (SQLException msg) {
            Logger.getLogger(getTopic.class.getName()).log(Level.SEVERE, null, msg);
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
