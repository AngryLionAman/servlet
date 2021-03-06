/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile;

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
 * @author inquiryhere.com
 */
public class EditUserProfileClassFile {

     /**
     *
     * @param profileId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<EditProfilePojoClass> getProfileDataById(int profileId) throws SQLException, ClassNotFoundException {

        List<EditProfilePojoClass> list = new ArrayList<>();

        String sql = "SELECT id,username,firstname AS fullname, email,higher_edu,best_achievement,bio,imagepath FROM newuser WHERE id  = ? LIMIT 1";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, profileId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int userId = rs.getInt("id");
                    String userName = rs.getString("username");
                    String fullName = rs.getString("fullname");
                    String email = rs.getString("email");
                    String higher_edu = rs.getString("higher_edu");
                    String best_achievement = rs.getString("best_achievement");
                    String bio = rs.getString("bio");
                    String imagePath = rs.getString("imagepath");
                    list.add(new EditProfilePojoClass(userId, userName, fullName, email, higher_edu, best_achievement, bio, imagePath));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(EditUserProfileClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param userId
     * @param higherQualification
     * @param bestAchievement
     * @param Bio
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public boolean saveUserProfile(int userId, String higherQualification, String bestAchievement, String Bio) throws SQLException, ClassNotFoundException, Exception {

        String sql = "update newuser set higher_edu = ?,best_achievement = ? ,bio = ? where id = ?";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, higherQualification);
            preparedStatement.setString(2, bestAchievement);
            preparedStatement.setString(3, Bio);
            preparedStatement.setInt(4, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException msg) {
            Logger.getLogger(EditUserProfileClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return false;
    }
}
