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
package com.topic;

import com.connect.DatabaseConnection;
import com.user.saveNewUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class selectSomeTopic {

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public Map<Integer, String> SelectSomeTopic() throws SQLException, ClassNotFoundException {

        Map<Integer, String> map = new HashMap<>();

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement("select unique_id,topic_name from topic where crawl = 1 order by rand() limit 500");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getInt("unique_id"), rs.getString("topic_name"));
            }
            return map;
        } catch (SQLException msg) {
            Logger.getLogger(saveNewUser.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
