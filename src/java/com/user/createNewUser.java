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
package com.user;

import com.string.ValidateWithRegularExpression;
import com.string.validateInput;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AngryLion
 */
public class createNewUser extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        validateInput input = new validateInput();
        saveNewUser newUser = new saveNewUser();
        SupportingFunction function = new SupportingFunction();
        ValidateWithRegularExpression expression = new ValidateWithRegularExpression();
        String message = null;
        String path = "signup.jsp";
        
        try{
            
            String fullName = input.getInputString(request.getParameter("firstname"));
            String email = input.getInputString(request.getParameter("email"));
            String password = request.getParameter("password");
            
            if (fullName == null || email == null || password == null) {
                message = "Input field can't be empty";
            } else {
                if (expression.validateFullName(fullName)
                        && expression.validatePassword(password)
                        && (expression.validateEamil(email) || expression.validateMobileNumber(email))) {
                    
                    if (!function.EmailIsAvaliabe(email)) {
                        if (!newUser.saveUser(fullName, email, password)) {
                            try {
                                Cookie usernameCookie = new Cookie("username-cookie", email);
                                Cookie passwordCookie = new Cookie("password-cookie", password);
                                usernameCookie.setMaxAge(24 * 60 * 60 * 100);
                                passwordCookie.setMaxAge(24 * 60 * 60 * 100);
                                response.addCookie(usernameCookie);
                                response.addCookie(passwordCookie);
                            } catch (Exception msg) {
                                //throw msg;  @beacuse cookies creation is not neccessary
                                Logger.getLogger(createNewUser.class.getName()).log(Level.SEVERE, null, msg);
                            }
                            //Creating session variable for the newuser
                            int userId = function.GetUserIdByEmail(email);
                            HttpSession session = request.getSession();
                            session.setAttribute("Session_id_of_user", userId);
                            
                            message = "Please select at least five topic to get the best result";
                            path = "FollowTopicAtAcountCreation";
                            /*
                            @if you don't use return statement the will got the exception error
                            @Exception error :- Cannot forward after response has been committed
                            @Referance :- https://stackoverflow.com/questions/2123514/java-lang-illegalstateexception-cannot-forward-sendredirect-create-session
                            */
                        } else {
                            message = "user not saved";
                        }
                    } else {
                        message = "Mail Id is already registred, please use another one";
                    }
                    
                } else {
                    message = "  <center>\n"
                            + "            <b style=\"color: red;\">We got some problem</b><br><br>\n"
                            + "            <b>1. May be you are putting the wrong email and email pattern</b><br><br>\n"
                            + "            <b>2. May be you are using the spacial character with full name</b><br><br>\n"
                            + "            <b>3. Your password length may be shorter then 6 character </b><br><br>\n"
                            + "            <a href=\"login.jsp?ref=nuser\">Click here to login</a><br><br>\n"
                            + "            <a href=\"signup.jsp?ref=nuser\">Click here for Sign up page</a><br><br>\n"
                            + "            <p>\n"
                            + "                Or it's seem like you are doing effort to break the site rule\n"
                            + "                    <br>Plese follow the procedure ,don't try to break the rule other wise your activity \n"
                            + "                    will be recorded for the monitoring purpose\n"
                            + "            </p>\n"
                            + "            </center>";
                }
            }
            
        } catch (SQLException | ClassNotFoundException msg) {
            Logger.getLogger(createNewUser.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
