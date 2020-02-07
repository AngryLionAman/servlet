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
package com.approval.user;

import com.connect.DatabaseConnection;
import com.notifications.CreateNotification;
import com.notifications.SupportingClassFile;
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
@WebServlet(name = "action_approval_by_user", urlPatterns = {"/action_approval_by_user"})
public class action_approval_by_user extends HttpServlet {

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

        validateInput input = new validateInput();
        ActionApprovalClassFile classFile = new ActionApprovalClassFile();
        SupportingClassFile file = new SupportingClassFile();
        CreateNotification notification = new CreateNotification();

        String message = null;
        int oldQuestionId = 0;
        String path = "questions";

        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection()) {

            int notifiaction_id = input.getInputInt(request.getParameter("notifiaction_id"));
            oldQuestionId = input.getInputInt(request.getParameter("old_question_id"));
            int newQuestionId = input.getInputInt(request.getParameter("new_question_id"));
            String action = input.getInputString(request.getParameter("action"));
            String reason_message = input.getInputString(request.getParameter("reason_message"));

            if (oldQuestionId != 0 && newQuestionId != 0 && action != null) {
                if (action.equalsIgnoreCase("Accept")) {
                    if (classFile.isApprovedByAdmin(con, newQuestionId)) {
                        if (!classFile.replaceOldQuestionWithNewQuestionAndNewQuestionWithNullAndChangePermission(con, oldQuestionId, newQuestionId, reason_message)) {
                            if (!file.deleteNotificationByNotificationId(con, notifiaction_id)) {
                                if (!classFile.changePermissionOfQuestionByUser(con, newQuestionId, reason_message)) {
                                    int whoModifiedTheQuestion = classFile.whoModifiedTheQuestion(con, newQuestionId);
                                    if (whoModifiedTheQuestion != 0) {
                                        if (!notification.requestHasBeenApprovedForQuestion(con, whoModifiedTheQuestion, oldQuestionId)) {
                                            message = "Approval successful, Notification has been successfully sent to user";
                                        } else {
                                            message = "Approval notification failed to send";
                                        }
                                    } else {
                                        message = "Approval successful.Question is modified by Guest user. No need to send the approval notifiation";
                                    }
                                } else {
                                    message = "Approved by admin, User approval operation failed. please report to admin";
                                }
                            } else {
                                message = "Notification deleation operation failed. Due to this you will get the same request again, Please report to admin";
                            }
                        } else {
                            message = "Question modification operaion failed, Please try agin or report to admin";
                        }
                    } else {
                        if (!classFile.changePermissionOfQuestionByUser(con, newQuestionId, reason_message)) {
                            if (!file.deleteNotificationByNotificationId(con, notifiaction_id)) {
                                int whoModifiedTheQuestion = classFile.whoModifiedTheQuestion(con, newQuestionId);
                                if (whoModifiedTheQuestion != 0) {
                                    if (!notification.questionApprovedByUser(whoModifiedTheQuestion, oldQuestionId)) {
                                        message = "Question is approved by You, Approval is panding by the admin";
                                    } else {
                                        message = "Question approval notification Sendig failed";
                                    }
                                } else {
                                    message = "Question is modified by the Guest user, So admin permission required to make change";
                                }
                            } else {
                                message = "Failed to delete this notifiaction. due to this you will get the same request message again";
                            }
                        } else {
                            message = "Approved by user operation failed. Please try again";
                        }
                    }
                } else if (action.equalsIgnoreCase("Delete")) {
                    if (!classFile.questionRequestRejectedByUser(con, newQuestionId, reason_message)) {
                        if (!file.deleteNotificationByNotificationId(con, notifiaction_id)) {
                            int whoModifiedTheQuestion = classFile.whoModifiedTheQuestion(con, newQuestionId);
                            if (whoModifiedTheQuestion != 0) {
                                if (!notification.questionRejectedByUser(con, whoModifiedTheQuestion, oldQuestionId)) {
                                    message = "You have rejected the modification of question request, it has been informaed to the user";
                                } else {
                                    message = "Failed to send the rejection notification to the user.";
                                }
                            } else {
                                message = "This question is improved by Guest user. So, No need to send notification to any one";
                            }
                        } else {
                            message = "Deleating notification Operation failed, Please report to Admin";
                        }
                    } else {
                        message = "Rejection by you, operation failed. Please try again or report to admin";
                    }
                }
            } else {
                message = "You hitted the invalid url";
                path = "Error.jsp";
            }
        } catch (ClassNotFoundException | SQLException msg) {
            Logger.getLogger(action_approval_by_user.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("message", message);
            request.setAttribute("id", oldQuestionId);

            request.getRequestDispatcher(path).forward(request, response);
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
            Logger.getLogger(action_approval_by_user.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(action_approval_by_user.class.getName()).log(Level.SEVERE, null, ex);
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
