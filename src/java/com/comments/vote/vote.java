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
package com.comments.vote;

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
public class vote extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validateInput input = new validateInput();
        String action = input.getInputString(request.getParameter("action"));
        String section = input.getInputString(request.getParameter("section"));
        int question_or_answer_id = input.getInputInt(request.getParameter("question_answer_id"));
        int activetUserId = input.getInputInt(request.getParameter("activetUserId"));
        if (action != null && section != null && question_or_answer_id != 0 && activetUserId != 0) {
            try {
                saveVote vote = new saveVote();
                boolean value = vote.saveVoteOfQuestionAndAnswer(action, section, question_or_answer_id, activetUserId);
                if(!value){
                    //Vote has been added
                }
            } catch (SQLException ex) {
                Logger.getLogger(vote.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }

    }
}