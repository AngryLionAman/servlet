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
public class searchUserPojo {

    int userId;
    String userName;
    String userFullName;
    String bio;
    String imageLink;
    int totalFollowers;

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
    public String getUserFullName() {
        return userFullName;
    }

    /**
     *
     * @return
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @return
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     *
     * @return
     */
    public int getTotalFollowers() {
        return totalFollowers;
    }

    /**
     *
     * @param userId
     * @param userName
     * @param userFullName
     * @param bio
     * @param imageLink
     * @param totalFollowers
     */
    public searchUserPojo(int userId, String userName, String userFullName, String bio, String imageLink, int totalFollowers) {
        this.userId = userId;
        this.userName = userName;
        this.userFullName = userFullName;
        this.bio = bio;
        this.imageLink = imageLink;
        this.totalFollowers = totalFollowers;
    }
}
