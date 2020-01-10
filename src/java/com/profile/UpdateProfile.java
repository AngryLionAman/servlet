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
package com.profile;

import com.string.validateInput;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AngryLion
 */
@WebServlet(name = "UpdateProfile", urlPatterns = {"/UpdateProfile"})
public class UpdateProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        validateInput input = new validateInput();
        EditUserProfileClassFile classFile = new EditUserProfileClassFile();

        String message = null;
        int user_id = 0;

        String path = "profile";
        try {

            user_id = input.getInputInt(request.getParameter("user_id"));
            //String fullname = input.getInputString(request.getParameter("fullname"));
            //String email = input.getInputString(request.getParameter("email"));
            String HigherQualification = input.getInputString(request.getParameter("HigherQualification"));
            String BestAchievement = input.getInputString(request.getParameter("BestAchievement"));
            String bio = input.getInputString(request.getParameter("bio"));

            if (user_id != 0) {
                if (classFile.saveUserProfile(user_id, HigherQualification, BestAchievement, bio)) {
                    message = "Profile updated successfully";
                } else {
                    message = "Profile update operation failed";
                }
            }

        } catch (Exception msg) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, msg);
        }finally{
            request.setAttribute("message", message);
            request.setAttribute("id", user_id);
            
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

}
