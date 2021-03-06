/*
 * Copyright 2019 inquiryhere.com.
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
package com.admin.unasweredQuestion;

/**
 *
 * @author inquiryhere.com
 */
public class uQuestionPojo {

    int questionId;
    String question;
    String date;
    int postedById;
    String postedByName;

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
    public String getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public int getPostedById() {
        return postedById;
    }

    /**
     *
     * @return
     */
    public String getPostedByName() {
        return postedByName;
    }

    /**
     *
     * @param questionId
     * @param question
     * @param date
     * @param postedById
     * @param postedByName
     */
    public uQuestionPojo(int questionId, String question, String date, int postedById, String postedByName) {
        this.questionId = questionId;
        this.question = question;
        this.date = date;
        this.postedById = postedById;
        this.postedByName = postedByName;
    }

}
