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
package com.index;

import com.advertise.displayAds;
import com.advertise.displayAdsPojo;
import com.fun.FunHelpingFunction;
import com.profile.profile;
import com.question.GetAllQuestionClass;
import com.question.Question;
import com.question.RecentPostQUestionHavingAtLeastOneAnswerPojo;
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
public class index extends HttpServlet {

    /**
     *
     * @param pageNo
     * @return
     */
    protected static int getInput(String pageNo) {
        int pageno = 1;
        if (pageNo == null) {
            return 1;
        }
        if (pageNo.isEmpty()) {
            return 1;
        }
        if (!pageNo.isEmpty()) {
            pageno = Integer.parseInt(pageNo);
        }
        return pageno;
    }

    /**
     *
     * @param tab
     * @return
     */
    protected static String Tab(String tab) {
        validateInput input = new validateInput();
        if (tab == null) {
            return "recent";
        } else if (tab.isEmpty()) {
            return "recent";
        } else if ("".equals(tab.trim())) {
            return "recent";
        } else {
            return input.getInputString(tab);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        indexPage page = new indexPage();
        Question question = new Question();
        GetAllQuestionClass allQuestionClass = new GetAllQuestionClass();
        profile user = new profile();
        topicDetals detals = new topicDetals();
        displayAds ads = new displayAds();
        FunHelpingFunction function = new FunHelpingFunction();

        String tab = Tab(request.getParameter("tab"));
        int pageNo = getInput(request.getParameter("p"));

        // List<recentQuestionPojo> recentPostQuestion = null;
        List<recentQuestionPojo> relatedQuestion = null;
        List<recentQuestionPojo> UnAnsweredQuestion = null;

        List<RecentPostQUestionHavingAtLeastOneAnswerPojo> RecentPostQUestionHavingAtLeastOneAnswer = null;

        List<recentQuestionPojo> AllQuestion = null;
        int TotalNoOfPage = 0;

        List<topicPojo> userFollowedTopic = null;
        List<topicPojo> randomTopic = null;

        int userId = user.GetUserId(request);
        if (userId != 0) {
            userFollowedTopic = detals.userFollowedTopic(userId);
        } else {
            randomTopic = detals.randomTopic(15);
        }

        List<displayAdsPojo> displayRandomAds = null;

        try {
            displayRandomAds = ads.displayRandomAds();
        } catch (Exception msg) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, msg);
        }

        List<String> CategoryDetail = null;
        try {
            CategoryDetail = function.getFunCategory();
        } catch (Exception msg) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, msg);
        }

        switch (tab) {
            case "recent":
                RecentPostQUestionHavingAtLeastOneAnswer = question.RecentPostQUestionHavingAtLeastOneAnswer();
                //recentPostQuestion = page.recentPostQuestion();
                break;

            case "allquestion":
                AllQuestion = allQuestionClass.AllQuestion(pageNo);
                TotalNoOfPage = allQuestionClass.TotalNoOfPage();
                break;

            case "unanswered":
                UnAnsweredQuestion = question.UnAnsweredQuestion();
                break;

            case "related":

                if (userId != 0) {
                    relatedQuestion = page.relatedQuestion(userId);
                } else {
                    String message = "Dear User, we are really sorry for this problem."
                            + "<br>This problem occurred due to following reasong.."
                            + "<br>After logout, you are trying to access this url."
                            + "<br>You are hitting the invalid argument."
                            + "<br>If you are not doing wrong and still getting this message. So, Please contect to administrator.";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("Error404.jsp").forward(request, response);
                    return;
                }
                break;

            default:
                RecentPostQUestionHavingAtLeastOneAnswer = question.RecentPostQUestionHavingAtLeastOneAnswer();
                break;

        }

        request.setAttribute("recentPostQuestion", RecentPostQUestionHavingAtLeastOneAnswer);
        request.setAttribute("relatedQuestion", relatedQuestion);
        request.setAttribute("userFollowedTopic", userFollowedTopic);
        request.setAttribute("randomTopic", randomTopic);
        request.setAttribute("UnAnsweredQuestion", UnAnsweredQuestion);

        request.setAttribute("AllQuestion", AllQuestion);
        request.setAttribute("totalNumberOfpage", TotalNoOfPage);

        request.setAttribute("displayRandomAds", displayRandomAds);
        request.setAttribute("CategoryDetail", CategoryDetail);
        
        request.setAttribute("what", "notNull");

        request.getRequestDispatcher("index.jsp").forward(request, response);
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
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
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
