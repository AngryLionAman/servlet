/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author inquiryhere.com
 */
public class profile_need_to_re_modify extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            String fullName = request.getParameter("fullname");
            String eMail = request.getParameter("email");
            String higherQualification = request.getParameter("HigherQualification");
            String bestAchievement = request.getParameter("BestAchievement");
            String bio = request.getParameter("bio");
            userProfile obj = new userProfile();
            obj.saveUserProfile(eMail, higherQualification, bestAchievement, bio);
            out.println(fullName+"'s profile has been saved!!!!");
            response.sendRedirect("profile.jsp");
            //RequestDispatcher rd = request.getRequestDispatcher("UpdateUserProfile.jsp");
            //rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(profile_need_to_re_modify.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(profile_need_to_re_modify.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
