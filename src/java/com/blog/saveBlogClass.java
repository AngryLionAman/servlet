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
package com.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class saveBlogClass {

    /**
     *
     * @param con
     * @param sub
     * @param desc
     * @param id
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean saveBlog(Connection con, String sub, String desc, int id) throws SQLException, ClassNotFoundException {

        String sql = "insert into blog(subject,`desc`,posted_by) values(?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sub);
            ps.setString(2, desc);
            ps.setInt(3, id);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(saveBlogClass.class.getName()).log(Level.SEVERE, desc, msg);
        }
        return true;
    }
}
