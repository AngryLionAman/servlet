<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Error</title>
    </head>
    <body>
        <center>
            <h3>Sorry for the inconvenience,Work in Process</h3>
            <%
                try {
                    out.println(exception.getMessage());
                } catch (Exception msg) {
                    out.println(msg);
                }
            %>
        </center>	
    </body>
</html>