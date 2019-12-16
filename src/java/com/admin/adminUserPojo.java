/*
 * Copyright 2019 inquiryhere.com.
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
package com.admin;

/**
 *
 * @author inquiryhere.com
 */
public class adminUserPojo {

    int userId;
    String userName;
    String fullName;
    String eMail;

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
    public String geteMail() {
        return eMail;
    }

    /**
     *
     * @param userId
     * @param userName
     * @param fullName
     * @param eMail
     */
    public adminUserPojo(int userId, String userName, String fullName, String eMail) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.eMail = eMail;
    }

}
