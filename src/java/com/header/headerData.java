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
package com.header;

import com.connect.DatabaseConnection;
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
public class headerData {
    public List<headerPojo> headerUser(int userId) throws Exception{
        List<headerPojo> list = new ArrayList<>();
        
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "select id,username,firstname from newuser where id = ?";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while(rs.next()){
                int user_Id = rs.getInt("id");
                String userName = rs.getString("username");
                String fullName = rs.getString("firstname");
                list.add(new headerPojo(user_Id, userName, fullName));
            }
        }catch(SQLException msg){
            throw msg;
        }finally{
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
            if (con != null && !con.isClosed()) {
                if (!con.getAutoCommit()) {
                    con.commit();
                    con.setAutoCommit(true);
                }
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
        return list;
    }
}
