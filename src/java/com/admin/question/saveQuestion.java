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
package com.admin.question;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author AngryLion
 */
public class saveQuestion {
    protected boolean updateQuestionById(int questionId,String question) throws SQLException{
        DatabaseConnection dc = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        boolean status = true;
        try {
            String sql = "update question set question = ? where q_id = ?";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, question);
            ps.setInt(2, questionId);
            status = ps.execute();
        }catch(SQLException msg){
            throw msg;
        }finally{
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
        return status;
        
    }

    protected boolean deleteQuesstionTag(int questionId) throws SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        boolean status = true;
        try {
            String sql = "delete from question_topic_tag where question_id = ?";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            status = ps.execute();
        } catch (SQLException msg) {
            throw msg;
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
        return status;

    }

    public void saveQuestionWithIdAndTag(int questionId,String question, String questionTag) throws SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            con = dc.getConnection();
            //Delete the stored question tag
            boolean status = deleteQuesstionTag(questionId);
            boolean status2 = updateQuestionById(questionId, question);
            if (!status && !status2) {
                /**
                 * ***********
                 * Storing tag, If not present ************
                 */
                String[] arrSplit = questionTag.split(",");
                for (String arrSplit1 : arrSplit) {
                    if (!arrSplit1.isEmpty()) {
                        boolean fountStatus = true;
                        try {
                            String sql = "select topic_name from topic where lower(topic_name) = ?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, arrSplit1.trim().toLowerCase());
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                fountStatus = false;
                            }
                            if (fountStatus) {
                                String sql1 = "insert into topic(topic_name) values(?)";
                                ps1 = con.prepareStatement(sql1);
                                ps1.setString(1, arrSplit1.trim().toLowerCase());
                                ps1.execute();
                            }

                        } catch (SQLException ms) {
                            throw ms;
                        }
                    }
                }
                /**
                 * ******
                 * Storing tag id with question id ************
                 */
                for (String arrSplit1 : arrSplit) {
                    if (!arrSplit1.isEmpty()) {
                        try {
                            String sql2 = "select unique_id from topic where lower(topic_name) = ?";
                            ps2 = con.prepareStatement(sql2);
                            ps2.setString(1, arrSplit1.trim().toLowerCase());
                            rs2 = ps2.executeQuery();
                            if (rs2.next()) {
                                int topicId = rs2.getInt("unique_id");
                                try {
                                    String sql3 = "insert into question_topic_tag(question_id,tag_id) values(?,?)";
                                    ps3 = con.prepareStatement(sql3);
                                    ps3.setInt(1, questionId);
                                    ps3.setInt(2, topicId);
                                    ps3.execute();
                                } catch (SQLException msg) {
                                    throw msg;
                                }
                            }

                        } catch (SQLException msg) {
                            throw msg;
                        }
                    }
                }
            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {

                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                } catch (SQLException msg) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {

                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException msg) {

                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException msg) {

                }
            }
            if (ps3 != null) {
                try {
                    ps3.close();
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
