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
package com.admin.approval;

import java.util.Date;

/**
 *
 * @author AngryLion
 */
public class QuestionForApprovalPojo {

    int old_question_id;
    String old_question;
    int new_question_id;
    String new_question;
    boolean approve_by_user;
    boolean approve_by_admin;
    boolean rejected_by_user;
    boolean rejected_by_admin;
    String message;
    Date date;

    /**
     *
     * @param old_question_id
     * @param old_question
     * @param new_question_id
     * @param new_question
     * @param approve_by_user
     * @param approve_by_admin
     * @param rejected_by_user
     * @param rejected_by_admin
     * @param message
     * @param date
     */
    public QuestionForApprovalPojo(int old_question_id, String old_question, int new_question_id, String new_question, boolean approve_by_user, boolean approve_by_admin, boolean rejected_by_user, boolean rejected_by_admin, String message, Date date) {
        this.old_question_id = old_question_id;
        this.old_question = old_question;
        this.new_question_id = new_question_id;
        this.new_question = new_question;
        this.approve_by_user = approve_by_user;
        this.approve_by_admin = approve_by_admin;
        this.rejected_by_user = rejected_by_user;
        this.rejected_by_admin = rejected_by_admin;
        this.message = message;
        this.date = date;
    }

    /**
     *
     * @return
     */
    public int getOld_question_id() {
        return old_question_id;
    }

    /**
     *
     * @return
     */
    public String getOld_question() {
        return old_question;
    }

    /**
     *
     * @return
     */
    public int getNew_question_id() {
        return new_question_id;
    }

    /**
     *
     * @return
     */
    public String getNew_question() {
        return new_question;
    }

    /**
     *
     * @return
     */
    public boolean isApprove_by_user() {
        return approve_by_user;
    }

    /**
     *
     * @return
     */
    public boolean isApprove_by_admin() {
        return approve_by_admin;
    }

    /**
     *
     * @return
     */
    public boolean isRejected_by_user() {
        return rejected_by_user;
    }

    /**
     *
     * @return
     */
    public boolean isRejected_by_admin() {
        return rejected_by_admin;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }
}
