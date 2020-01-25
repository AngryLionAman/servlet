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
package com.count;

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
public class CountRowsProfile {

    /**
     *
     * @param userId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int CountBlogRowByUserId(int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();
        String sql = "SELECT COUNT(*) AS cnt FROM blog WHERE posted_by = ?";
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(CountRowsProfile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param userId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int CountFollowersRowByUserId(int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();
        String sql = "SELECT COUNT(*) AS cnt FROM newuser user INNER JOIN ak_follower_detail ak ON ak.followers_id=user.ID WHERE user_id = ? AND user.id <> ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(CountRowsProfile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param userId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int CountFollowingRowByUserId(int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT count(*) AS cnt FROM newuser user INNER JOIN ak_follower_detail ak ON ak.user_id=user.ID WHERE followers_id = ? AND user.id <> ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }

        } catch (SQLException msg) {
            Logger.getLogger(CountRowsProfile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param userId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int CountTopicRowByUserId(int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT COUNT(*) AS cnt FROM topic t INNER JOIN topic_followers_detail de ON t.unique_id = de.topic_id WHERE user_or_followers_id = ? AND t.unique_id IS NOT NULL AND t.topic_name IS NOT NULL";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(CountRowsProfile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param userId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int CountAnswerRowByUserId(int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT COUNT(*) AS cnt FROM answer WHERE Answer_by_id = ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(CountRowsProfile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param userId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int CountQuestionRowByUserId(int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT COUNT(*) AS cnt FROM question WHERE id = ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(CountRowsProfile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }
}
