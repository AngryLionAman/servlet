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
package com.profile;

import com.comments.GetComment;
import com.comments.ProfileCommentsPojo;
import com.connect.DatabaseConnection;
import com.login.supportingFunctionLogin;
import com.string.validateInput;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AngryLion
 */
public class profile extends HttpServlet {

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
        profileDetailClassFile file = new profileDetailClassFile();
        userSupportingClass supportingClass = new userSupportingClass();
        GetComment comment = new GetComment();

        List<profileDetialPojoFile> GetUserDetailByUserId = null;
        List<questionByUserIdPojo> GetTotalQuestionPostedByUserId = null;
        List<answerByUserIdPojo> GetTotalAnswerPostedByUserId = null;
        List<TopicByUserIdPojo> GetTotalTopicFollowedByUserId = null;
        List<FollowersByUserIdPojo> GetTotalFollowersByUserId = null;
        List<FollowingByUserIdPojo> GetTotalFollowingByUserId = null;
        List<BlogByUserIdPojo> GetTotalBlogByUserId = null;
        List<CountRowByUserIdPojo> CountRowByUserIdController = null;
        List<ProfileCommentsPojo> GetCommentByProfileId = null;

        String message = null;
        String gotException = null;
        String path = "profile.jsp";

        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection()) {
            int userId = 0;

            try {
                userId = GetUserId(con, request);
            } catch (Exception msg) {
                Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
            }

            String tab = "question";

            try {
                if (request.getAttribute("tab") != null) {
                    tab = (String) request.getAttribute("tab");
                } else {
                    if (null == request.getParameter("tab")) {
                        tab = "question";
                    } else {
                        switch (request.getParameter("tab")) {
                            case "":
                                tab = "question";
                                break;
                            default:
                                tab = input.getInputString(request.getParameter("tab"));
                                break;
                        }
                    }
                }
            } catch (Exception msg) {
                Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
            }

            if (request.getAttribute("message") != null) {
                message = (String) request.getAttribute("message");
            }

            if (userId != 0) {

                try {
                    GetUserDetailByUserId = file.GetUserDetailByUserId(con,userId);
                } catch (ClassNotFoundException | SQLException msg) {
                    Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                }
                try {
                    supportingClass.UpdateProfileViewBy1ByUserId(con,userId);
                } catch (ClassNotFoundException | SQLException msg) {
                    Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                }

                try {
                    CountRowByUserIdController = supportingClass.CountRowByUserIdController(con,userId);
                } catch (ClassNotFoundException | SQLException msg) {
                    Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                }

                try {
                    GetCommentByProfileId = comment.GetCommentByProfileId(con,userId);
                } catch (ClassNotFoundException | SQLException msg) {
                    Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                }

                switch (tab) {
                    case "question":
                        try {
                            GetTotalQuestionPostedByUserId = supportingClass.GetTotalQuestionPostedByUserId(con,userId);
                        } catch (ClassNotFoundException | SQLException msg) {
                            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                        }

                        break;

                    case "answer":
                        try {
                            GetTotalAnswerPostedByUserId = supportingClass.GetTotalAnswerPostedByUserId(con,userId);
                        } catch (ClassNotFoundException | SQLException msg) {
                            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                        }

                        break;

                    case "topic":
                        try {
                            GetTotalTopicFollowedByUserId = supportingClass.GetTotalTopicFollowedByUserId(con,userId);
                        } catch (ClassNotFoundException | SQLException msg) {
                            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                        }

                        break;

                    case "following":
                        try {
                            GetTotalFollowingByUserId = supportingClass.GetTotalFollowingByUserId(con,userId);
                        } catch (ClassNotFoundException | SQLException msg) {
                            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                        }

                        break;

                    case "followers":
                        try {
                            GetTotalFollowersByUserId = supportingClass.GetTotalFollowersByUserId(con,userId);
                        } catch (ClassNotFoundException | SQLException msg) {
                            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                        }

                        break;

                    case "blog":
                        try {
                            GetTotalBlogByUserId = supportingClass.GetTotalBlogByUserId(con,userId);
                        } catch (ClassNotFoundException | SQLException msg) {
                            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                        }

                        break;

                    default:
                        try {
                            GetTotalQuestionPostedByUserId = supportingClass.GetTotalQuestionPostedByUserId(con,userId);
                        } catch (ClassNotFoundException | SQLException msg) {
                            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, path, msg);
                        }

                        break;
                }
                path = "profile.jsp";
            } else {
                message = "The user you are looking for is not present in our database,"
                        + " Or may you entring the invalid argument, Please try search option....";
                path = "Error.jsp";
            }

        } catch (Exception msg) {
            gotException = "not null";
            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, message, msg);
        } finally {
            request.setAttribute("gotException", gotException);

            request.setAttribute("message", message);
            request.setAttribute("GetUserDetailByUserId", GetUserDetailByUserId);
            request.setAttribute("CountRowByUserIdController", CountRowByUserIdController);
            request.setAttribute("GetTotalQuestionPostedByUserId", GetTotalQuestionPostedByUserId);
            request.setAttribute("GetTotalAnswerPostedByUserId", GetTotalAnswerPostedByUserId);
            request.setAttribute("GetTotalTopicFollowedByUserId", GetTotalTopicFollowedByUserId);
            request.setAttribute("GetTotalFollowersByUserId", GetTotalFollowersByUserId);
            request.setAttribute("GetTotalFollowingByUserId", GetTotalFollowingByUserId);
            request.setAttribute("GetTotalBlogByUserId", GetTotalBlogByUserId);
            request.setAttribute("GetCommentByProfileId", GetCommentByProfileId);
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    /**
     *
     * @param con
     * @param request
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int GetUserId(Connection con, HttpServletRequest request) throws SQLException, ClassNotFoundException, Exception {

        validateInput input = new validateInput();
        HttpSession session = request.getSession(false);
        profileDetailClassFile file = new profileDetailClassFile();
        int getParameterUserId = input.getInputInt(request.getParameter("id"));
        String getParameterUserName = input.getInputString(request.getParameter("user"));

        int getAttributeUserId;

        if (request.getAttribute("id") != null) {
            getAttributeUserId = input.getInputInt(String.valueOf((int) request.getAttribute("id")));
        } else {
            getAttributeUserId = 0;
        }

        int sessionGetAttributUserId;
        if (session != null) {
            if (session.getAttribute("Session_id_of_user") != null) {
                sessionGetAttributUserId = (int) session.getAttribute("Session_id_of_user");
            } else {
                sessionGetAttributUserId = 0;
            }
        } else {
            sessionGetAttributUserId = 0;
        }

        int userId = 0;

        if (getParameterUserId != 0) {
            if (file.IsUserPresent(con, getParameterUserId)) {
                userId = getParameterUserId;
            }
        } else if (getParameterUserName != null) {
            if (file.IsUserPresent(con, getParameterUserName)) {
                userId = file.GetUserIdByUserName(con,getParameterUserName);
            }
        } else if (getAttributeUserId != 0) {
            if (file.IsUserPresent(con, getAttributeUserId)) {
                userId = getAttributeUserId;
            }
        } else if (sessionGetAttributUserId != 0) {
            if (file.IsUserPresent(con, sessionGetAttributUserId)) {
                userId = sessionGetAttributUserId;
            }
        } else {
            userId = GetUserIdByCookiesEmailAndPass(con, request);
        }

        return userId;
    }

    /**
     *
     * @param con
     * @param request
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int GetUserIdByCookiesEmailAndPass(Connection con, HttpServletRequest request) throws SQLException, ClassNotFoundException, Exception {

        supportingFunctionLogin login = new supportingFunctionLogin();
        validateInput input = new validateInput();

        String userEmail = null;
        String userPass = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usernamecookie")) {
                    userEmail = cookie.getValue();
                }
                if (cookie.getName().equals("passwordcookie")) {
                    userPass = cookie.getValue();
                }
            }

            if (input.getInputString(userEmail) != null && input.getInputString(userPass) != null) {
                if (login.IsUserIsPresent(con, userEmail, userPass)) {
                    return login.GetUserIdByEmailAndPassword(con, userEmail, userPass);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
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
            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, null, ex);
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
