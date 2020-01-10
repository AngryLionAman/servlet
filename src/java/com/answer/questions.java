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
package com.answer;

import com.index.indexPage;
import com.index.indexPageExtraFunction;
import com.index.recentQuestionPojo;
import com.string.validateInput;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.question.Question;
import com.question.getQuestion;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class questions extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, Exception {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        validateInput input = new validateInput();
        Question ques = new Question();
        SEO seo = new SEO();
        indexPage page = new indexPage();
        getAnswer answer = new getAnswer();
        getQuestion q = new getQuestion();
        indexPageExtraFunction function = new indexPageExtraFunction();

        String message = null;

        if (request.getAttribute("message") != null) {
            message = (String) request.getAttribute("message");
        }

        List<SEOPojo> titleAndDescripiton = null;
        List<String> questionTag = null;
        HashMap<Integer, String> questionTagWithId = null;
        List<recentQuestionPojo> question = null;
        List<getAnswerPojo> answerById = null;
        HashMap<Integer, String> relatedQuestionById = null;
        HashMap<Integer, String> randomQuestion = null;

        int questionId = 0;
        String gotException = null;

        try {
            try {
                randomQuestion = q.getRandomQuestion();
            } catch (ClassNotFoundException | SQLException msg) {
                Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, msg);
            }

            if (request.getParameter("Id") != null && !request.getParameter("Id").isEmpty()) {
                questionId = input.getInputInt(request.getParameter("Id"));
            } else if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                questionId = input.getInputInt(request.getParameter("id"));
            } else if (request.getAttribute("id") != null) {
                questionId = (int) request.getAttribute("id");
            }

            if (questionId != 0) {
                if (ques.IsQuestionPresentByQuestionId(questionId)) {

                    try {
                        titleAndDescripiton = seo.getTitleAndDescripiton(questionId);
                    } catch (Exception msg) {
                        Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, msg);
                    }

                    try {
                        questionTag = seo.getQuestionTag(questionId);
                    } catch (Exception msg) {
                        Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, msg);
                    }

                    try {
                        questionTagWithId = seo.getQuestionTagWithId(questionId);
                    } catch (Exception msg) {
                        Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, msg);
                    }

                    try {
                        relatedQuestionById = q.getRelatedQuestionById(questionId);
                    } catch (ClassNotFoundException | SQLException msg) {
                        Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, msg);
                    }

                    question = page.getQuestion(questionId);

                    answerById = answer.getAnswerById(questionId);

                    try {
                        function.updateQuestionView(questionId);
                    } catch (Exception msg) {
                        Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, msg);
                    }

                } else {
                    message = "Question id not found in database, please try search option";
                }
            } else {
                message = "Question id is zero, or you are hiting the invalid argumet";
            }
        } catch (Exception msg) {
            gotException = "Not null";
            message = "Got some unknown error. We already working on this, Please try agina or visit after some time";
            Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("gotException", gotException);
            request.setAttribute("message", message);

            request.setAttribute("questionId", questionId);
            request.setAttribute("titleAndDescripiton", titleAndDescripiton);
            request.setAttribute("questionTag", questionTag);
            request.setAttribute("questionTagWithId", questionTagWithId);
            request.setAttribute("question", question);
            request.setAttribute("answerById", answerById);
            request.setAttribute("relatedQuestionById", relatedQuestionById);
            request.setAttribute("randomQuestion", randomQuestion);

            request.getRequestDispatcher("Answer.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(questions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This is fucking servlet, it made my life hell. "
                + "it never let me do any task of my presnol life. my life is sucks";
    }// </editor-fold>

}
