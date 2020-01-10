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
package com.test;

import java.io.*;
import java.sql.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.*;

/**
 *
 * @author AngryLion
 */
@WebServlet(name = "OrdersServlet", urlPatterns = {"/OrdersServlet"})
public class OrdersServlet extends HttpServlet {

    private static long pooledDuration, nonPooledDuration;
    private static long pooledCount, nonPooledCount;
    private DataSource datasource = null;

    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            //Create a datasource for pooled connections.
            //Register the driver for non pooled connections.
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            datasource = (DataSource) envCtx.lookup("jdbc/mydatabase");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NamingException e) {
            throw new ServletException(e.getMessage());
        }
    }

    /**
     *
     * @param pooledConnection
     * @return
     * @throws SQLException
     */
    public Connection getConnection(boolean pooledConnection) throws SQLException {
        if (pooledConnection) {
            pooledCount++;
            return datasource.getConnection();
        } else {
            nonPooledCount++;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bharat?useUnicode=true&amp;characterEncoding=utf-8", "root", null);
            return con;
        }
    }

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        String queryStr = req.getQueryString();
        out.println("<br>queryStr : " + queryStr);

        boolean poolingEnabled = queryStr == null || !queryStr.equals("disablePooling");
        out.println("<br>poolingEnabled : " + poolingEnabled);

        out.println("<html><head><title>Orders</title></head><body>");
        out.println("<br>PooledConnectionCount:" + pooledCount + ", nonPooledConnectionCount:" + nonPooledCount + "<br>");
        out.println("<br>pooledDuration:" + pooledDuration + ", nonPooledDuration:" + nonPooledDuration + "<br>");

        try {
            out.println("<br>" + "Average pooled response:" + pooledDuration / pooledCount);
        } catch (Exception s) {
            out.println(s);
        }
        try {
            out.println("<br>" + "Average non pooled response:" + nonPooledDuration / nonPooledCount);
        } catch (Exception s) {
            out.println(s);
        }
        if (pooledDuration > 0 && nonPooledDuration > 0) {
            out.println("<br>"
                    + "Average pooled response:" + pooledDuration / pooledCount
                    + "Average non pooled response:" + nonPooledDuration / nonPooledCount);
        }

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        long startTime = System.currentTimeMillis();
        out.println("<br>startTime : " + startTime);

        try {
            connection = getConnection(poolingEnabled);
            stmt = connection.createStatement();
            String sqlSelect = "select id,username from newuser  order by rand() limit 10";
            rs = stmt.executeQuery(sqlSelect);
            ResultSetMetaData dbMeta = rs.getMetaData();
            out.println("<br><table border='1'>");

            //Create the table headers
            out.println("<tr>");
            for (int col = 0; col < dbMeta.getColumnCount(); col++) {
                out.println("<th>" + dbMeta.getColumnName(col + 1) + "</th>");
            }
            out.println("</tr>");

            //Create the table data
            while (rs.next()) {
                out.println("<tr>");
                for (int col = 0; col < dbMeta.getColumnCount(); col++) {
                    out.println("<td>" + rs.getString(col + 1) + "</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
            connection.close();
        } catch (SQLException e) {
             out.println(e.fillInStackTrace());
             out.println(e.getSQLState());
            throw new ServletException(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
            long elapsed = System.currentTimeMillis() - startTime;
            out.println("elapsed : " + elapsed);
            //Collect the times 
            if (poolingEnabled) {
                OrdersServlet.pooledDuration += elapsed;
            } else {
                OrdersServlet.nonPooledDuration += elapsed;
            }
        }
    }

}
