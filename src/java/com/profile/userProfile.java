/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author inquiryhere.com
 */
public class userProfile {

    public boolean saveUserProfile(String eMail, String higherQualification,
            String bestAchievement, String Bio) throws SQLException, ClassNotFoundException, Exception {
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            String email = eMail;
            String HigherQualification = higherQualification;
            String BestAchievement = bestAchievement;
            String bio = Bio;
            try {
                String UpdateQuiry = "update newuser set higher_edu = ?,best_achievement = ? ,bio = ? where email = ?";
                con = connection.getConnection();
                preparedStatement = con.prepareStatement(UpdateQuiry);
                preparedStatement.setString(1, HigherQualification);
                preparedStatement.setString(2, BestAchievement);
                preparedStatement.setString(3, bio);
                preparedStatement.setString(4, email);
                preparedStatement.executeUpdate();
            } catch (SQLException msg) {
                throw msg;
            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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
        return true;
    }
}
