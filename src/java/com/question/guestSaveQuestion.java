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
package com.question;

import com.question.user.questionClass;
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
public class guestSaveQuestion extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, Exception {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        validateInput input = new validateInput();
        // GuestUser guestUser = new GuestUser();
        //newUser user = new newUser();
        questionClass funcation = new questionClass();

        //String guestFullName = input.getInputString(request.getParameter("guestName"));
        //String guestEmail = input.getInputString(request.getParameter("guestEmail"));
        String question = input.getInputString(request.getParameter("question"));
        String tag_of_question = input.getInputString(request.getParameter("tag_of_question"));

        /*
         * Got the null pointer excpeiton at creating the user name
         * Work under process
         */
 /*
        String guestUserName;
        if (guestFullName != null) {
            guestUserName = guestUser.GenreateGuestName(guestFullName.replaceAll(" ", "").toLowerCase());
        } else {
            guestUserName = guestUser.GenreateGuestName("GuestUser");
        }*/
        String message;
        int guestId;
        int questionId = 0;
        if (question != null) {

            guestId = 291;//(Selected this id because user name of this id is 'GuestUser') user.saveNewGuestUser(guestUserName, guestFullName, guestEmail, "guest");
            if (guestId != 0) {
                if (!funcation.SaveQuestionByQuestionAndTagandUserId(guestId, question)) {
                    questionId = funcation.GetQuestionIdByQuestion(question);
                    if (tag_of_question != null) {
                        if (questionId != 0) {
                            if (!funcation.SaveTag(tag_of_question)) {
                                if (!funcation.SaveTagWithQuestionId(questionId, tag_of_question)) {
                                    message = "Question has been successfully Posted";
                                } else {
                                    message = "Tag saved but not integrated with the question id, Please inform us at contact us from";
                                }
                            } else {
                                message = "Tag not saved in the database, Please inform us at contact us form";
                            }
                        } else {
                            message = "Question has been save but question not found in database";
                        }
                    } else {
                        message = "Question has been saved but tag is empty";
                    }
                } else {
                    message = "question not saved";
                }
            } else {
                message = "Sorry user.Got some internal problem to getting the guest id, Please try again";
            }

        } else {
            message = "Question can't be empty";
        }
        request.setAttribute("message", message);
        request.setAttribute("id", questionId);
        
        request.getRequestDispatcher("questions").forward(request, response);
    }

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
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(guestSaveQuestion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(guestSaveQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(guestSaveQuestion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(guestSaveQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
