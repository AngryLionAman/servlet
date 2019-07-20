
<%@page import="java.util.Random"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%!    public String CreateUsername(String username) {
        String finalUsername = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
            String sql = "select username from newuser where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean usernameFound = false;
            while (resultSet.next()) {
                usernameFound = true;
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
            if (usernameFound) {
                Random rand = new Random();
                int number = rand.nextInt(100);
                finalUsername = username + number;
                CreateUsername(finalUsername);
            } else {
                finalUsername = username;
            }
        } catch (Exception msg) {
            finalUsername = msg.toString();
        }
        return finalUsername;
    }
%>
<%
    String sl = request.getParameter("sl");
    if (sl == null) {
        sl = "eng";
    }
    String firstname, password, email;
    firstname = request.getParameter("firstname");
    email = request.getParameter("email");
    password = request.getParameter("password");
    //userName = request.getParameter("userName");

    if (firstname == null || email == null || password == null) {
        out.println("you can't access this page direcitly");
    } else {
        //Form validation
        /*Email validation*/
        boolean validFirstName = false;
        //boolean validLastName = false;
        boolean validPassword = false;
        boolean emailValid = false;
        boolean MobileValid = false;
        //boolean userNameValid = false;
        //UserName validation
//        int userNameLength = userName.length();
//        if(userNameLength > 5){
//            userNameValid = true;
//        }

    String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    if(email.matches(pattern)){
      MobileValid = true;  
    }

        //Regular expression for validating email from server side
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        if (email.matches(ePattern)) {
            //out.println("<b>"+email+"</b> email is valid");
            emailValid = true;
        }
        /**
         * * ****************
         */
        /*Firstname validation*/
        int length = firstname.length();
        if (length < 25) { //If the name length is too long
            Pattern p = Pattern.compile("[^A-Za-z0-9\\s]");
            Matcher m = p.matcher(firstname);
            // boolean b = m.matches();
            boolean b = m.find();
            if (b != true) {
                validFirstName = true;
            }
        }

        /**
         * *****************
         */
        /*LastName validation*/
        //       length = lastname.length(); 
//        if (length < 25) {
//            Pattern p = Pattern.compile("[^A-Za-z0-9]");
//            Matcher m = p.matcher(lastname);
//            // boolean b = m.matches();
//            boolean b = m.find();
//            if (b != true) {
//                validLastName = true;
//            }
//        }
        /**
         * *****************
         */
        /*Validate password lenght*/
        length = password.length();
        if (length >= 6) {
            validPassword = true;
        }

        /**
         * *****************
         */
        if (validFirstName && validPassword && (emailValid || MobileValid)) {
            String Email = request.getParameter("email");
            Connection connection = null;
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            int Session_id_of_user = 0;
            try {
                if (connection == null || connection.isClosed()) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                        out.println("Exception in loading the class forname Driver" + ex);
                    }
                    connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                }
                String v_check = "SELECT id,email FROM newuser WHERE email = '" + Email + "'";
                preparedStatement = connection.prepareStatement(v_check);
                resultSet = preparedStatement.executeQuery();
                int i = 0;
                while (resultSet.next()) {
                    String Stored_email = resultSet.getString("email");
                    if (Stored_email.equals(Email)) {
                        i = 1;
                    }
                }
                if (i == 1) {
                    response.sendRedirect("signup.jsp?sl=" + sl + "&Error=This email/Phone is already registered with this site, please choose another one");
                } else {
                    try {
                        String userName = CreateUsername(firstname.trim().replaceAll(" ", ""));
                        //Statement statement = connection.createStatement();
                        String insert_user = "insert into newuser(firstname,username,email,email_s,password,imagepath) values(?,?,?,?,?,?)";

                        //'" + firstname + "','" + lastname + "','" + email + "','0','" + password + "','inquiryhere_Logo.PNG'
                        //Email_s represent the security of gmail
                        // i can make this value as default but when i thought about this, i did't know about how to set default value in database
                        // and when i got to know that time i feel lazy thats why i leave it as it was
                        preparedStatement = connection.prepareStatement(insert_user);
                        preparedStatement.setString(1, firstname);
                        preparedStatement.setString(2, userName);
                        preparedStatement.setString(3, email);
                        preparedStatement.setInt(4, 1);
                        preparedStatement.setString(5, password);
                        preparedStatement.setString(6, "inquiryhere_Logo.PNG");
                        preparedStatement.execute();
                        try {
                            Cookie usernameCookie = new Cookie("username-cookie", email);
                            Cookie passwordCookie = new Cookie("password-cookie", password);
                            usernameCookie.setMaxAge(24 * 60 * 60 * 100);
                            passwordCookie.setMaxAge(24 * 60 * 60 * 100);
                            response.addCookie(usernameCookie);
                            response.addCookie(passwordCookie);
                        } catch (Exception msg) {
                            out.println("Please use password without space");
                        }
                        
//                        statement.execute(p);
//                        if (statement != null) {
//                            statement.close();
//                        }
                        String fetch_user_id = "SELECT id FROM newuser WHERE email = '" + Email + "'";
                        preparedStatement = connection.prepareStatement(fetch_user_id);
                        resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            //String Stored_email = resultSet.getString("email");
                            Session_id_of_user = resultSet.getInt("id");
                        }
                        session.setAttribute("email", email);
                        session.setAttribute("Session_id_of_user", Session_id_of_user);
                        response.sendRedirect("CompleteProfilefFollowTopic.jsp?sl=" + sl);
                    } catch (Exception e1) {
                        out.print("Error:-" + e1);
                    }
                }
            } catch (Exception e) {
                out.println("Error in main try block:-" + e);
            } finally {

                if (connection != null || !connection.isClosed()) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        out.println("Exception in closing connection " + e);
                    }
                }
                if (resultSet != null || !resultSet.isClosed()) {
                    try {
                        resultSet.close();
                    } catch (Exception e) {
                        out.println("Exception in closing resulatset " + e);
                    }
                }
                if (preparedStatement != null || !preparedStatement.isClosed()) {
                    try {
                        preparedStatement.close();
                    } catch (Exception e) {
                        out.println("Exception in closing preparedStatement " + e);
                    }
                }
            }
        } else {
            out.println("Its seem like you are doing effort to break the site rule"
                    + "<br>Plese follow the procedure ,don't try to break the rule other wise your activity "
                    + "will be recorded for the monitoring purpose");
        }
    }
%>