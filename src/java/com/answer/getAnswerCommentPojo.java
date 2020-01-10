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
public class getAnswerCommentPojo {

    int commentId;
    String comments;
    String date;
    int commentPostedById;
    String userName;
    String fullName;
    String userType;

    /**
     *
     * @return
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     *
     * @return
     */
    public String getComments() {
        return comments;
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
    public int getCommentPostedById() {
        return commentPostedById;
    }

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
     * @param commentId
     * @param comments
     * @param date
     * @param commentPostedById
     * @param userName
     * @param fullName
     * @param userType
     */

    public getAnswerCommentPojo(int commentId, String comments, String date, int commentPostedById, String userName, String fullName, String userType) {
        this.commentId = commentId;
        this.comments = comments;
        this.date = date;
        this.commentPostedById = commentPostedById;
        this.userName = userName;
        this.fullName = fullName;
        this.userType = userType;
    }
}
