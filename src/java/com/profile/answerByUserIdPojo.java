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
public class answerByUserIdPojo {

    int questionId;
    String question;
    String shortAnswer;
    int answerid;
    int answerById;
    Date lastModifiedTime;
    int totalView;

    /**
     *
     * @param questionId
     * @param question
     * @param shortAnswer
     * @param answerid
     * @param answerById
     * @param lastModifiedTime
     * @param totalView
     */
    public answerByUserIdPojo(int questionId, String question, String shortAnswer, int answerid, int answerById, Date lastModifiedTime, int totalView) {
        this.questionId = questionId;
        this.question = question;
        this.shortAnswer = shortAnswer;
        this.answerid = answerid;
        this.answerById = answerById;
        this.lastModifiedTime = lastModifiedTime;
        this.totalView = totalView;
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
    public String getShortAnswer() {
        return shortAnswer;
    }

    /**
     *
     * @return
     */
    public int getAnswerid() {
        return answerid;
    }

    /**
     *
     * @return
     */
    public int getAnswerById() {
        return answerById;
    }

    /**
     *
     * @return
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

}
