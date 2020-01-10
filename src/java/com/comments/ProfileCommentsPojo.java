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
package com.comments;

import java.util.Date;

/**
 *
 * @author AngryLion
 */
public class ProfileCommentsPojo {

    int userId;
    String userType;
    String userName;
    String fullName;
    String comment;
    Date date;

    /**
     *
     * @param userId
     * @param userType
     * @param userName
     * @param fullName
     * @param comment
     * @param date
     */
    public ProfileCommentsPojo(int userId, String userType, String userName, String fullName, String comment, Date date) {
        this.userId = userId;
        this.userType = userType;
        this.userName = userName;
        this.fullName = fullName;
        this.comment = comment;
        this.date = date;
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
}
