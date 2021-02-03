/*
 * Copyright 2020 AngryLion.
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
package com.image;

/**
 *
 * @author AngryLion
 */
import com.connect.DatabaseConnection;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "UploadImage", urlPatterns = {"/UploadImage"})
public class UploadImage extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        out.println("request: " + request);
        
        if (!isMultipart) {
            out.println("File Not Uploaded");
        } else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            
            try {
                items = upload.parseRequest(request);
                out.println("items: " + items);
            } catch (FileUploadException e) {
                out.println("com.image.UploadImage.doPost()" + e);
            }
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    out.println("name: " + name);
                    String value = item.getString();
                    out.println("value: " + value);
                } else {
                    try {
                        String itemName = item.getName();
                        Random generator = new Random();
                        int r = Math.abs(generator.nextInt());
                        
                        String reg = "[.*]";
                        String replacingtext = "";
                        out.println("Text before replacing is:-" + itemName);
                        Pattern pattern = Pattern.compile(reg);
                        Matcher matcher = pattern.matcher(itemName);
                        StringBuffer buffer = new StringBuffer();
                        
                        while (matcher.find()) {
                            matcher.appendReplacement(buffer, replacingtext);
                        }
                        int IndexOf = itemName.indexOf(".");
                        String domainName = itemName.substring(IndexOf);
                        out.println("domainName: " + domainName);
                        
                        String finalimage = buffer.toString() + "_" + r + domainName;
                        out.println("Final Image===" + finalimage);
                        
                        File savedFile = new File("C:\\Program Files (x86)\\Apache Software Foundation\\Apache Tomcat 8.0.27\\webapps\\examples\\" + "images\\" + finalimage);
                        item.write(savedFile);
                        out.println("<html>");
                        out.println("<body>");
                        out.println("<table><tr><td>");
                        out.println("<img src=images/" + finalimage + ">");
                        out.println("</td></tr></table>");
                        
                        try {
                            DatabaseConnection connection = new DatabaseConnection();
                            
                            out.println("itemName::::: " + itemName);
                            
                            Connection conn = DatabaseConnection.makeConnection();
                            Statement st = conn.createStatement();
                            String strQuery = "insert into testimage set image='" + finalimage + "'";
                            int rs = st.executeUpdate(strQuery);
                            out.println("Query Executed Successfully++++++++++++++");
                            out.println("image inserted successfully");
                            out.println("</body>");
                            out.println("</html>");
                        } catch (ClassNotFoundException | SQLException e) {
                            out.println(e.getMessage());
                        } finally {
                            
                        }
                    } catch (Exception e) {
                        out.print(e);
                    }
                }
            }
        }
    }
}
