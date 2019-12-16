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

        int questionId = 0;
        if (request.getParameter("Id") != null && !request.getParameter("Id").isEmpty()) {
            questionId = input.getInputInt(request.getParameter("Id"));
        } else if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            questionId = input.getInputInt(request.getParameter("id"));
        }else if(request.getAttribute("id") != null){
            questionId = (int) request.getAttribute("id");
        }

        String message;
        
        if (questionId != 0) {
            if (ques.IsQuestionPresentByQuestionId(questionId)) {
                List<SEOPojo> titleAndDescripiton = seo.getTitleAndDescripiton(questionId);
                List<String> questionTag = seo.getQuestionTag(questionId);
                HashMap<Integer, String> questionTagWithId = seo.getQuestionTagWithId(questionId);
                List<recentQuestionPojo> question = page.getQuestion(questionId);
                List<getAnswerPojo> answerById = answer.getAnswerById(questionId);
                HashMap<Integer, String> relatedQuestionById = q.getRelatedQuestionById(questionId);
                HashMap<Integer, String> randomQuestion = q.getRandomQuestion();

                request.setAttribute("questionId", questionId);
                request.setAttribute("titleAndDescripiton", titleAndDescripiton);
                request.setAttribute("questionTag", questionTag);
                request.setAttribute("questionTagWithId", questionTagWithId);
                request.setAttribute("question", question);
                request.setAttribute("answerById", answerById);
                request.setAttribute("relatedQuestionById", relatedQuestionById);
                request.setAttribute("randomQuestion", randomQuestion);

                request.getRequestDispatcher("Answer.jsp").forward(request, response);
                return;
            } else {
                message = "Question id not found in database, please try search option";
            }
        } else {
            message = "Question id is zero, or you are hiting the invalid argumet";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("Error404.jsp").forward(request, response);
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
