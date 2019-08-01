/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.connect.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inquiryhere.com
 */
public class totalUser {

    public List<saveUser> totalUserDetails() throws SQLException {
        database obj = new database();
        List<saveUser> o = new ArrayList<>();
        try {
            Connection con = obj.connect();
            String sql = "select * from newuser limit 10";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("firstname");
                String email = rs.getString("email");
                int id = rs.getInt("id");
                o.add(new saveUser(userName, email, id));
            }
            obj.disconnect();
        } catch (SQLException msg) {
            throw msg;
        }
        return o;
    }

}
