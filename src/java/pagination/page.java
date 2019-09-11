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
package pagination;

import com.connect.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class page extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            int startPage = 1;
            int recordPerPage = 30;
            float totalNumberOfpage = 0;
            int pageNumber = 1;
            if (request.getParameter("p") == null) {
                pageNumber = 1;
            } else {
                pageNumber = Integer.parseInt(request.getParameter("p"));
            }
            if (request.getParameter("p").isEmpty()) {
                pageNumber = 1;
            } else {
                pageNumber = Integer.parseInt(request.getParameter("p"));
            }
            if (pageNumber != 0) {
                startPage = (pageNumber * recordPerPage) - recordPerPage;
            }
            List<userRecoredPojo> list = new ArrayList<>();
            userRecoredPojo pojo = null;
            DatabaseConnection dc = DatabaseConnection.getInstance();
            Connection con = null;
            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            try {
                con = dc.getConnection();
                String sql = "select id,username,firstname,email,password from newuser limit ?,?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, startPage);
                ps.setInt(2, recordPerPage);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("id");
                    String userName = rs.getString("username");
                    String fullname = rs.getString("firstname");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    list.add(new userRecoredPojo(userId, userName, fullname, email, password));
                }
                String sql1 = "select count(*) as cnt from newuser";
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                if (rs1.first()) {
                    totalNumberOfpage = rs1.getInt("cnt");
                }
                totalNumberOfpage = totalNumberOfpage / recordPerPage;
                int newnumber = (int) totalNumberOfpage;
                if (totalNumberOfpage > newnumber) {
                    totalNumberOfpage = totalNumberOfpage + 1;
                }
            } catch (SQLException msg) {
                throw msg;
            } finally {
                request.setAttribute("list", list);
                request.setAttribute("totalNumberOfpage", totalNumberOfpage);
                request.getRequestDispatcher("pagination.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
