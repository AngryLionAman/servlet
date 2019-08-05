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
import javax.sql.DataSource;

/**
 *
 * @author inquiryhere.com
 */
public class totalUser {

    public List<saveUser> totalUserDetails() throws SQLException, ClassNotFoundException, Exception {
        database obj = new database();
        DataSource dataSource = obj.setUpPool();
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<saveUser> o = new ArrayList<>();
        try {
            String sql = "select * from newuser limit 10";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("firstname");
                String email = rs.getString("email");
                int id = rs.getInt("id");
                o.add(new saveUser(userName, email, id));
            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {
                }
            }
        }
        return o;
    }

}
