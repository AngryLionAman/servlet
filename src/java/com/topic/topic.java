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
package com.topic;

import com.question.getQuestion;
import com.question.getQuestionByTopicId;
import com.string.validateInput;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
public class topic extends HttpServlet {

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

        validateInput input = new validateInput();
        topicDetail detailForSeo = new topicDetail();
        getQuestionByTopicId byTopicId = new getQuestionByTopicId();
        getTopic topicObj = new getTopic();
        getTotalnumberOfColum colum = new getTotalnumberOfColum();
        supportingFunctionTopic ft = new supportingFunctionTopic();
        getQuestion q = new getQuestion();

        int recoredPerPage = 30;
        String message = null;
        String gotException = null;
        String validInput = null;

        List<topicPojo> topicDetailForSeo = null;
        HashMap<Integer, String> allQuestionByTopicId = null;
        int totalNumberOfpage = 0;
        HashMap<Integer, String> topicDetailByRefId = null;
        HashMap<Integer, String> randomQuestionByLimit = null;

        try {

            int pageNo = 0;

            try {
                pageNo = input.getInputInt(request.getParameter("p"));
            } catch (Exception msg) {
                Logger.getLogger(topic.class.getName()).log(Level.SEVERE, message, msg);
            }

            int topicId = 0;

            try {
                topicId = input.getInputInt(request.getParameter("id"));
            } catch (Exception msg) {
                Logger.getLogger(topic.class.getName()).log(Level.SEVERE, message, msg);
            }

            try {
                randomQuestionByLimit = q.getRandomQuestionByLimit(10);
            } catch (ClassNotFoundException | SQLException msg) {
                Logger.getLogger(topic.class.getName()).log(Level.SEVERE, message, msg);
            }

            if (topicId != 0) {
                validInput = "Got valid input";

                if (ft.isTopicPresentByTopicId(topicId)) {
                    try {
                        topicDetailForSeo = detailForSeo.topic(topicId);
                    } catch (Exception msg) {
                        Logger.getLogger(topic.class.getName()).log(Level.SEVERE, message, msg);
                    }

                    try {
                        allQuestionByTopicId = byTopicId.getAllQuestionByTopicId(topicId, pageNo, recoredPerPage);
                    } catch (ClassNotFoundException | SQLException msg) {
                        Logger.getLogger(topic.class.getName()).log(Level.SEVERE, message, msg);
                    }

                    try {
                        totalNumberOfpage = colum.totalNumberOfPageOfTopicByTopicId(topicId, recoredPerPage);
                    } catch (ClassNotFoundException | SQLException msg) {
                        Logger.getLogger(topic.class.getName()).log(Level.SEVERE, message, msg);
                    }

                    try {
                        topicDetailByRefId = topicObj.getTopicDetailByRefId(topicId);
                    } catch (ClassNotFoundException | SQLException msg) {
                        Logger.getLogger(topic.class.getName()).log(Level.SEVERE, message, msg);
                    }

                } else {
                    message = "Topic not found in database or location has been changed, please try searching option";
                }

            } else {
                message = "Topic has been deleted or location has been changed or you may hiting the invalid argumet, please try searching option";
            }

        } catch (Exception msg) {
            gotException = "not null";
            Logger.getLogger(topic.class.getName()).log(Level.SEVERE, message, msg);
        } finally {
            request.setAttribute("validInput", validInput);
            request.setAttribute("message", message);
            request.setAttribute("gotException", gotException);

            request.setAttribute("randomQuestionByLimit", randomQuestionByLimit);
            request.setAttribute("topicDetailForSeo", topicDetailForSeo);
            request.setAttribute("allQuestionByTopicId", allQuestionByTopicId);
            request.setAttribute("totalNumberOfpage", totalNumberOfpage);
            request.setAttribute("topicDetailByRefId", topicDetailByRefId);

            request.getRequestDispatcher("topic.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(topic.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(topic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
