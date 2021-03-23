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
package com.follow;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class SaveFollowUserClassFile {

    /**
     *
     * @param userId
     * @param followersId
     * @return
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean FollowUser(int userId, int followersId) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO ak_follower_detail(user_id,followers_id) VALUES (?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, followersId);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(SaveFollowUserClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param userId
     * @param followersId
     * @return
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean UnfollowUser(int userId, int followersId) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM ak_follower_detail WHERE  user_id = ? AND followers_id = ?";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, followersId);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(SaveFollowUserClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }
}
