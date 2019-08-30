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
package com.admin.question;

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
public class deleteQuestionById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int questionId = Integer.parseInt(request.getParameter("qId"));
        int pageNumber = Integer.parseInt(request.getParameter("p"));
        
        try{
            boolean status = deleteQuestionById(questionId);
            if(status){
                response.sendRedirect("Admin/modifyQuestion.jsp?p="+pageNumber+"&msg=Question not deleted");
            }else{
                response.sendRedirect("Admin/modifyQuestion.jsp?p="+pageNumber+"&msg=Question has been successfully deleted");
            }
        }catch(SQLException msg){
            try {
                throw msg;
            } catch (SQLException ex) {
                Logger.getLogger(deleteQuestionById.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    private boolean deleteQuestionById(int questionId) throws SQLException{
        boolean status = false;
        DatabaseConnection dc = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        try{
            con = dc.getConnection();
            String sqlForDeleteTagedTopic = "delete from question_topic_tag where question_id = ?";
            ps = con.prepareStatement(sqlForDeleteTagedTopic);
            ps.setInt(1, questionId);
            status = ps.execute();
            if(!status){
                String sqlForDeleteQuestion = "delete from question where q_id = ?";
                 ps1 = con.prepareStatement(sqlForDeleteQuestion);
                 ps1.setInt(1, questionId);
                 status = ps1.execute();
            }
        }catch(SQLException msg){
            throw msg;
        }finally{
            if(ps != null){
                try{
                    ps.close();
                }catch(SQLException msg){
                    
                }
            }
            if(ps1 != null){
                try{
                    ps1.close();
                }catch(SQLException msg){
                    
                }
            }
            if(con != null){
                try{
                    con.close();
                }catch(SQLException msg){
                    
                }
            }
        }
        return status;
        
    }
}
