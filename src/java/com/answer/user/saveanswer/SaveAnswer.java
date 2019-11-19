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
public class SaveAnswer {

    public boolean saveAnswerByQuestionAndIdUserId(int userid, int questionid, String answer) throws SQLException {

        DatabaseConnection dc = DatabaseConnection.getInstance();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dc.getConnection();
            String sql = "insert into answer(q_id,answer,Answer_by_id,vote) values(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionid);
            ps.setString(2, answer);
            ps.setInt(3, userid);
            ps.setInt(4, 0);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(SaveAnswer.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    //out.println("Exception in closing preparedStatement " + e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    //out.println("Exception in closing connection " + e);
                }
            }
        }
        return true;
    }
}