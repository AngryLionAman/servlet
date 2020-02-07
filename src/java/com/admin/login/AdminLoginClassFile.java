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
package com.admin.login;

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
public class AdminLoginClassFile {

    public boolean validateAdminUser(Connection con, String eMail, String passWord) {

        String sql = "select id,username from newuser where email = ? and password = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, eMail);
            ps.setString(2, passWord);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.first();
            }

        } catch (SQLException msg) {
            Logger.getLogger(AdminLoginClassFile.class.getName()).log(Level.SEVERE, eMail, msg);
        }
        return false;
    }

}
