<%-- 
    Document   : readCookies
    Created on : 28 Jun, 2019, 12:21:15 PM
    Author     : AngryLion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Reading Cookies</title>
   </head>
   
   <body>
      <center>
         <h1>Reading Cookies</h1>
      </center>
      <%
         Cookie cookie = null;
         Cookie[] cookies = null;
         
         // Get an array of Cookies associated with the this domain
         cookies = request.getCookies();
         
         if( cookies != null ) {
            out.println("<h2> Found Cookies Name and Value</h2>");
            
            for (int i = 0; i < cookies.length; i++) {
               cookie = cookies[i];
               out.print("Name : " + cookie.getName( ) + ",  ");
               out.print("Value: " + cookie.getValue( )+" <br/>");
//               if(cookie.getName().endsWith("first_name") ){
//                   out.println("validation is working");
//               }else{
//                   out.println("validation is not working");
//               }
            }
         } else {
            out.println("<h2>No cookies founds</h2>");
         }
      %>
   </body>
   
</html>
