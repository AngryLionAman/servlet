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
package com.notifications;

import com.connect.DatabaseConnection;
import com.string.validateInput;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class CreateNotification {

    public boolean userGetFollowed(int notification_receiver_id, int notification_creater_id, int content_id) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO notification (user_id, notification_type,creater_id, content_id)VALUES(?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, notification_receiver_id);
            ps.setString(2, "followed_by");
            ps.setInt(3, notification_creater_id);
            ps.setInt(4, content_id);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param session_user_id
     * @param on_comment_user_id
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean CreateNotificationOfProfileComment(int session_user_id, int on_comment_user_id) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO notification (user_id,notification_type,creater_id, content_id) VALUES (?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, on_comment_user_id);
            ps.setString(2, "comment_on_Profile");
            ps.setInt(3, session_user_id);
            ps.setInt(4, on_comment_user_id);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param userId
     * @param oldQuestionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean questionRejectedByAdmin(int userId, int oldQuestionId) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO notification (user_id,content_id,notification_type,creater_id)VALUES(?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setInt(2, oldQuestionId);
                ps.setString(3, "question_approvel_rejected_by_admin");
                ps.setInt(4, 0);
                return ps.execute();

            } catch (SQLException msg) {
                Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param userId
     * @param oldQuestionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean questionRejectedByUser(int userId, int oldQuestionId) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO notification (user_id,content_id,notification_type,creater_id)VALUES(?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setInt(2, oldQuestionId);
                ps.setString(3, "question_approvel_rejected_by_user");
                ps.setInt(4, 0);
                return ps.execute();

            } catch (SQLException msg) {
                Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param userId
     * @param oldQuestionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean questionApprovedByAdmin(int userId, int oldQuestionId) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO notification (user_id,content_id,notification_type,creater_id)VALUES(?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setInt(2, oldQuestionId);
                ps.setString(3, "question_approved_by_admin");
                ps.setInt(4, 0);
                return ps.execute();
            } catch (SQLException msg) {
                Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param userId
     * @param oldQuestionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean questionApprovedByUser(int userId, int oldQuestionId) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO notification (user_id,content_id,notification_type,creater_id)VALUES(?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, oldQuestionId);
            ps.setString(3, "question_approved_by_user");
            ps.setInt(4, 0);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param userId
     * @param oldQuestionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean requestHasBeenApprovedForQuestion(int userId, int oldQuestionId) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO notification (user_id,notification_type,creater_id,content_id)VALUES(?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setString(2, "modified_question_approved");
                ps.setInt(3, 0);
                ps.setInt(4, oldQuestionId);
                return ps.execute();
            } catch (SQLException msg) {
                Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param userId
     * @param followerdId
     * @param questionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean modificationOfQuestionRequest(int userId, int followerdId, int questionId) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO notification (user_id,creater_id,content_id,notification_type)VALUES(?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, followerdId);
            ps.setInt(3, questionId);
            ps.setString(4, "approvel_for_question");
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param userid
     * @param useridWhoPostedQuestion
     * @param questionid
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean UserGotAnswerOfQuestion(int userid, int useridWhoPostedQuestion, int questionid) throws SQLException, ClassNotFoundException, Exception {
        /*
        @userid - aslo consider as followers_id, Who gave the answer, id of current active user
        @useridWhoPostedQuestion - who asked the question
        @questionid - id of question
         */

        String sql = "INSERT INTO notification (user_id,notification_type,creater_id,content_id)VALUES(?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            /* In MySql
            @user_id - who will get the notification, mean who posted the questiion
            @notification_type - 
            @followers_id - who is creating notification, mean who gave the answer
             */
            ps.setInt(1, useridWhoPostedQuestion);
            ps.setString(2, "got_answer_of_a_question");
            ps.setInt(3, userid);
            ps.setInt(4, questionid);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param sessionUserId
     * @param bloggerId
     * @param blogId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean CreateNotificationForBlogComment(int sessionUserId, int bloggerId, int blogId) throws SQLException, ClassNotFoundException, Exception {

        String sql = "INSERT INTO notification (user_id,notification_type,creater_id,content_id) VALUES (?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bloggerId);
            ps.setString(2, "comment_on_Blog");
            ps.setInt(3, sessionUserId);
            ps.setInt(4, blogId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param userIdWhoPostedQuestion
     * @param userId
     * @param questionId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean CreateNotificationForQuestionComment(int userIdWhoPostedQuestion, int userId, int questionId) throws SQLException, ClassNotFoundException, Exception {

        String sql = "INSERT INTO notification (user_id,notification_type,creater_id,content_id ) VALUES (?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userIdWhoPostedQuestion);
            ps.setString(2, "comment_on_question");
            ps.setInt(3, userId);
            ps.setInt(4, questionId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param allUserId
     * @param sessionActiveUserId
     * @param questionId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean CreateNotificationOfQuestionForAllRealtedUsers(String allUserId, int sessionActiveUserId, int questionId) throws SQLException, ClassNotFoundException, Exception {

        validateInput input = new validateInput();

        boolean val = true;
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            String[] userId = allUserId.split(" ");
            String sql = "INSERT INTO notification (user_id,notification_type,creater_id,content_id ) VALUES (?,?,?,?)";
            for (String obj : userId) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, input.getOnlyInteger(obj));
                    ps.setString(2, "comment_on_question");
                    ps.setInt(3, sessionActiveUserId);
                    ps.setInt(4, questionId);
                    val = ps.execute();
                } catch (SQLException msg) {
                    Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, allUserId, msg);
                }
            }
            return val;
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, allUserId, msg);
        }
        return val;
    }

    /**
     *
     * @param allUserId
     * @param followersIdWhoCommentd
     * @param questionId
     * @param answerId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean CreateNotificationOfAnswerCommentForAllReatedUsers(String allUserId, int followersIdWhoCommentd, int questionId, int answerId) throws SQLException, ClassNotFoundException, Exception {

        validateInput input = new validateInput();

        boolean status = true;

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            String[] userId = allUserId.split(" ");
            String sql = "INSERT INTO notification (user_id,notification_type,creater_id,content_id) VALUES (?,?,?,?)";
            for (String obj : userId) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, input.getOnlyInteger(obj));
                    ps.setString(2, "comment_on_Answer");
                    ps.setInt(3, followersIdWhoCommentd);
                    ps.setInt(4, answerId);
                    status = ps.execute();
                } catch (SQLException msg) {
                    Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
                }
            }
            return status;
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return status;
    }

    /**
     *
     * @param userIdWhoPostedQuestion
     * @param followersIdWhoCommentd
     * @param questionId
     * @param answerId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean CreateNotificationOfAnswerComment(int userIdWhoPostedQuestion, int followersIdWhoCommentd, int questionId, int answerId) throws SQLException, ClassNotFoundException, Exception {

        String sql = "INSERT INTO notification (user_id,notification_type,creater_id,content_id) VALUES (?,?,?,?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userIdWhoPostedQuestion);
            ps.setString(2, "comment_on_Answer");
            ps.setInt(3, followersIdWhoCommentd);
            ps.setInt(4, answerId);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }
}
