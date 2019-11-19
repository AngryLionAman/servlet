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
package com.test;

import com.connect.DatabaseConnection;
import com.question.user.questionClass;
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
public class getRow {
     public static boolean IsTopicIsPresent(String topic) throws SQLException{
        DatabaseConnection dc = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = dc.getConnection();
            String sql = "select topic_name from topic where lower(topic_name) = ? limit 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, topic);
            rs = ps.executeQuery();
            return rs.first();
        } catch (SQLException msg) {
            Logger.getLogger(questionClass.class.getName()).log(Level.SEVERE, null, msg);
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
        return true;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(IsTopicIsPresent("java")+"\n");
        System.out.println(IsTopicIsPresent("aman")+"\n");
        System.out.println(IsTopicIsPresent("alok")+"\n");
        System.out.println(IsTopicIsPresent("computer")+"\n");
        System.out.println(IsTopicIsPresent("JaVa")+"\n");
    }
}
