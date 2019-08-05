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
   int questionId;
   String question;
   int vote;
   String fullName;
   String higherEdu;
   int userId;

    public int getTotalView() {
        return totalView;
    }

    public String getDate() {
        return date;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public int getVote() {
        return vote;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHigherEdu() {
        return higherEdu;
    }

    public int getUserId() {
        return userId;
    }

    public recentQuestionPojo(int totalView, String date, int questionId, String question, int vote, String fullName, String higherEdu, int userId) {
        this.totalView = totalView;
        this.date = date;
        this.questionId = questionId;
        this.question = question;
        this.vote = vote;
        this.fullName = fullName;
        this.higherEdu = higherEdu;
        this.userId = userId;
    }
}
