/*
 * Copyright 2019 inquiryhere.com.
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
import com.index.indexPageExtraFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inquiryhere.com
 */
public class topicDetail {

    public List<topicPojo> topic(int topicid) throws SQLException, Exception {
        indexPageExtraFunction function = new indexPageExtraFunction();
        DatabaseConnection connection = new DatabaseConnection();
        List<topicPojo> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from topic where unique_id = ?";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, topicid);
            rs = ps.executeQuery();
            while (rs.next()) {
                String topicName = rs.getString("topic_name");
                int topicId = rs.getInt("unique_id");
                String imageUrl = rs.getString("image_url");
                String descHindi = rs.getString("desc_hindi");
                String descEng = rs.getString("desc_english");
                int totalFollowers = function.totalFollowersOfTopic(topicId);
                int relatedQuestion = function.totalRelatedQuestion(topicId);
                list.add(new topicPojo(topicName, topicId, imageUrl, descHindi, descEng, totalFollowers, relatedQuestion));
            }
        } catch (Exception msg) {
            throw msg;
        } finally {
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException msg){}
            }
            if(ps != null){
                try{
                    ps.close();
                }catch(SQLException msg){}
            }
            if(con != null){
                try{
                    con.close();
                }catch(SQLException msg){}
            }
        }
        return list;
    }
}
