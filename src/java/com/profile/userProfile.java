/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile;

import com.connect.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class userProfile {

    public boolean saveUserProfile(String eMail, String higherQualification,
            String bestAchievement, String Bio) throws SQLException {
        database obj = new database();
        try (Connection con = obj.connect()) {
            String email = eMail;
            String HigherQualification = higherQualification;
            String BestAchievement = bestAchievement;
            String bio = Bio;
            try {
                String UpdateQuiry = "update newuser set higher_edu = ?,best_achievement = ? ,bio = ? where email = ?";
                try (PreparedStatement preparedStatement = con.prepareStatement(UpdateQuiry)) {
                    preparedStatement.setString(1, HigherQualification);
                    preparedStatement.setString(2, BestAchievement);
                    preparedStatement.setString(3, bio);
                    preparedStatement.setString(4, email);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
            } catch (SQLException msg) {
                System.err.println(msg);
            }
            obj.disconnect();
            con.close();
        }
        return true;
    }
}
