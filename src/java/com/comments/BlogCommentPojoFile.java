/*
 * Copyright 2020 AngryLion.
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
package com.comments;

import java.sql.Date;

/**
 *
 * @author AngryLion
 */
public class BlogCommentPojoFile {

    String comment;
    Date date;
    int userId;
    String userName;
    String fullName;
    String userType;

    /**
     *
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public int getUserId() {
        return userId;
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
     * @param comment
     * @param date
     * @param userId
     * @param userName
     * @param fullName
     * @param userType
     */
    public BlogCommentPojoFile(String comment, Date date, int userId, String userName, String fullName, String userType) {
        this.comment = comment;
        this.date = date;
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.userType = userType;
    }

}
