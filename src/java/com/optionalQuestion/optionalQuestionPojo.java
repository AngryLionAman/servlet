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
package com.optionalQuestion;

/**
 *
 * @author AngryLion
 */
public class optionalQuestionPojo {

    int id;
    String question;
    String correctAnswer;
    String onTopic;
    int postedBy;
    int totalOption;

    /**
     *
     * @return
     */
    public int getId() {
        return id;
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
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     *
     * @return
     */
    public String getOnTopic() {
        return onTopic;
    }

    /**
     *
     * @return
     */
    public int getPostedBy() {
        return postedBy;
    }

    /**
     *
     * @return
     */
    public int getTotalOption() {
        return totalOption;
    }

    /**
     *
     * @param id
     * @param question
     * @param correctAnswer
     * @param onTopic
     * @param postedBy
     * @param totalOption
     */
    public optionalQuestionPojo(int id, String question, String correctAnswer, String onTopic, int postedBy, int totalOption) {
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.onTopic = onTopic;
        this.postedBy = postedBy;
        this.totalOption = totalOption;
    }
}
