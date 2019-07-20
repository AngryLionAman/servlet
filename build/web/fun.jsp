<html lang="en">
    <head>
        <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var maxLength = 100;
                $(".show-read-more").each(function () {
                    var myStr = $(this).text();
                    if ($.trim(myStr).length > maxLength) {
                        var newStr = myStr.substring(0, maxLength);
                        var removedStr = myStr.substring(maxLength, $.trim(myStr).length);
                        $(this).empty().html(newStr);
                        $(this).append(' <a href="javascript:void(0);" class="read-more">read more...</a>');
                        $(this).append('<span class="more-text">' + removedStr + '</span>');
                    }
                });
                $(".read-more").click(function () {
                    $(this).siblings(".more-text").contents().unwrap();
                    $(this).remove();
                });
            });
        </script>
        <style type="text/css">
            .show-read-more .more-text{
                display: none;
            }
        </style>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Poem List | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta property="og:description" content="Read poem in hindi and english" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="Read poem in hindi and english" />
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="https://www.inquiryhere.com/" />
        <%@page import="java.sql.*" %> 
        <%@include file="site.jsp" %>
        <%@include file="validator.jsp" %>
        <%!            String WELCOME_NOTES = "";
        %>

        <%
            //String page_name = request.getParameter("page");
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                WELCOME_NOTES = "inquiryhere.com में आपकी रुचि के लिए धन्यवाद";
            } else {
                WELCOME_NOTES = "Thanks for your interest in Inquiryhere.com";
            }
        %>

    </head>

    <body>
        <div class="main-page-wrapper">
            <!-- Header _________________________________ -->
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="<%=sl%>"></jsp:param>
            </jsp:include>
            <%
                Connection connection = null;
                ResultSet resultSet = null;
                ResultSet resultSet1 = null;
                PreparedStatement preparedStatement = null;
                PreparedStatement preparedStatement1 = null;
                try {
                    if (connection == null || connection.isClosed()) {
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                        } catch (ClassNotFoundException ex) {
                            out.println("Exception in loading the class forname Driver" + ex);
                        }
                        connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                    }
            %>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <%
                            String get_page_title = "select distinct page from everything";

                            preparedStatement = connection.prepareStatement(get_page_title);

                            resultSet = preparedStatement.executeQuery();

                            while (resultSet.next()) {

                                String pageName = resultSet.getString("page");
                                out.println("<br>--------------------------<br>|&nbsp;&nbsp;" + pageName + "&nbsp;&nbsp;|<br>-----------------------------<br>");
                                try { //to get the top 10 result

                                    String sql_by_page_name = "select * from everything where page = ? limit 10";

                                    preparedStatement1 = connection.prepareStatement(sql_by_page_name);

                                    preparedStatement1.setString(1, pageName);

                                    resultSet1 = preparedStatement1.executeQuery();
                        %><div class="themeBox" style="height:auto;"><%
                            while (resultSet1.next()) {
                                if (resultSet1.getString("subject") != null && resultSet1.getString("subject").length() > 0) {
                                    //out.println("<br>subject -->" + resultSet1.getString("subject"));
                            %>
                            <div class="boxHeading marginbot10">
                                @<%=resultSet1.getString("subject")%>
                            </div>       
                            <%
                                    }   %><div class="questionArea"><%
                                    if (resultSet1.getString("description") != null && resultSet1.getString("description").length() > 0) {
                                        //out.println("<br>description -->" + resultSet1.getString("description"));
                                        %><p class="show-read-more"><%=resultSet1.getString("description")%></p><%
                                                                    }
                                                                    if (resultSet1.getString("author") != null && resultSet1.getString("author").length() > 0) {
                                                                        //out.println("<br>author -->" + resultSet1.getString("author"));
%><div class="postedBy">Posted By : <%=resultSet1.getString("author")%></div><%
                                        }
                                %>       </div>
                                <%
                                        //                                    if (resultSet1.getString("category") != null && resultSet1.getString("category").length() > 0) {
                                        //
                                        //                                        out.println("<br>Category -->" + resultSet1.getString("category"));
                                        //
                                        //                                    }
                                        //
                                        //                                    if (resultSet1.getString("based_on") != null && resultSet1.getString("based_on").length() > 0) {
                                        //
                                        //                                        out.println("<br>based on -->" + resultSet1.getString("based_on"));
                                        //
                                        //                                    }
                                        //                                    out.println(resultSet1.getString("page"));
                                        //
                                        //                                    out.println(resultSet1.getString("description"));
                                        out.println("<br>");
                                    }
                                %></div><%
                                        } catch (Exception msg) {
                                            out.println("<br>error in geting the 10 result" + msg);
                                        }
                                    }
                            %>
                    </div>


                    <div class="clear-fix"></div>
                    <%
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
                            /*if (resultSet != null || !resultSet.isClosed()) {
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
                            }*/
                        }
                    %>
                    <%@include file="notificationhtml.jsp" %>
                    <jsp:include page="footer.jsp">
                        <jsp:param name="sl" value="<%=sl%>"/>
                    </jsp:include>
                    <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
                    <!-- Bootstrap JS -->
                    <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
                    <!-- Bootstrap Select JS -->
                    <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
                </div> 
            </div>
    </body>
</html>