<%-- 
    Document   : login
    Created on : 28 Feb, 2020, 10:33:50 AM
    Author     : AngryLion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="text-align: center;">
        <h1>Login Page for SUB4SUB</h1>

        <form name="logon" action="#">

            Name : <input type="text" name="fullname"><br><br>

            What you are:- 
            <select name="category" size="5">
                <option value="youtuber">youtuber</option>
                <option value="instagram">instagram</option>
                <option value="tiktok">TilTok</option>
                <option value="likee">likee</option>
            </select><br><br>

            Channel Name:- <input type="text" name="account_name"><br><br>

            Channel Link:- <input type="text" name="account_link"><br><br>

            How user will contact you:- 
            <select name="chaneelLink" size="5">
                <option value="phoneNumber">phone</option>
                <option value="instagram">instagram</option>
                <option value="facebook">facebook</option>
                <option value="email">Email</option>
            </select><br><br>

            contact detail:- <input type="text" name="contacds"><br><br>

            Which country you belong :- <input type="text" name="city"><br><br>


            Provide the new password:- <input type="password" name="password"><br><br>

            <input type="submit" name="Create Account">

        </form>
    </body>
</html>
