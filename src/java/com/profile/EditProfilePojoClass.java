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
package com.profile;

/**
 *
 * @author AngryLion
 */
public class EditProfilePojoClass {

    int userId;
    String userName;
    String fullName;
    String email;
    String higher_edu;
    String best_achievement;
    String bio;
    String imagePath;

    public EditProfilePojoClass(int userId, String userName, String fullName, String email, String higher_edu, String best_achievement, String bio, String imagePath) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.higher_edu = higher_edu;
        this.best_achievement = best_achievement;
        this.bio = bio;
        this.imagePath = imagePath;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getHigher_edu() {
        return higher_edu;
    }

    public String getBest_achievement() {
        return best_achievement;
    }

    public String getBio() {
        return bio;
    }

    public String getImagePath() {
        return imagePath;
    }
}
