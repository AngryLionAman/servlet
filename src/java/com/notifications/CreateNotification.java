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
import com.connect.PoolConnection;
import com.string.validateInput;
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
public class CreateNotification {

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
        DatabaseConnection ds = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
            String sql = "INSERT INTO notification (user_id,notification_type,followers_id,question_id)VALUES(?,?,?,?)";
            /* In MySql
            @user_id - who will get the notification, mean who posted the questiion
            @notification_type - 
            @followers_id - who is creating notification, mean who gave the answer
             */
            ps = con.prepareStatement(sql);
            ps.setInt(1, useridWhoPostedQuestion);
            ps.setString(2, "got_answer_of_a_question");
            ps.setInt(3, userid);
            ps.setInt(4, questionid);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
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

        PoolConnection pc = new PoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
            String sql = "INSERT INTO notification (user_id,notification_type,followers_id,blog_id ) VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bloggerId);
            ps.setString(2, "comment_on_Blog");
            ps.setInt(3, sessionUserId);
            ps.setInt(4, blogId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
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

    /**
     *
     * @param userIdWhoPostedQuestion
     * @param sessionActiveUserId
     * @param questionId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean CreateNotificationForQuestionComment(int userIdWhoPostedQuestion, int sessionActiveUserId, int questionId) throws SQLException, ClassNotFoundException, Exception {

        PoolConnection pc = new PoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
            String sql = "INSERT INTO notification (user_id,notification_type,followers_id,question_id ) VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userIdWhoPostedQuestion);
            ps.setString(2, "comment_on_question");
            ps.setInt(3, sessionActiveUserId);
            ps.setInt(4, questionId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
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

        PoolConnection pc = new PoolConnection();
        DataSource ds = pc.setUpPool();
        
        validateInput input = new validateInput();

        Connection con = null;
        PreparedStatement ps = null;

        boolean val = true;
        try {
            con = ds.getConnection();

            String[] userId = allUserId.split(" ");
            String sql = "INSERT INTO notification (user_id,notification_type,followers_id,question_id ) VALUES (?,?,?,?)";
            for (String obj : userId) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, input.getOnlyInteger(obj));
                ps.setString(2, "comment_on_question");
                ps.setInt(3, sessionActiveUserId);
                ps.setInt(4, questionId);
                val = ps.execute();
            }
            return val;
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, allUserId, msg);
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

        PoolConnection pc = new PoolConnection();
        DataSource ds = pc.setUpPool();
        
        validateInput input = new validateInput();

        Connection con = null;
        PreparedStatement ps = null;

        boolean status = true;

        try {
            con = ds.getConnection();
            String[] userId = allUserId.split(" ");
            String sql = "INSERT INTO notification (user_id,notification_type,followers_id,question_id,ans_id ) VALUES (?,?,?,?,?)";
            for (String obj : userId) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, input.getOnlyInteger(obj));
                ps.setString(2, "comment_on_Answer");
                ps.setInt(3, followersIdWhoCommentd);
                ps.setInt(4, questionId);
                ps.setInt(5, answerId);
                status = ps.execute();
            }
            return status;
        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
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

        PoolConnection pc = new PoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
            String sql = "INSERT INTO notification (user_id,notification_type,followers_id,question_id,ans_id ) VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userIdWhoPostedQuestion);
            ps.setString(2, "comment_on_Answer");
            ps.setInt(3, followersIdWhoCommentd);
            ps.setInt(4, questionId);
            ps.setInt(5, answerId);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(CreateNotification.class.getName()).log(Level.SEVERE, null, msg);
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
