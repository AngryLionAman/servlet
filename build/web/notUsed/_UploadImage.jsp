<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Path"%>
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
<%    if (session.getAttribute("email") != null) {
        String type = request.getContentType();
        try {
            if (type.indexOf("multipart/form-data") >= 0) {
                Part part = request.getPart("photo");
                out.println(part.getName());
                out.println(part.getSize());
                InputStream inputStream = part.getInputStream();
                String relatinvePath = "/images";
                ServletContext servletContext = getServletContext();
                String fullpath = servletContext.getRealPath(relatinvePath);
                File file = new File(fullpath,part.getName()+ ".png");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                int read = inputStream.read();
                while(read != -1){
                    fileOutputStream.write(read);
                    read = inputStream.read();
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                out.println("file has been uploaded");

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
