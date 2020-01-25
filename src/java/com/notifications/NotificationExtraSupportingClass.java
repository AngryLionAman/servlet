/*
 * Copyright 2020 AngryLion.
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
package com.notifications;

import com.connect.DatabaseConnection;
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
public class NotificationExtraSupportingClass {

    public int getQuestionIdByAnswerId(int answerId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT q_id AS content FROM answer WHERE a_id = ? LIMIT 1";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, answerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("content");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(NotificationExtraSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    public String getQuestionByAnswerId(int answerId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT question AS content FROM question INNER JOIN answer ON question.q_id = answer.q_id WHERE answer.a_id = ? LIMIT 1";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, answerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getString("content");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(NotificationExtraSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    public String getQuestionById(int questionId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT question AS content FROM question WHERE q_id = ? LIMIT 1";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getString("content");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(NotificationExtraSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    public String getFullNameById(int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT firstname FROM newuser WHERE id = ? LIMIT 1";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getString("firstname");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(NotificationExtraSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    public String getUserNameById(int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT username FROM newuser WHERE id = ? LIMIT 1";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getString("username");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(NotificationExtraSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        }
        return "userName";
    }
}
