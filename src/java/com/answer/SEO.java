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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author AngryLion
 */
public class SEO {
    public HashMap<Integer, String> getQuestionTagWithId(int qId) throws SQLException{
        DatabaseConnection dc = new DatabaseConnection();
        HashMap<Integer, String> map = new HashMap<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dc.getConnection();
            String sql = "select tag_id as unique_id,(select topic_name from topic where unique_id = question_topic_tag.tag_id)topic_name from question_topic_tag where question_id =?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, qId);
            rs = ps.executeQuery();
            while(rs.next()){
                int questionTagId = rs.getInt("unique_id");
                String questionTag = rs.getString("topic_name");
                map.put(questionTagId, questionTag);
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
        return map;
    }
    public List<String> getQuestionTag(int qId) throws SQLException{
        DatabaseConnection dc = new DatabaseConnection();
        List<String> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dc.getConnection();
            String sql = "select tag_id as unique_id,(select topic_name from topic where unique_id = question_topic_tag.tag_id)topic_name from question_topic_tag where question_id =?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, qId);
            rs = ps.executeQuery();
            while(rs.next()){
                String questionTag = rs.getString("topic_name");
                list.add(questionTag);
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
    public List<SEOPojo> getTitleAndDescripiton(int qId) throws SQLException{
        DatabaseConnection dc = new DatabaseConnection();
        List<SEOPojo> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT q.q_id AS q_id,q.question AS question,SUBSTRING(a.answer,1,500) AS answer FROM question q LEFT JOIN answer a on q.q_id = a.q_id WHERE q.q_id = ? limit 1";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, qId);
            rs = ps.executeQuery();
            while(rs.next()){
                int questionId = rs.getInt("q_id");
                String question = rs.getString("question");//Title
                String answer = rs.getString("answer");//Description
                list.add(new SEOPojo(questionId, question, answer));
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
