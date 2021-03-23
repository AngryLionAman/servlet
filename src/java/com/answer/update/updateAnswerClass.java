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
package com.answer.update;

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
public class updateAnswerClass {

    /**
     *
     * @param anser_id
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public String GetAnswerByAnswerid(int anser_id) throws SQLException, ClassNotFoundException, Exception {

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select answer from answer where a_id = ?")) {
                ps.setInt(1, anser_id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        return rs.getString("answer");
                    }
                } catch (SQLException msg) {
                    Logger.getLogger(updateAnswerClass.class.getName()).log(Level.SEVERE, null, msg);
                }
            } catch (SQLException msg) {
                Logger.getLogger(updateAnswerClass.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(updateAnswerClass.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

}
