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
package com.answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AngryLion
 */
public class SEO {

    /**
     *
     * @param con
     * @param qId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public HashMap<Integer, String> getQuestionTagWithId(Connection con, int qId) throws SQLException, ClassNotFoundException, Exception {

        HashMap<Integer, String> map = new HashMap<>();
        
        String sql = "select tag_id as unique_id,(select topic_name from topic where unique_id = question_topic_tag.tag_id)topic_name from question_topic_tag where question_id =?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int questionTagId = rs.getInt("unique_id");
                    String questionTag = rs.getString("topic_name");
                    map.put(questionTagId, questionTag);
                }
                return map;
            }
        } catch (SQLException msg) {
            Logger.getLogger(SEO.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param con
     * @param qId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<String> getQuestionTag(Connection con, int qId) throws SQLException, Exception {

        List<String> list = new ArrayList<>();

        String sql = "SELECT topic.unique_id AS tag_id, topic.topic_name AS topic_name FROM topic INNER JOIN question_topic_tag ON topic.unique_id = question_topic_tag.tag_id WHERE question_id = ? AND tag_id IS NOT NULL ORDER BY tag_id";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String questionTag = rs.getString("topic_name");
                    list.add(questionTag);
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(SEO.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param con
     * @param qId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<SEOPojo> getTitleAndDescripiton(Connection con, int qId) throws SQLException, ClassNotFoundException, Exception {

        Pattern pattern = Pattern.compile(
                "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)"
                + "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov"
                + "|mil|biz|info|mobi|name|aero|jobs|museum"
                + "|travel|[a-z]{2}))(:[\\d]{1,5})?"
                + "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?"
                + "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?"
                + "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)"
                + "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?"
                + "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*"
                + "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

        String sql = "SELECT q.q_id AS q_id,q.question AS question,SUBSTRING(a.answer,1,500) AS answer,a.answer as imageLinkHtml "
                + "FROM question q LEFT JOIN answer a on q.q_id = a.q_id WHERE q.q_id = ? limit 1";

        List<SEOPojo> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int questionId = rs.getInt("q_id");
                    String question = rs.getString("question");//Title
                    String answer = rs.getString("answer");//Description
                    String imageLinkHtml = rs.getString("imageLinkHtml");//For the link image
                    if (imageLinkHtml != null && !imageLinkHtml.isEmpty()) {
                        String imageLinkResult = null;
                        Matcher matcher = pattern.matcher(imageLinkHtml);
                        boolean foundImage = false;
                        if (matcher.find()) {
                            foundImage = true;
                            imageLinkResult = matcher.group();
                        }
                        if (foundImage) {
                            list.add(new SEOPojo(questionId, question, answer, imageLinkResult));
                        } else {
                            list.add(new SEOPojo(questionId, question, answer));
                        }
                    } else {
                        list.add(new SEOPojo(questionId, question, answer));
                    }

                }
                return list;
            }

        } catch (SQLException msg) {
            Logger.getLogger(SEO.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
