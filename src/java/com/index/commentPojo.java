/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

/**
 *
 * @author inquiryhere.com
 */
public class commentPojo {

    /**
     *
     * @param commentId
     * @param comment
     * @param time
     * @param userId
     * @param userFullName
     * @param userUserName
     */
    public commentPojo(int commentId, String comment, String time, int userId, String userFullName, String userUserName) {
        this.commentId = commentId;
        this.comment = comment;
        this.time = time;
        this.userId = userId;
        this.userFullName = userFullName;
        this.userUserName = userUserName;
    }

    /**
     *
     * @return
     */
    public int getCommentId() {
        return commentId;
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
    public String getTime() {
        return time;
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
    public String getUserFullName() {
        return userFullName;
    }

    /**
     *
     * @return
     */
    public String getUserUserName() {
        return userUserName;
    }

    int commentId;
    String comment;
    String time;
    int userId;
    String userFullName;
    String userUserName;
}
