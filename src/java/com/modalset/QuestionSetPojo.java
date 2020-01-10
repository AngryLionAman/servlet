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
package com.modalset;

/**
 *
 * @author AngryLion
 */
public class QuestionSetPojo {

    int questionId;
    String question;
    String correct_ans;
    String selected_ans;

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
    public String getCorrect_ans() {
        return correct_ans;
    }

    /**
     *
     * @return
     */
    public String getSelected_ans() {
        return selected_ans;
    }

    /**
     *
     * @param questionId
     * @param question
     * @param correct_ans
     * @param selected_ans
     */
    public QuestionSetPojo(int questionId, String question, String correct_ans, String selected_ans) {
        this.questionId = questionId;
        this.question = question;
        this.correct_ans = correct_ans;
        this.selected_ans = selected_ans;
    }
}
