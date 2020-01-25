/*
 * Copyright 2020 AngryLion.
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
package com.optionalQuestion;

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
 * @author AngryLion
 */
public class DisplayOptionalQuestionClassFile {

    public int getTotalNoOfPageByNoOfPage(int recordPerPage, int noOfOption) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT count(*) AS cnt FROM optional_question WHERE total_option  = ?";

        float totalNumberOfpage = 0;
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, noOfOption);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    totalNumberOfpage = rs.getInt("cnt");
                }
                totalNumberOfpage = totalNumberOfpage / recordPerPage;
                int newnumber = (int) totalNumberOfpage;
                if (totalNumberOfpage > newnumber) {
                    totalNumberOfpage = totalNumberOfpage + 1;
                }
                return (int) totalNumberOfpage;
            }

        } catch (SQLException msg) {
            Logger.getLogger(DisplayOptionalQuestionClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    public int getTotalNoOfPageByBasedOn(int recordPerPage, String basedOn) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();
        String sql = "SELECT COUNT(*) AS cnt FROM optional_question WHERE on_topic = ?";

        float totalNumberOfpage = 0;
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, basedOn);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    totalNumberOfpage = rs.getInt("cnt");
                }
                totalNumberOfpage = totalNumberOfpage / recordPerPage;
                int newnumber = (int) totalNumberOfpage;
                if (totalNumberOfpage > newnumber) {
                    totalNumberOfpage = totalNumberOfpage + 1;
                }
                return (int) totalNumberOfpage;
            }
        } catch (SQLException msg) {
            Logger.getLogger(DisplayOptionalQuestionClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    public int getTotalNoOfPage(int recordPerPage) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        float totalNumberOfpage = 0;
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement("select count(*) as cnt from optional_question");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                totalNumberOfpage = rs.getInt("cnt");
            }
            totalNumberOfpage = totalNumberOfpage / recordPerPage;
            int newnumber = (int) totalNumberOfpage;
            if (totalNumberOfpage > newnumber) {
                totalNumberOfpage = totalNumberOfpage + 1;
            }
            return (int) totalNumberOfpage;
        } catch (SQLException msg) {
            Logger.getLogger(DisplayOptionalQuestionClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    public List<optionalQuestionPojo> getOptionalQuestionByBasedOn(String basedOn, int pageNo, int recordPerPage) throws SQLException, ClassNotFoundException {

        List<optionalQuestionPojo> list = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();

        if (pageNo < 1) {
            pageNo = 1;
        }
        int startPage = (pageNo * recordPerPage) - recordPerPage;

        String sql = "select id,question,answer,on_topic,posted_by,total_option from optional_question where on_topic = ? limit ?,?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, basedOn);
            ps.setInt(2, startPage);
            ps.setInt(3, recordPerPage);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String question = rs.getString("question");
                    String correctAnswer = rs.getString("answer");
                    String onTopic = rs.getString("on_topic");
                    int postedBy = rs.getInt("posted_by");
                    int totalOption = rs.getInt("total_option");
                    list.add(new optionalQuestionPojo(id, question, correctAnswer, onTopic, postedBy, totalOption));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(DisplayOptionalQuestionClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    public List<optionalQuestionPojo> getOptionalQuestionByNoOfPage(int noOfOption, int pageNo, int recordPerPage) throws SQLException, ClassNotFoundException {

        List<optionalQuestionPojo> list = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();

        if (pageNo < 1) {
            pageNo = 1;
        }
        int startPage = (pageNo * recordPerPage) - recordPerPage;

        String sql = "select id,question,answer,on_topic,posted_by,total_option from optional_question where total_option = ? limit ?,?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, noOfOption);
            ps.setInt(2, startPage);
            ps.setInt(3, recordPerPage);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String question = rs.getString("question");
                    String correctAnswer = rs.getString("answer");
                    String onTopic = rs.getString("on_topic");
                    int postedBy = rs.getInt("posted_by");
                    int totalOption = rs.getInt("total_option");
                    list.add(new optionalQuestionPojo(id, question, correctAnswer, onTopic, postedBy, totalOption));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(DisplayOptionalQuestionClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    public List<optionalQuestionPojo> getOptionalQuestionByLimit(int pageNo, int recordPerPage) throws SQLException, ClassNotFoundException {

        List<optionalQuestionPojo> list = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();

        if (pageNo < 1) {
            pageNo = 1;
        }
        int startPage = (pageNo * recordPerPage) - recordPerPage;

        String sql = "select id,question,answer,on_topic,posted_by,total_option from optional_question order by rand() limit ?,?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, startPage);
            ps.setInt(2, recordPerPage);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String question = rs.getString("question");
                    String correctAnswer = rs.getString("answer");
                    String onTopic = rs.getString("on_topic");
                    int postedBy = rs.getInt("posted_by");
                    int totalOption = rs.getInt("total_option");
                    list.add(new optionalQuestionPojo(id, question, correctAnswer, onTopic, postedBy, totalOption));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(DisplayOptionalQuestionClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
