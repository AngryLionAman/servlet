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
package com.search;

/**
 *
 * @author AngryLion
 */
public class searchAnswerPojo {

    int questionId;
    String question;
    String answer;
    int totalViewOfQuestion;
    int totalAnswerOfQuestion;

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getTotalViewOfQuestion() {
        return totalViewOfQuestion;
    }

    public int getTotalAnswerOfQuestion() {
        return totalAnswerOfQuestion;
    }

    public searchAnswerPojo(int questionId, String question, String answer, int totalViewOfQuestion, int totalAnswerOfQuestion) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.totalViewOfQuestion = totalViewOfQuestion;
        this.totalAnswerOfQuestion = totalAnswerOfQuestion;
    }
}
