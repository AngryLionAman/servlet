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
package com.advertise;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class displayAds {

    private void updateImpressionOfads(int adsId) throws SQLException, ClassNotFoundException, Exception {

        String sql = "UPDATE advertise SET impression = impression+1 WHERE id = ?";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, adsId);
                ps.execute();
            } catch (SQLException msg) {
                Logger.getLogger(displayAds.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(displayAds.class.getName()).log(Level.SEVERE, null, msg);
        }
    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public List<displayAdsPojo> displayRandomAds() throws SQLException, ClassNotFoundException, Exception {

        List<displayAdsPojo> list = new ArrayList<>();
        String sql = "select * from advertise where display = 1 order by rand() limit 1";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int adsId = rs.getInt("id");
                    //updateImpressionOfads(adsId);
                    String imageName = rs.getString("image_name");
                    String imageAlt = rs.getString("image_alt");
                    int imageHeight = rs.getInt("height");
                    int imageWidth = rs.getInt("width");
                    String promotedBy = rs.getString("promoted_by");
                    String forwardUrl = rs.getString("forward_link");
                    list.add(new displayAdsPojo(adsId, imageName, imageAlt, imageHeight, imageWidth, promotedBy, forwardUrl));
                }
                return list;
            } catch (SQLException msg) {
                Logger.getLogger(displayAds.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(displayAds.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
