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

import java.util.Objects;

/**
 *
 * @author AngryLion
 */
public class searchQuestionPojo {

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.questionId;
        hash = 79 * hash + Objects.hashCode(this.question);
        hash = 79 * hash + this.totalView;
        hash = 79 * hash + this.totalAnswer;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final searchQuestionPojo other = (searchQuestionPojo) obj;
        if (this.questionId != other.questionId) {
            return false;
        }
        if (this.totalView != other.totalView) {
            return false;
        }
        if (this.totalAnswer != other.totalAnswer) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        return true;
    }

    int questionId;
    String question;
    int totalView;
    int totalAnswer;

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
    public int getTotalAnswer() {
        return totalAnswer;
    }

    /**
     *
     * @param questionId
     * @param question
     * @param totalView
     * @param totalAnswer
     */
    public searchQuestionPojo(int questionId, String question, int totalView, int totalAnswer) {
        this.questionId = questionId;
        this.question = question;
        this.totalView = totalView;
        this.totalAnswer = totalAnswer;
    }
}
