<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!    
    String str;
    File file;
    int maxSize = 1024 * 1024 * 1024;
    int maxFactSize = 1024 * 1024 * 1024;
    //String path = "C:/Users/AngryLion/Documents/NetBeansProjects/InquiryHere/web/images/UploadedImage";
    String path = "E:/Project/inquiryhere/web/images/UploadedImage";
%>
<%
    if (session.getAttribute("email") != null) {
        String type = request.getContentType();
        try {
            if (type.indexOf("multipart/form-data") >= 0) {
                DiskFileItemFactory disFact = new DiskFileItemFactory();
                disFact.setSizeThreshold(maxFactSize);
                //disFact.setRepository(new File("c:\\temp"));
                ServletFileUpload upload = new ServletFileUpload(disFact);
                upload.setSizeMax(maxSize);
                List lis = upload.parseRequest(request);
                Iterator itr = lis.iterator();
                String UserEmail = (String) session.getAttribute("email");
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (!item.isFormField()) {
                        str = item.getName();
                        str = UserEmail + (str.substring(str.indexOf(".")));
                        //out.println("<br>" + str);
                        file = new File(path, str);
                        item.write(file);
                        Statement st;
                        Connection cn;
                        ResultSet rs;
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            cn = DriverManager.getConnection(DB_URL_,DB_USERNAME_,DB_PASSWORD_);
                            st = cn.createStatement();
                            String p = "update newuser set imagepath = '" + str + "' where email = '" + UserEmail + "'";
                            st.execute(p);
                            st.close();
                            cn.close();
                            out.println("Recored has been successfully updated");
                        } catch (Exception e1) {
                            out.print("Error:-" + e1);
                        }
                        response.sendRedirect("profile.jsp");
                    }
                }
            } else {
                out.println("Please select a file");
            }
        } catch (Exception x) {
            out.println("Error in Image file uploading" + x);
        }
    } else {
        out.println("You can't access this page directly");
    }
    

%>
