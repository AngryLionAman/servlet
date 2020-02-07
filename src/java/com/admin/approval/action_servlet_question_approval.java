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
package com.admin.approval;

import com.approval.user.ActionApprovalClassFile;
import com.connect.DatabaseConnection;
import com.notifications.CreateNotification;
import com.string.validateInput;
import java.io.IOException;
import java.sql.Connection;
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
@WebServlet(name = "action_servlet_question_approval", urlPatterns = {"/action_servlet_question_approval"})
public class action_servlet_question_approval extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        validateInput input = new validateInput();
        AdminApprovalClassFile file = new AdminApprovalClassFile();
        ActionApprovalClassFile classFile = new ActionApprovalClassFile();
        CreateNotification notification = new CreateNotification();

        String message = null;

        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection()) {
            int old_question_id = input.getInputInt(request.getParameter("o_q_id"));
            int new_question_id = input.getInputInt(request.getParameter("n_q_id"));
            String commet_message = input.getInputString(request.getParameter(""));

            String action = input.getInputString(request.getParameter("action"));

            if (old_question_id != 0 && action != null) {
                if (action.equalsIgnoreCase("Accept")) {
                    if (file.isApprovedByUser(con, new_question_id)) {
                        if (!classFile.replaceOldQuestionWithNewQuestionAndNewQuestionWithNullAndChangePermission(con, old_question_id, new_question_id, commet_message)) {
                            if (!classFile.changePermissionOfQuestionByAdmin(con, new_question_id, message)) {
                                int whoModifiedTheQuestion = classFile.whoModifiedTheQuestion(con, new_question_id);
                                if (whoModifiedTheQuestion != 0) {
                                    if (!notification.requestHasBeenApprovedForQuestion(con, whoModifiedTheQuestion, old_question_id)) {
                                        message = "Notification has been successfully sent to user";
                                    } else {
                                        message = "Notification not sent to user";
                                    }
                                } else {
                                    message = "Notification is not generatred for the guest";
                                }
                            } else {
                                message = "Change question permission by admin is failed";
                            }

                        } else {
                            message = "Replace question with the new question is failed";
                        }
                    } else {
                        if (!classFile.changePermissionOfQuestionByAdmin(con, new_question_id, message)) {
                            int whoModifiedTheQuestion = classFile.whoModifiedTheQuestion(con, new_question_id);
                            if (whoModifiedTheQuestion != 0) {
                                if (!notification.questionApprovedByAdmin(con, whoModifiedTheQuestion, old_question_id)) {
                                    message = "Question is approved by You, Approval is panding by the User";
                                } else {
                                    message = "Sendig notification to user is failed";
                                }
                            } else {
                                message = "Question is modified by the guest user,No need to send notification";
                            }
                        } else {
                            message = "Change question permission by admin is failed";
                        }
                    }
                } else if (action.equalsIgnoreCase("Delete")) {
                    if (!classFile.questionRequestRejectedByAdmin(con, new_question_id, message)) {
                        int whoModifiedTheQuestion = classFile.whoModifiedTheQuestion(con, new_question_id);
                        if (whoModifiedTheQuestion != 0) {
                            if (!notification.questionRejectedByAdmin(con, whoModifiedTheQuestion, old_question_id)) {
                                message = "You have the rejected the question request, it has been informaed to the user";
                            } else {
                                message = "question request rejected by user , operation failed ";
                            }
                        } else {
                            message = "Notification not generation for the guest user in delete operation";
                        }
                    } else {
                        message = "Question approval rejected by admin operation failed";
                    }
                } else if (action.equalsIgnoreCase("Remove")) {
                    if (!classFile.deleteModificationQuestionByAdmin(con, new_question_id)) {
                        message = "Approval question deleted successfully";
                    } else {
                        message = "Not deleted, Try again";
                    }
                } else {
                    message = "There is no third action option, you must be manupulating url. Please select the valid input";
                }
            } else {
                message = "Invalid argument";
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = "Exception occred : " + e;
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("approval_request_for_admin_servlet").forward(request, response);
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
            Logger.getLogger(action_servlet_question_approval.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(action_servlet_question_approval.class.getName()).log(Level.SEVERE, null, ex);
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
