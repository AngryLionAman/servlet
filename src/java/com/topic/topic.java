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

import com.question.getQuestionByTopicId;
import java.io.IOException;
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

    private int getInput(String pageNo) {
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

    public int getInputInt(String topicId) {
        int id = 0;
        if (topicId == null) {
            return 0;
        }
        if (topicId.isEmpty()) {
            return 0;
        }
        if (!topicId.isEmpty()) {
            id = Integer.parseInt(topicId);
        }
        return id;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNo = getInput(request.getParameter("p"));

        int topicId;
        if (request.getParameter("id") == null) {

            topicId = 0;
            
        } else {

            topicId = getInputInt(request.getParameter("id"));

        }
        int recoredPerPage = 30;

        topicDetail detailForSeo = new topicDetail();

        getQuestionByTopicId byTopicId = new getQuestionByTopicId();

        getTopic topic = new getTopic();

        getTotalnumberOfColum colum = new getTotalnumberOfColum();

        if (topicId != 0) {

            try {

                List<topicPojo> topicDetailForSeo = detailForSeo.topic(topicId);

                HashMap<Integer, String> allQuestionByTopicId = byTopicId.getAllQuestionByTopicId(topicId, pageNo, recoredPerPage);

                int totalNumberOfpage = colum.totalNumberOfPageOfTopicByTopicId(topicId, recoredPerPage);

                List<topicPojo> topicDetailByRefId = topic.getTopicDetailByRefId(topicId);

                request.setAttribute("topicDetailForSeo", topicDetailForSeo);

                request.setAttribute("allQuestionByTopicId", allQuestionByTopicId);

                request.setAttribute("totalNumberOfpage", totalNumberOfpage);

                request.setAttribute("topicDetailByRefId", topicDetailByRefId);

                request.getRequestDispatcher("topic.jsp").forward(request, response);

            } catch (Exception msg) {

                try {

                    throw msg;

                } catch (Exception ex) {

                    Logger.getLogger(topic.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        } else {
            request.setAttribute("message", "You have hitted the bad url");

            request.getRequestDispatcher("topic.jsp").forward(request, response);
        }

    }
}
