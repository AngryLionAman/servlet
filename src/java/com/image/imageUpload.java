//
//package com.image;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;
//
///**
// *
// * @author AngryLion
// */
//@MultipartConfig(maxFileSize = 15000000)
//@WebServlet(name = "imageUpload", urlPatterns = {"/imageUpload"})
//public class imageUpload extends HttpServlet {
//
//    PrintWriter pw;
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        pw = response.getWriter();
//        try {
//            Part part = request.getPart("photo");
//            if (part != null) {
//                pw.print(part.getSize());
//                InputStream inputStream = part.getInputStream();
//                String relativePath = "/images";
//                ServletContext application = getServletContext();
//                String absolutepath = application.getRealPath(relativePath);
//
//                HttpSession session = request.getSession();
//                pw.print("Before session");
//                int UserEmail = (Integer) (session.getAttribute("Session_id_of_user"));
//                pw.print(UserEmail);
//
//                String fileName = part.getName() + "of" + UserEmail + ".jpg";
//
//                File file = new File(absolutepath, fileName);
//                try (FileOutputStream fos = new FileOutputStream(file)) {
//                    int read = inputStream.read();
//                    while (read != -1) {
//                        fos.write(read);
//                        read = inputStream.read();
//                    }
//                    fos.flush();
//                    fos.close();
//                }
//              /*  pw.print("<br>Image has been upload");
//                pw.print("<br> Content type:" + part.getContentType());
//                pw.print("<br> fullPath:" + absolutepath);
//                pw.print("<br> fullPath:" + file.getAbsolutePath());
//                pw.print("<br> FileName:" + part.getName());*/
//
//                //pw.print("<br>"+file.getAbsolutePath());
//                // pw.print("<br><img src='images\\" + part.getName() + ".jpg' alt='not found'/>");
//                try {
//                    Class.forName("com.mysql.jdbc.Driver");
//                    try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bharat", "root", null);
//                            Statement st = cn.createStatement()) {
//                        String p = "update newuser set imagepath = '" + fileName + "' where id = '" + UserEmail + "'";
//                        st.execute(p);
//                    }
//                    //pw.print("Recored has been successfully updated");
//                    response.sendRedirect("UpdateUserProfile.jsp");
//                } catch (ClassNotFoundException | SQLException msg) {
//                    pw.print(msg);
//                }
//
//            }
//        } catch (IOException | ServletException msg) {
//            pw.print("Exception msg is:" + msg);
//        }
//    }
//
//}
