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
import com.string.WordFormating;
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
public class ModelSetClass {

    public boolean saveOptionalAnswer(int questionId, String[] opt) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();
        WordFormating wf = new WordFormating();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "INSERT INTO set_question_option (question_id, answer) VALUES (?,?)";
            for (String obj : opt) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionId);
                ps.setString(2, wf.RemoveWhiteSpace(obj));
                ps.execute();
            }
            return true;
        } catch (SQLException msg) {
            Logger.getLogger(ModelSetClass.class.getName()).log(Level.SEVERE, null, msg);
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
        return false;
    }

    public int getQuestionIdByModelSetQuestion(String question) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "SELECT unique_id FROM set_question WHERE question = ? ORDER BY 1 DESC LIMIT 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, question);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("unique_id");
            }
        } catch (SQLException msg) {
            Logger.getLogger(ModelSetClass.class.getName()).log(Level.SEVERE, null, msg);
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

    public boolean saveSetQuestion(String exam_of, int set_no, String question, String correct_ans) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dc.getConnection();
            String sql = "INSERT INTO set_question(exam_of,set_no,question,correct_ans) VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, exam_of);
            ps.setInt(2, set_no);
            ps.setString(3, question);
            ps.setString(4, correct_ans);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(ModelSetClass.class.getName()).log(Level.SEVERE, exam_of, msg);
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
        return true;
    }

}
