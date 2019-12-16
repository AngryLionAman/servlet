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
public class recentQuestionPojo {

    int totalView;
    String date;
    int days;
    int questionId;
    String question;
    int vote;
    String fullName;
    String userName;
    String userType;
    String higherEdu;
    int userId;
    int totalAnswer;

    /**
     *
     * @return
     */
    public int getTotalView() {
        return totalView;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public int getDays() {
        return days;
    }

    /**
     *
     * @return
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     *
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @return
     */
    public int getVote() {
        return vote;
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
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getUserType() {
        return userType;
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
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @return
     */
    public int getTotalAnswer() {
        return totalAnswer;
    }

    /**
     *
     * @param totalView
     * @param date
     * @param days
     * @param questionId
     * @param question
     * @param vote
     * @param fullName
     * @param userName
     * @param userType
     * @param higherEdu
     * @param userId
     * @param totalAnswer
     */
    public recentQuestionPojo(int totalView, String date, int days, int questionId, String question, int vote, String fullName, String userName, String userType, String higherEdu, int userId, int totalAnswer) {
        this.totalView = totalView;
        this.date = date;
        this.days = days;
        this.questionId = questionId;
        this.question = question;
        this.vote = vote;
        this.fullName = fullName;
        this.userName = userName;
        this.userType = userType;
        this.higherEdu = higherEdu;
        this.userId = userId;
        this.totalAnswer = totalAnswer;
    }
}
