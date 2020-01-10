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
package com.search;

import com.index.topicDetals;
import com.index.topicPojo;
import com.string.validateInput;
import java.io.IOException;
import java.util.List;
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
public class search extends HttpServlet {

    /**
     *
     * @param tab
     * @return
     */
    protected static String Tab(String tab) {
        validateInput input = new validateInput();
        if (tab == null) {
            return "default";
        } else if (tab.isEmpty()) {
            return "default";
        } else if ("".equals(tab.trim())) {
            return "default";
        } else {
            return input.getInputString(tab);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        validateInput input = new validateInput();
        SearchClassFile file = new SearchClassFile();
        topicDetals detals = new topicDetals();

        String message = null;
        String gotException = null;
        String query = null;

        List<topicPojo> randomTopic = null;
        List<CountSearchPojo> GetCountRowSearch = null;
        List<searchQuestionPojo> questionByQueryAndLimit = null;
        List<searchAnswerPojo> answerByQuearyAndLimit = null;
        List<searchTopicPojo> topicByQuearyAndLimit = null;
        List<searchUserPojo> userByQuearyAndLimit = null;

        try {

            String tab = "default";

            try {
                tab = Tab(request.getParameter("tab"));
            } catch (Exception msg) {
                Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
            }

            try {
                query = input.getInputString(request.getParameter("q"));
            } catch (Exception msg) {
                Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
            }

            try {
                randomTopic = detals.randomTopic(50);
            } catch (Exception msg) {
                Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
            }

            if (query != null) {

                try {
                    GetCountRowSearch = file.GetCountRowSearch(query);
                } catch (Exception msg) {
                    Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
                }
                switch (tab) {
                    case "question":
                        try {
                            questionByQueryAndLimit = file.getQuestionByQueryAndLimit(query, 0);
                        } catch (Exception msg) {
                            Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
                        }
                        break;

                    case "answer":
                        try {
                            answerByQuearyAndLimit = file.getAnswerByQuearyAndLimit(query, 0);
                        } catch (Exception msg) {
                            Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
                        }
                        break;

                    case "topic":
                        try {
                            topicByQuearyAndLimit = file.getTopicByQuearyAndLimit(query, 0);
                        } catch (Exception msg) {
                            Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
                        }
                        break;

                    case "profile":
                        try {
                            userByQuearyAndLimit = file.getUserByQuearyAndLimit(query, 0);
                        } catch (Exception msg) {
                            Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
                        }
                        break;

                    default:

                        try {
                            questionByQueryAndLimit = file.getQuestionByQueryAndLimit(query, 5);
                        } catch (Exception msg) {
                            Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
                        }

                        try {
                            answerByQuearyAndLimit = file.getAnswerByQuearyAndLimit(query, 5);
                        } catch (Exception msg) {
                            Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
                        }

                        try {
                            topicByQuearyAndLimit = file.getTopicByQuearyAndLimit(query, 5);
                        } catch (Exception msg) {
                            Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
                        }

                        try {
                            userByQuearyAndLimit = file.getUserByQuearyAndLimit(query, 5);
                        } catch (Exception msg) {
                            Logger.getLogger(search.class.getName()).log(Level.SEVERE, tab, msg);
                        }
                        break;
                }
            } else {
                message = "Empty queary, Please search something..";
            }
        } catch (Exception msg) {
            gotException = "not null";
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("query", query);
            request.setAttribute("randomTopic", randomTopic);
            request.setAttribute("GetCountRowSearch", GetCountRowSearch);
            request.setAttribute("questionByQueryAndLimit", questionByQueryAndLimit);
            request.setAttribute("answerByQuearyAndLimit", answerByQuearyAndLimit);
            request.setAttribute("topicByQuearyAndLimit", topicByQuearyAndLimit);
            request.setAttribute("userByQuearyAndLimit", userByQuearyAndLimit);

            request.setAttribute("message", message);
            request.setAttribute("gotException", gotException);

            request.getRequestDispatcher("search.jsp").forward(request, response);
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
        } catch (Exception ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
