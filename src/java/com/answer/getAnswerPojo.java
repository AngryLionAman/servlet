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

/**
 *
 * @author AngryLion
 */
public class getAnswerPojo {

    int userId;
    String userName;
    String fullName;
    String answer;
    int answerId;
    int totalView;
    int vote;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAnswer() {
        return answer;
    }

    public int getAnswerId() {
        return answerId;
    }

    public int getTotalView() {
        return totalView;
    }

    public int getVote() {
        return vote;
    }

    public getAnswerPojo(int userId, String userName, String fullName, String answer, int answerId, int totalView, int vote) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.answer = answer;
        this.answerId = answerId;
        this.totalView = totalView;
        this.vote = vote;
    }
}
