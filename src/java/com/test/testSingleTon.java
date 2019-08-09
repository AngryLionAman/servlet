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
package com.test;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class testSingleTon {
   private void userDetail() throws SQLException{
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connection.getConnection();
            String sql = "select username,firstname from newuser where id =139";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("username"));
                System.out.println(","+rs.getString("firstname"));
            }
        }catch(SQLException msg){
            System.err.println(msg);
        }finally{
            rs.close();
            ps.close();
            con.close();
            
        }
    }
   public static void main(String[] args) throws SQLException{
       testSingleTon obj = new testSingleTon();
       obj.userDetail();
   }
}
