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
package com.comments.vote;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author AngryLion
 */
public class saveVote {

    public boolean saveVoteOfQuestionAndAnswer(String action, String section, int questionOrAnswerId, int userId) throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        boolean value;
        try {
            con = dc.getConnection();
            String sql = "";
            if (section.equals("answer") && action.equals("upvote")) {

                sql = "insert into vote_by_user(answer_id,user_id)values(?,?)";

            }
            if (section.equals("answer") && action.equals("downvote")) {

                sql = "delete from vote_by_user where answer_id = ? and user_id  = ?";

            }
            if (section.equals("question") && action.equals("upvote")) {

                sql = "insert into vote_by_user(question_id,user_id)values(?,?)";

            }
            if (section.equals("question") && action.equals("downvote")) {

                sql = "delete from vote_by_user where question_id = ? and user_id  = ?";

            }
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionOrAnswerId);
            ps.setInt(2, userId);
            value = ps.execute();
        } catch (SQLException msg) {
            throw msg;
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

        return value;
    }

}
