/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.connect.DatabaseConnection;
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
public class usePool {

    public List<String> user() throws Exception {
        List<String> name = new ArrayList<>();
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connection.getConnection();
            String sql = "select * from newuser limit 5";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                String username = rs.getString("username");
                name.add(username);
                System.out.println(username);
            }

        } catch (Exception msg) {
            throw msg;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }

        }
        return name;

    }

}
