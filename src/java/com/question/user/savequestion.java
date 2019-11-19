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
package com.question.user;

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
public class savequestion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validateInput input = new validateInput();

        int userId = input.getInputInt(request.getParameter("userid"));

        String question = input.getInputString(request.getParameter("question"));

        String tag_of_question = input.getInputString(request.getParameter("tag_of_question"));

        String message = null;

        questionClass funcation = new questionClass();
        int questionId = 0;

        if (userId != 0 && question != null && tag_of_question != null) {
            try {
                if (!funcation.SaveQuestionByQuestionAndTagandUserId(userId, question)) {
                    //Get the question id
                    questionId = funcation.GetQuestionIdByQuestion(question);
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
                    message = "question not saved";
                }
            } catch (SQLException ex) {
                Logger.getLogger(savequestion.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                // Some Dode stuf
            }
        } else {
            message = "Got some problme, Please try again";
        }
        request.setAttribute("message", message);
        request.setAttribute("Id", questionId);
        request.getRequestDispatcher("Answer.jsp").forward(request, response);
    }
}
