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

/**
 *
 * @author AngryLion
 */
public class QuestionForApprovalPojo {

    int oldQuestionId;
    String oldQuestion;
    int newQuestionId;
    String newQuestion;

    /**
     *
     * @param oldQuestionId
     * @param oldQuestion
     * @param newQuestionId
     * @param newQuestion
     */
    public QuestionForApprovalPojo(int oldQuestionId, String oldQuestion, int newQuestionId, String newQuestion) {
        this.oldQuestionId = oldQuestionId;
        this.oldQuestion = oldQuestion;
        this.newQuestionId = newQuestionId;
        this.newQuestion = newQuestion;
    }

    /**
     *
     * @return
     */
    public int getOldQuestionId() {
        return oldQuestionId;
    }

    /**
     *
     * @return
     */
    public String getOldQuestion() {
        return oldQuestion;
    }

    /**
     *
     * @return
     */
    public int getNewQuestionId() {
        return newQuestionId;
    }

    /**
     *
     * @return
     */
    public String getNewQuestion() {
        return newQuestion;
    }
}
