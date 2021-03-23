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
package com.advertise;

import com.connect.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
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
public class saveAdvertise extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        try {
            final String userName = "adminUserName";
            final String passWord = "adminPassWord";
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String imageFileName = request.getParameter("filename");
            String imageNameAlt = request.getParameter("imageAlt");
            int height = Integer.parseInt(request.getParameter("height"));
            int width = Integer.parseInt(request.getParameter("width"));
            String promatedBy = request.getParameter("promoted_by");
            int viewStatus = Integer.parseInt(request.getParameter("view_status"));
            int days = Integer.parseInt(request.getParameter("days"));
            String forwoarlink = request.getParameter("forward_link");
            String adsType = request.getParameter("ads_type");
            String description = request.getParameter("description");
            if (userName.equals(username) && passWord.equals(password)) {
                if (!forwoarlink.isEmpty()  && !imageFileName.isEmpty() && height > 0 && width > 0 && !promatedBy.isEmpty() && (viewStatus == 0 || viewStatus == 1) && days > 0) {
                    
                    Connection con = null;
                    PreparedStatement ps = null;

                    try {
                        con = DatabaseConnection.getInstance().getConnection();
                        String sql = "insert into advertise (image_name,image_alt,height,width,promoted_by,display,days,forward_link,ads_type,description)values(?,?,?,?,?,?,?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, imageFileName);
                        ps.setString(2, imageNameAlt);
                        ps.setInt(3, height);
                        ps.setInt(4, width);
                        ps.setString(5, promatedBy);
                        ps.setInt(6, viewStatus);
                        ps.setInt(7, days);
                        ps.setString(8, forwoarlink);
                        ps.setString(9, adsType);
                        ps.setString(10, description);
                        boolean value = ps.execute();
                        if (!value) {
                            printWriter.print("value has been saved");
                        } else {
                            printWriter.print("this is else condition ,its mean something went wrong...");
                        }
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
                        request.setAttribute("message", "Data has been saved");
                        request.getRequestDispatcher("Admin/advertise.jsp").forward(request, response);
                    }

                } else {
                    printWriter.print("Something is missing..");
                }
            } else {
                request.setAttribute("message", "username or password is worng");
                request.getRequestDispatcher("Admin/advertise.jsp").forward(request, response);
            }
        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(saveAdvertise.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
