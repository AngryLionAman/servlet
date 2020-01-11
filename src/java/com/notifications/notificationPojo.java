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
    int user_id;
    int notificationCreatedBy;
    int content_id;
    String notification_type;
    boolean seen;

    public int getComment_id() {
        return comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getNotificationCreatedBy() {
        return notificationCreatedBy;
    }

    public int getContent_id() {
        return content_id;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public boolean isSeen() {
        return seen;
    }

    public notificationPojo(int comment_id, int user_id, int notificationCreatedBy, int content_id, String notification_type, boolean seen) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.notificationCreatedBy = notificationCreatedBy;
        this.content_id = content_id;
        this.notification_type = notification_type;
        this.seen = seen;
    }

}
