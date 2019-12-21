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
package com.modalset;

import com.string.WordFormating;
import com.string.validateInput;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "SaveModalSetQuestion", urlPatterns = {"/SaveModalSetQuestion"})
public class SaveModalSetQuestion extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        validateInput input = new validateInput();
        ModelSetClass msc = new ModelSetClass();
        WordFormating wf = new WordFormating();

        String message = null;

        try {
            String exam_of = wf.RemoveWhiteSpace(input.getInputString(request.getParameter("exam_of")));
            int set_no = input.getInputInt(request.getParameter("set_no"));
            String question = input.getInputString(request.getParameter("question"));
            String correctAnswer = wf.RemoveWhiteSpace(input.getInputString(request.getParameter("correctAnswer")));
            String[] opt = request.getParameterValues("option[]");

            if (exam_of != null && set_no != 0 && question != null && correctAnswer != null) {
                if (!msc.saveSetQuestion(exam_of, set_no, question, correctAnswer)) {
                    int questionIdByModelSetQuestion = msc.getQuestionIdByModelSetQuestion(question);
                    if (questionIdByModelSetQuestion != 0) {
                        if (opt != null) {
                            if (msc.saveOptionalAnswer(questionIdByModelSetQuestion, opt)) {
                                message = "Question and option successfully posted";
                            } else {
                                message = "Question has been saved but tag option not integrated with this question, Please contact administrator";
                            }
                        } else {
                            message = "Found the question id database but option is null. Please enter the valid input";
                        }
                    } else {
                        message = "Question has been saved in database but not able to find in database, please contact administrator";
                    }
                } else {
                    message = "We got all argumet but Question not saved in database";
                }
            } else {
                message = "Some option is empty, Please fill the all the option";
            }
        } catch (ClassNotFoundException | SQLException msg) {
            Logger.getLogger(SaveModalSetQuestion.class.getName()).log(Level.SEVERE, message, msg);
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("modalset.jsp").forward(request, response);
        }
    }
}
