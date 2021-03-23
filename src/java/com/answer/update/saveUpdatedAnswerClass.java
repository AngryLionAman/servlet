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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class saveUpdatedAnswerClass {

    /**
     *
     * @param con
     * @param answerid
     * @param answer
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean SaveupdatedAnswerByAnswerId(Connection con, int answerid, String answer) throws SQLException, ClassNotFoundException, Exception {

        try (PreparedStatement ps = con.prepareStatement("update answer set answer = ? where a_id=?")) {

            ps.setString(1, answer);
            ps.setInt(2, answerid);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(saveUpdatedAnswerClass.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }
}
