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
package com.notifications;

/**
 *
 * @author AngryLion
 */
public class notificationPojo {

    int comment_id;
    int question_id;
    int blog_id;
    String question;
    int notificationCreatedBy;//user Id
    String userFirstName;
    String notification_type;

    public notificationPojo(int comment_id, int question_id, int blog_id, String question, int notificationCreatedBy, String userFirstName, String notification_type) {
        this.comment_id = comment_id;
        this.question_id = question_id;
        this.blog_id = blog_id;
        this.question = question;
        this.notificationCreatedBy = notificationCreatedBy;
        this.userFirstName = userFirstName;
        this.notification_type = notification_type;
    }

    public int getComment_id() {
        return comment_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public String getQuestion() {
        return question;
    }

    public int getNotificationCreatedBy() {
        return notificationCreatedBy;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getNotification_type() {
        return notification_type;
    }

}
