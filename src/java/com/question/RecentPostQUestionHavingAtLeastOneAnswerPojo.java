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
package com.question;

import java.util.Date;

/**
 *
 * @author AngryLion
 */
public class RecentPostQUestionHavingAtLeastOneAnswerPojo {

    String userName;
    String fullName;
    String userType;
    String higherEdu;
    int userId;
    int questionId;
    String question;
    int questionView;
    int questionVote;
    Date lastModify;
    int ansCount;
    int days;

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @return
     */
    public String getHigherEdu() {
        return higherEdu;
    }

    /**
     *
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @return
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     *
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @return
     */
    public int getQuestionView() {
        return questionView;
    }

    /**
     *
     * @return
     */
    public int getQuestionVote() {
        return questionVote;
    }

    /**
     *
     * @return
     */
    public Date getLastModify() {
        return lastModify;
    }

    /**
     *
     * @return
     */
    public int getAnsCount() {
        return ansCount;
    }

    /**
     *
     * @return
     */
    public int getDays() {
        return days;
    }

    /**
     *
     * @param userName
     * @param fullName
     * @param userType
     * @param higherEdu
     * @param userId
     * @param questionId
     * @param question
     * @param questionView
     * @param questionVote
     * @param lastModify
     * @param ansCount
     * @param days
     */
    public RecentPostQUestionHavingAtLeastOneAnswerPojo(String userName, String fullName, String userType, String higherEdu, int userId, int questionId, String question, int questionView, int questionVote, Date lastModify, int ansCount, int days) {
        this.userName = userName;
        this.fullName = fullName;
        this.userType = userType;
        this.higherEdu = higherEdu;
        this.userId = userId;
        this.questionId = questionId;
        this.question = question;
        this.questionView = questionView;
        this.questionVote = questionVote;
        this.lastModify = lastModify;
        this.ansCount = ansCount;
        this.days = days;
    }
}
