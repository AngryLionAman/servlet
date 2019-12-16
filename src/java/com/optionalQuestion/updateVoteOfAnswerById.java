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
package com.optionalQuestion;

import com.connect.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class updateVoteOfAnswerById extends HttpServlet {

    private int getInput(String id) {
        int _id = 0;
        if (id == null) {
            return 1;
        }
        if (id.isEmpty()) {
            return 1;
        }
        if (!id.isEmpty()) {
            _id = Integer.parseInt(id);
        }
        return _id;
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
        int answerId = getInput(request.getParameter("answerId"));
        if (answerId != 0) {
            try {
                DatabaseConnection dc = DatabaseConnection.getInstance();
                Connection con = null;
                PreparedStatement ps = null;
                try {
                    con = dc.getConnection();
                    String sql = "update option_of_question set vote = vote + 1 where unique_id = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, answerId);
                    ps.execute();
                } catch (SQLException msg) {
                    throw msg;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException msg) {
                            
                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException msg) {
                            
                        }
                    }
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(updateVoteOfAnswerById.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
