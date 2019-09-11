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

/**
 *
 * @author AngryLion
 */
public class displayAds {

    private void updateImpressionOfads(int adsId) throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        try {
            con = dc.getConnection();
            String sql = "UPDATE advertise SET impression = impression+1 WHERE id = ?";
            ps1 = con.prepareStatement(sql);
            ps1.setInt(1, adsId);
            ps1.execute();
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (SQLException msg) {

                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
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
    }

    public List<displayAdsPojo> displayRandomAds() throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        List<displayAdsPojo> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dc.getConnection();
            String sql = "select * from advertise where display = 1 order by rand() limit 1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
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
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {

                }
            }
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
        return list;
    }
}
