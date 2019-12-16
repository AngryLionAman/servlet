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
package com.fun;

import com.connect.SecondPoolConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author AngryLion
 */
public class SaveFunDataClassFile {

    public boolean saveFunData(int userId, String title, String description, String category, String basedon, String type) throws Exception {

        SecondPoolConnection pc = new SecondPoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            
            con = ds.getConnection();
            String sql = "insert into fun(posted_by_id,title,description,category,based_on,type)value(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, category);
            ps.setString(5, basedon);
            ps.setString(6, type);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(SaveFunDataClassFile.class.getName()).log(Level.SEVERE, type, msg);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
        return true;
    }
}
