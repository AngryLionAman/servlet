/*
 * Copyright 2020 AngryLion.
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
package com.security;

import com.connect.DatabaseConnection;
import com.string.validateInput;
import com.user.SupportingFunction;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AngryLion
 */
public class settings extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String message = null;
        String path = "settings.jsp";
        String email = null;
        int mobileNumberByuserId = 0;
        boolean emailDisplayStatus = false;

        try {

            DatabaseConnection connection = new DatabaseConnection();

            validateInput input = new validateInput();
            SupportingFunction function = new SupportingFunction();
            OperationWithMobileNumber mobileNumber = new OperationWithMobileNumber();
            UserProfilestatus profilestatus = new UserProfilestatus();

            String action = input.getInputString(request.getParameter("action"));
            int userId = input.getInputInt(request.getParameter("session_userid"));
            String tab = input.getInputString(request.getParameter("tab"));

            if (tab == null) {
                tab = "Account";
            }

            if (action != null && userId != 0) {
                try (Connection con = DatabaseConnection.makeConnection()) {

                    switch (tab) {
                        case "Account":
                            email = function.getEmailbyUserId(con, userId);
                            mobileNumberByuserId = mobileNumber.getMobileNumberByuserId(con, userId);
                            break;
                        case "Privacy":
                            emailDisplayStatus = profilestatus.getEmailDisplayStatus(con, userId);
                            break;
                        case "Notification":
                            break;
                        default:
                            break;
                    }

                    switch (action) {
                        case "addEmail":
                            String newEmail = input.getInputString(request.getParameter("newEmail"));
                            if (newEmail != null) {
                                OperationWithEmail email1 = new OperationWithEmail();
                                if (!email1.isEmailAvailabe(con, newEmail)) {// New list is not added of email
                                    if (!email1.addEmail(con, userId, newEmail)) {
                                        message = "Email successfully added";
                                    } else {
                                        message = "Email not added, please try again";
                                    }
                                } else {
                                    message = "Email is already available, Please choose another email";
                                }
                            } else {
                                message = "Email id not found";
                            }
                            break;
                        case "chagePassword":
                            String oldPassword = input.getInputString(request.getParameter("oldPassword"));
                            String newPassword = input.getInputString(request.getParameter("newPassword"));
                            String ConfirmNewPassword = input.getInputString(request.getParameter("confirmNewPassword"));
                            if (oldPassword != null && newPassword != null && ConfirmNewPassword != null) {
                                OperationWithPassword operationWithPassword = new OperationWithPassword();
                                if (operationWithPassword.isPasswordMatched(con, oldPassword, userId)) {
                                    if (newPassword.equals(ConfirmNewPassword)) {
                                        if (operationWithPassword.changePassword(con, userId, newPassword)) {
                                            message = "Password Chnaged Sucessfully";
                                        } else {
                                            message = "Password not changed, please try again";
                                        }
                                    } else {
                                        message = "New password not matched";
                                    }
                                } else {
                                    message = "Old password is wrong";
                                }
                            } else {
                                message = "Input field is empty";
                            }
                            break;
                        case "addPhone":
                            String telNum = input.getInputString(request.getParameter("telNum"));
                            if (telNum != null) {

                                if (!mobileNumber.isMobileNumberAvaliable(con, telNum)) {
                                    if (mobileNumber.addMobileNumber(con, userId, telNum)) {
                                        message = "Mobile number is added sucessfully";
                                    } else {
                                        message = "Mobile number saving operation failed, Please try agian";
                                    }
                                } else {
                                    message = "Mobile Number is already available, Please choose another one";
                                }
                            } else {
                                message = "Mobile Number is empty";
                            }
                            break;
                    }

                } catch (Exception msg) {
                    Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, msg);
                }
            } else {
                message = "You are not login or Action not detecated, or hitting the wrong url";
                path = "Error404.jsp";
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.setAttribute("emailDisplayStatus", emailDisplayStatus);
            request.setAttribute("mobileNumberByuserId", mobileNumberByuserId);
            request.setAttribute("email", email);
            request.setAttribute("message", message);
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

}
