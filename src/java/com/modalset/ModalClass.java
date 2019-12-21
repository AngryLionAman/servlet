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
package com.modalset;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class ModalClass {

    public List<String> getOptionByQuestionId(int questionId) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        List<String> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "SELECT answer FROM set_question_option WHERE question_id  = ? ORDER BY 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String opt = rs.getString("answer");
                list.add(opt);
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(ModalClass.class.getName()).log(Level.SEVERE, null, msg);
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

    public HashMap<Integer, String> getSetQuestionById(int questionId) throws SQLException, ClassNotFoundException {

        HashMap<Integer, String> map = new HashMap<>();
        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "SELECT unique_id, question FROM set_question WHERE unique_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while (rs.next()) {

                int q_Id = rs.getInt("unique_id");
                String question = rs.getString("question");
                map.put(q_Id, question);
            }
            return map;
        } catch (SQLException msg) {
            Logger.getLogger(ModalClass.class.getName()).log(Level.SEVERE, null, msg);
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

    public List<QuestionSetPojo> initilizeArrayList(String exam_of, int set_no) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();
        List<QuestionSetPojo> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "SELECT unique_id,question,correct_ans FROM set_question WHERE exam_of = ? AND set_no = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, exam_of);
            ps.setInt(2, set_no);
            rs = ps.executeQuery();
            while (rs.next()) {
                int questionId = rs.getInt("unique_id");
                String question = rs.getString("question");
                String correct_ans = rs.getString("correct_ans");
                String selected_ans = null;
                list.add(new QuestionSetPojo(questionId, question, correct_ans, selected_ans));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(ModalClass.class.getName()).log(Level.SEVERE, exam_of, msg);
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

    public int arraySise(String exam_of, int set_no) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "SELECT COUNT(*) AS cnt FROM set_question WHERE exam_of = ? AND set_no = ? GROUP BY exam_of";
            ps = con.prepareStatement(sql);
            ps.setString(1, exam_of);
            ps.setInt(2, set_no);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("cnt");
            }
        } catch (SQLException msg) {
            Logger.getLogger(ModalClass.class.getName()).log(Level.SEVERE, exam_of, msg);
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
        return 0;
    }

}
