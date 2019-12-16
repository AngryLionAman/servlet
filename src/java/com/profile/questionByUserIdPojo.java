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

import java.util.Date;

/**
 *
 * @author AngryLion
 */
public class questionByUserIdPojo {

    int questionId;
    String question;
    int totalView;
    int questionPostedById;
    Date lastModifiedTime;
    int totalAnswer;

    /**
     *
     * @param questionId
     * @param question
     * @param totalView
     * @param questionPostedById
     * @param lastModifiedTime
     * @param totalAnswer
     */
    public questionByUserIdPojo(int questionId, String question, int totalView, int questionPostedById, Date lastModifiedTime, int totalAnswer) {
        this.questionId = questionId;
        this.question = question;
        this.totalView = totalView;
        this.questionPostedById = questionPostedById;
        this.lastModifiedTime = lastModifiedTime;
        this.totalAnswer = totalAnswer;
    }

    /**
     *
     * @return
     */
    public int getTotalAnswer() {
        return totalAnswer;
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
    public int getTotalView() {
        return totalView;
    }

    /**
     *
     * @return
     */
    public int getQuestionPostedById() {
        return questionPostedById;
    }

    /**
     *
     * @return
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

   
}
