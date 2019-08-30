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

/**
 *
 * @author AngryLion
 */
public class time {
    public int showTime(int questionId) throws SQLException{
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int days = 0;
        try{
           String sql = "SELECT DATEDIFF(CURDATE(), posted_time) AS Date FROM question WHERE q_id = ?";
           con = connection.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, questionId);
           rs = ps.executeQuery();           
           if(rs.next()){
               days = rs.getInt("Date");
           }           
        }catch(SQLException msg){
            throw msg;
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException msg){
                    
                }
            }
            if(ps != null){
                try{
                    ps.close();
                }catch(SQLException msg){
                    
                }
            }
            if(con != null){
                try{
                    con.close();
                }catch(SQLException msg){
                    
                }
            }
        }
        return days;
    }
}
