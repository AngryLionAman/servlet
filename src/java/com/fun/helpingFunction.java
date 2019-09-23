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
package com.fun;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AngryLion
 */
public class helpingFunction {
    public List<String> CategoryDetail() throws SQLException{
        DatabaseConnection dc = DatabaseConnection.getInstance();
        List<String> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dc.getConnection();
            String sql = "select distinct category from fun";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String category = rs.getString("category");
                list.add(category);
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
        return list;
    }
    public List<String> TypeDetail() throws SQLException{
        DatabaseConnection dc = DatabaseConnection.getInstance();
        List<String> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dc.getConnection();
            String sql = "select distinct type from fun where type is not null";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String type = rs.getString("type");
                list.add(type);
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
        return list;
    }
    public List<String> basedOnDetail() throws SQLException{
        DatabaseConnection dc = DatabaseConnection.getInstance();
        List<String> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dc.getConnection();
            String sql = "select distinct based_on from fun where based_on is not null";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String basedOn = rs.getString("based_on");
                list.add(basedOn);
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
        return list;
    }
}
