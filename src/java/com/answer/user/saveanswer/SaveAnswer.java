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
package com.answer.user.saveanswer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class SaveAnswer {

    /**
     *
     * @param con
     * @param userid
     * @param questionid
     * @param answer
     * @param approved_by_admin
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean SaveAnswerByQuestionIdAndIdUserId(Connection con, int userid, int questionid, String answer, boolean approved_by_admin) throws SQLException, ClassNotFoundException, Exception {

        String sql = "insert into answer(q_id,answer,Answer_by_id,approved_by_admin) values(?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, questionid);
            ps.setString(2, answer);
            ps.setInt(3, userid);
            ps.setBoolean(4, approved_by_admin);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(SaveAnswer.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }
}
