/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

/**
 *
 * @author inquiryhere.com
 */
public class saveUser {

    String userName;
    String email;
    int userId;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public int getUserId() {
        return userId;
    }

    public saveUser(String userName, String email, int userId) {
        this.userName = userName;
        this.email = email;
        this.userId = userId;
    }
    @Override
    public String toString(){
        return "[" + userName + " " + userId +" " + email + "]";
    }
}
