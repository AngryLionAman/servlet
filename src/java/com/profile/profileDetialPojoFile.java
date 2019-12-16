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
package com.profile;

/**
 *
 * @author AngryLion
 */
public class profileDetialPojoFile {

    int userId;
    String username;
    String fullName;
    String email;
    int email_s;
    String higherEdu;
    String bestAchivement;
    String bio;
    String imagePath;
    int totalView;

    /**
     *
     * @param userId
     * @param username
     * @param fullName
     * @param email
     * @param email_s
     * @param higherEdu
     * @param bestAchivement
     * @param bio
     * @param imagePath
     * @param totalView
     */
    public profileDetialPojoFile(int userId, String username, String fullName, String email, int email_s, String higherEdu, String bestAchivement, String bio, String imagePath, int totalView) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.email_s = email_s;
        this.higherEdu = higherEdu;
        this.bestAchivement = bestAchivement;
        this.bio = bio;
        this.imagePath = imagePath;
        this.totalView = totalView;
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
    public String getUsername() {
        return username;
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
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public int getEmail_s() {
        return email_s;
    }

    /**
     *
     * @return
     */
    public String getHigherEdu() {
        return higherEdu;
    }

    /**
     *
     * @return
     */
    public String getBestAchivement() {
        return bestAchivement;
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
    public String getImagePath() {
        return imagePath;
    }

    /**
     *
     * @return
     */
    public int getTotalView() {
        return totalView;
    }
}
