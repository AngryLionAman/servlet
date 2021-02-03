<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@include file="db.jsp" %>
<%
    if (request.getParameter("file_location") != null
            && request.getParameter("notes_name") != null
            && request.getParameter("notes_by") != null
            && request.getParameter("notes_subject") != null
            && request.getParameter("notes_for") != null
            && request.getParameter("notes_price") != null
            && request.getParameter("notes_discount") != null
            && request.getParameter("notes_current_prise") != null) {
        String filelocation = request.getParameter("file_location");
        String notes_description = request.getParameter("notes_description");
        String notes_name = request.getParameter("notes_name");
        String notes_by = request.getParameter("notes_by");
        String notes_subject = request.getParameter("notes_subject");
        String notes_for = request.getParameter("notes_for");
        float notes_price = Float.parseFloat(request.getParameter("notes_price"));
        int notes_discount = Integer.parseInt(request.getParameter("notes_discount"));
        int total_no_of_page = Integer.parseInt(request.getParameter("total_no_of_page"));
        float notes_current_prise = Float.parseFloat(request.getParameter("notes_current_prise"));
       
        out.println("<br>"+filelocation);
        out.println("<br>"+notes_description);
        out.println("<br>"+notes_name);
        out.println("<br>"+notes_by);
        out.println("<br>"+notes_subject);
        out.println("<br>"+notes_for);
        out.println("<br>"+notes_price);
        out.println("<br>"+notes_discount);
        out.println("<br>"+total_no_of_page);
        out.println("<br>"+notes_current_prise);

    } else {
        out.println("You just left some option empty");
    }

%>