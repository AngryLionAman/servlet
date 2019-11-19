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
package com.blog;

/**
 *
 * @author AngryLion
 */
public class blogPojoById {

    int uniqueId;
    String subject;
    String desc;
    int view;
    int userId;
    String userName;
    String fullName;

    public blogPojoById(int uniqueId, String subject, String desc, int view, int userId, String userName, String fullName) {
        this.uniqueId = uniqueId;
        this.subject = subject;
        this.desc = desc;
        this.view = view;
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getSubject() {
        return subject;
    }

    public String getDesc() {
        return desc;
    }

    public int getView() {
        return view;
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

}
