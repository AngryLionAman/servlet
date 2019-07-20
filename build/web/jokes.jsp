<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Jokes in Hindi | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta property="og:description" content="read jokes in Hindi and english" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="Read jokes in Hindi and english" />
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="https://www.inquiryhere.com/" />
        <%@page import="java.sql.*" %> 
        <%@include file="site.jsp" %>
        <%@include file="validator.jsp" %>
        <%!            
            String WELCOME_NOTES = "";
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
                PreparedStatement preparedStatement = null;
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
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <%
                                    ResultSet rs1 = null;
                                    ResultSet rs2 = null;
                                    ResultSet rs3 = null;
                                    PreparedStatement ps1 = null;
                                    PreparedStatement ps2 = null;
                                    PreparedStatement ps3 = null;
                                    // Connection connection2 = null;

                                    int showRows = 10;
                                    int totalRecords = 5;
                                    int totalRows = nullIntconvert(request.getParameter("totalRows"));
                                    int totalPages = nullIntconvert(request.getParameter("totalPages"));
                                    int iPageNo = nullIntconvert(request.getParameter("iPageNo"));
                                    int cPageNo = nullIntconvert(request.getParameter("cPageNo"));
                                    String category = nullStringconvert(request.getParameter("category"));
                                    String query1 = "";

                                    int startResult = 0;
                                    int endResult = 0;
                                    if (iPageNo == 0) {
                                        iPageNo = 0;
                                    } else {
                                        iPageNo = Math.abs((iPageNo - 1) * showRows);
                                    }
                                    try {
                                        Class.forName("com.mysql.jdbc.Driver");
                                        connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                                        if (category.equalsIgnoreCase("All")) {
                                            query1 = "SELECT SQL_CALC_FOUND_ROWS * FROM joke ORDER BY joke LIMIT " + iPageNo + "," + showRows + "";
                                        } else {
                                            query1 = "SELECT SQL_CALC_FOUND_ROWS * FROM joke where joke_on = '" + category + "' ORDER BY joke LIMIT " + iPageNo + "," + showRows + "";
                                        }
                                        ps1 = connection.prepareStatement(query1);
                                        rs1 = ps1.executeQuery();

                                        String query2 = "SELECT FOUND_ROWS() as cnt";
                                        ps2 = connection.prepareStatement(query2);
                                        rs2 = ps2.executeQuery();
                                    } catch (Exception error) {
                                        out.println("Error occer in :" + error);
                                    }
                                    try {
                                        if (rs2.next()) {
                                            totalRows = rs2.getInt("cnt");
                                        }

                                %>
                                <form>

                                    <input type="hidden" name="iPageNo" value="<%=iPageNo%>">
                                    <input type="hidden" name="cPageNo" value="<%=cPageNo%>">
                                    <input type="hidden" name="showRows" value="<%=showRows%>">
                                    <%
                                        while (rs1.next()) {
                                    %>
                                    <div class="themeBox" style="height:auto;">

                                        <div class="boxHeading">
                                            <%=rs1.getString("joke")%>
                                        </div>

                                    </div>

                                    <%
                                            }
                                        } catch (Exception e) {
                                            out.println(e);
                                        }

                                    %>

                                    <%                                            try {
                                            if (totalRows < (iPageNo + showRows)) {
                                                endResult = totalRows;
                                            } else {
                                                endResult = (iPageNo + showRows);
                                            }
                                            startResult = (iPageNo + 1);
                                            totalPages = ((int) (Math.ceil((double) totalRows / showRows)));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    %>
                                    <table width="100%" cellpadding="0" cellspacing="0" border="1" >
                                        <tr>
                                            <td colspan="3">
                                                <div>
                                                    <%
                                                        int i = 0;
                                                        int cPage = 0;
                                                        if (totalRows != 0) {
                                                            cPage = ((int) (Math.ceil((double) endResult / (totalRecords * showRows))));

                                                            int prePageNo = (cPage * totalRecords) - ((totalRecords - 1) + totalRecords);
                                                            if ((cPage * totalRecords) - (totalRecords) > 0) {
                                                    %>
                                                    <a href="jokes.jsp?iPageNo=<%=prePageNo%>&cPageNo=<%=prePageNo%>&category=<%=category%>&sl=<%=sl%>"> << Previous</a>
                                                    <%
                                                        }
                                                        for (i = ((cPage * totalRecords) - (totalRecords - 1)); i <= (cPage * totalRecords); i++) {
                                                            if (i == ((iPageNo / showRows) + 1)) {%>
                                                    <a href="jokes.jsp?iPageNo=<%=i%>&category=<%=category%>&sl=<%=sl%>" style="cursor:pointer;color:red"><b><%=i%></b></a>
                                                            <%
                                                            } else if (i <= totalPages) {
                                                            %>
                                                    <a href="jokes.jsp?iPageNo=<%=i%>&category=<%=category%>&sl=<%=sl%>"><%=i%></a>
                                                    <%
                                                            }
                                                        }
                                                        if (totalPages > totalRecords && i < totalPages) {
                                                    %>
                                                    <a href="jokes.jsp?iPageNo=<%=i%>&cPageNo=<%=i%>&category=<%=category%>&sl=<%=sl%>"> >> Next</a>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                    <b>Rows <%=startResult%> - <%=endResult%>, Total Rows <%=totalRows%> </b>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                                <%
                                    try {
                                        if (ps1 != null) {
                                            ps1.close();
                                        }
                                        if (rs1 != null) {
                                            rs1.close();
                                        }

                                        if (ps2 != null) {
                                            ps2.close();
                                        }
                                        if (rs2 != null) {
                                            rs2.close();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                %>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Category
                                </div>
                                <div>
                                    <%
                                        String sql = "SELECT DISTINCT joke_on FROM joke WHERE joke_on IS NOT NULL";
                                        preparedStatement = connection.prepareStatement(sql);
                                        resultSet = preparedStatement.executeQuery();
                                        out.println("<ul>");
                                        out.println("<li><a href=jokes.jsp?category=All>All Jokes</a></li>");

                                        while (resultSet.next()) {
                                            String Select_category = resultSet.getString("joke_on");
                                    %>
                                    <li><a href="jokes.jsp?category=<%=Select_category%>"><%=convertStringUpperToLower(Select_category)%> Jokes</a></li>
                                        <%
                                            }
                                            out.println("</ul>");
                                        %>
                                </div>
                            </div>  
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Fun Zone
                                </div>
                                <div>
                                    <jsp:include page="funZoneList.jsp"></jsp:include>
                                </div>
                            </div>
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Education Zone
                                </div>
                                <div>
                                <jsp:include page="eduZoneList.jsp"></jsp:include>
                                </div>
                            </div>
                        </div>

                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>
            <%
                } catch (Exception e) {
                    out.println("Error in main try block:-" + e);
                } finally {
                    try {
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
                    } catch (Exception msg) {
                        out.println(msg);
                    }
                }
            %>
            <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> 
    </body>
</html>