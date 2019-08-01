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

    public commentPojo(int commentId, String comment, String time, int userId, String userFullName, String userUserName) {
        this.commentId = commentId;
        this.comment = comment;
        this.time = time;
        this.userId = userId;
        this.userFullName = userFullName;
        this.userUserName = userUserName;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getTime() {
        return time;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

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
