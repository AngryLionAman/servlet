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
package com.follow;

import com.notifications.CreateNotification;
import com.string.validateInput;
import java.io.IOException;
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
public class SaveFollowUserServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        validateInput input = new validateInput();
        UserFollow follow = new UserFollow();
        SaveFollowUserClassFile file = new SaveFollowUserClassFile();
        CreateNotification notification = new CreateNotification();

        String message = null;

        try {
            int userId = 0;
            try {
                userId = input.getInputInt(request.getParameter("userId"));
            } catch (Exception msg) {
                Logger.getLogger(SaveFollowUserServlet.class.getName()).log(Level.SEVERE, null, msg);
            }

            int followersId = 0;
            try {
                followersId = input.getInputInt(request.getParameter("followersId"));
            } catch (Exception msg) {
                Logger.getLogger(SaveFollowUserServlet.class.getName()).log(Level.SEVERE, null, msg);
            }

            String action = null;
            try {
                action = input.getInputString(request.getParameter("action"));//follow or followed
            } catch (Exception msg) {
                Logger.getLogger(SaveFollowUserServlet.class.getName()).log(Level.SEVERE, null, msg);
            }

            if (userId != 0 && followersId != 0 && action != null) {
                if (action.equalsIgnoreCase("followed")) {
                    try {
                        if (follow.IsUserFollowingByUserId(userId, followersId)) {
                            if (!file.UnfollowUser(userId, followersId)) {
                                message = "User unfollow successfully";
                            } else {
                                message = "User not unfollow successfully, Please try again";
                            }
                        } else {
                            message = "User clicked the unfollow action. this message mean user is already followed by active user but in database, data not mathced";
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(SaveFollowUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (action.equalsIgnoreCase("follow")) {
                    try {
                        if (!follow.IsUserFollowingByUserId(userId, followersId)) {
                            if (!file.FollowUser(userId, followersId)) {
                                if (!notification.userGetFollowed(userId, followersId, followersId)) {
                                    message = "User followed successfully, Notification sent";
                                } else {
                                    message = "Failed to send folowers notifiaction";
                                }
                            } else {
                                message = "user not followed successfully";
                            }
                        } else {
                            message = "User is already followed by current active user";
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(SaveFollowUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                message = "Unvalid argument, Please try again";
            }
        } catch (Exception msg) {
            Logger.getLogger(SaveFollowUserServlet.class.getName()).log(Level.SEVERE, null, msg);
        }

        //System.out.println("com.follow.SaveFollowUserServlet.doPost()" + message);
    }
}
