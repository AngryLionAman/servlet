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
package com.search;

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
public class saveSearchedQueryClassFile {

    /**
     *
     * @param query
     * @param userId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean SaveSearchedQuery(String query, int userId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "insert into searched_queary(searched_queary,userid) values(?,?)";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, query);
            ps.setInt(2, userId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(saveSearchedQueryClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }
}
