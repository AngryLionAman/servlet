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
package com.profile;

import com.count.CountRowsProfile;
import com.connect.DatabaseConnection;
import com.count.CountExtraActivity;
import com.string.WordFormating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class userSupportingClass {

    /**
     *
     * @param userId
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void UpdateProfileViewBy1ByUserId(int userId) throws SQLException, ClassNotFoundException {
        
       DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
            String sql = "UPDATE newuser SET total_view = total_view + 1 WHERE ID =?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(userSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
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
    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<CountRowByUserIdPojo> CountRowByUserIdController(int userid) throws SQLException, ClassNotFoundException {

        List<CountRowByUserIdPojo> list = new ArrayList<>();

        CountRowsProfile rows = new CountRowsProfile();

        int countQestion = rows.CountQuestionRowByUserId(userid);
        int countAnswer = rows.CountAnswerRowByUserId(userid);
        int countTopic = rows.CountTopicRowByUserId(userid);
        int countFollowing = rows.CountFollowingRowByUserId(userid);
        int countFollowers = rows.CountFollowersRowByUserId(userid);
        int countBlog = rows.CountBlogRowByUserId(userid);

        list.add(new CountRowByUserIdPojo(countQestion, countAnswer, countTopic, countFollowing, countFollowers, countBlog));

        return list;
    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<BlogByUserIdPojo> GetTotalBlogByUserId(int userid) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<BlogByUserIdPojo> list = new ArrayList<>();

        try {
            con = dc.getConnection();
            String sql = "SELECT unique_id,subject,updated_time,posted_by,view FROM blog WHERE posted_by = ? ORDER BY 1 DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int blogId = rs.getInt("unique_id");
                String blogSub = rs.getString("subject");
                Date lastModifiedTime = rs.getDate("updated_time");
                int postedById = rs.getInt("posted_by");
                int totalView = rs.getInt("view");
                
                list.add(new BlogByUserIdPojo(blogId, blogSub, lastModifiedTime, postedById,totalView));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(userSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<FollowingByUserIdPojo> GetTotalFollowingByUserId(int userid) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<FollowingByUserIdPojo> list = new ArrayList<>();

        WordFormating word = new WordFormating();

        try {
            con = dc.getConnection();
            String sql = "SELECT user.ID,user.username,user.firstname,user.imagepath FROM newuser user INNER JOIN ak_follower_detail ak ON ak.user_id=user.ID WHERE followers_id = ? AND user.id <> ? ORDER BY 2";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userid);
            ps.setInt(2, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user.ID");
                String userName = rs.getString("user.username");
                String fullName = word.convertStringUpperToLower(rs.getString("user.firstname"));
                String imagePath = rs.getString("user.imagepath");
                list.add(new FollowingByUserIdPojo(userId, userName, fullName, imagePath));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(userSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;

    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<FollowersByUserIdPojo> GetTotalFollowersByUserId(int userid) throws SQLException, ClassNotFoundException {
        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<FollowersByUserIdPojo> list = new ArrayList<>();

        WordFormating word = new WordFormating();

        try {
            con = dc.getConnection();
            String sql = "SELECT user.ID,user.username,user.firstname,user.imagepath FROM newuser user INNER JOIN ak_follower_detail ak ON ak.followers_id=user.ID WHERE user_id = ? AND user.id <> ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userid);
            ps.setInt(2, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user.ID");
                String userName = rs.getString("user.username");
                String fullName = word.convertStringUpperToLower(rs.getString("user.firstname"));
                String imagePath = rs.getString("user.imagepath");
                list.add(new FollowersByUserIdPojo(userId, userName, fullName, imagePath));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(userSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<TopicByUserIdPojo> GetTotalTopicFollowedByUserId(int userid) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TopicByUserIdPojo> list = new ArrayList<>();

        WordFormating word = new WordFormating();

        try {
            con = dc.getConnection();
            String sql = "SELECT DISTINCT t.unique_id,t.topic_name, t.image_url FROM topic t INNER JOIN topic_followers_detail de ON t.unique_id = de.topic_id WHERE user_or_followers_id= ? AND t.unique_id IS NOT NULL AND t.topic_name IS NOT NULL";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int topicId = rs.getInt("t.unique_id");
                String topicName = word.convertStringUpperToLower(rs.getString("t.topic_name"));
                String imagePath = rs.getString("image_url");
                list.add(new TopicByUserIdPojo(topicId, topicName, imagePath));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(userSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<answerByUserIdPojo> GetTotalAnswerPostedByUserId(int userid) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<answerByUserIdPojo> list = new ArrayList<>();

        try {
            con = dc.getConnection();
            String sql = "SELECT q.q_id,q.question,SUBSTRING(ans.answer,1,200) as short_ans,ans.a_id,ans.Answer_by_id,ans.updatedtime,ans.total_view "
                    + "FROM answer ans INNER JOIN question q ON q.q_id = ans.q_id where Answer_by_id = ? order by 4 DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int questionId = rs.getInt("q.q_id");
                String question = rs.getString("q.question");
                String shortAnswer = rs.getString("short_ans");
                int answerid = rs.getInt("ans.a_id");
                int answerById = rs.getInt("ans.Answer_by_id");
                Date lastModifiedTime = rs.getDate("ans.updatedtime");
                int totalView = rs.getInt("ans.total_view");
                list.add(new answerByUserIdPojo(questionId, question, shortAnswer, answerid, answerById, lastModifiedTime, totalView));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(userSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<questionByUserIdPojo> GetTotalQuestionPostedByUserId(int userid) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection dc = new DatabaseConnection();
        
        CountExtraActivity count = new CountExtraActivity();

        Connection con = null;
        PreparedStatement ps = null;
        
        ResultSet rs = null;

        List<questionByUserIdPojo> list = new ArrayList<>();

        try {
            con = dc.getConnection();
            String sql = "SELECT id,q_id,question,total_view,updated_time FROM question WHERE id = ? ORDER BY 2 DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int questionId = rs.getInt("q_id");
                String question = rs.getString("question");
                int totalView = rs.getInt("total_view");
                int questionPostedById = rs.getInt("id");
                Date lastModifiedTime = rs.getDate("updated_time");
                int totalAnswer = count.CountTotalAnswerOfQuestionByQuestionId(questionId);
                list.add(new questionByUserIdPojo(questionId, question, totalView, questionPostedById, lastModifiedTime, totalAnswer));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(userSupportingClass.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }
}