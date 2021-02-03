<html lang="en"><head>
        <meta charset="UTF-8">
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Get Youtube subscriber | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@page import="java.sql.*" %> 
        <%@include file="site.jsp" %>
        <%!            String WELCOME_NOTES = "";
            String WELCOME_NOTES_DESCRIPTION = "";
        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                WELCOME_NOTES = "inquiryhere.com में आपकी रुचि के लिए धन्यवाद";
                WELCOME_NOTES_DESCRIPTION = "प्रिय उपयोगकर्ता, अगर आपके पास INQUIRYHERE.COM के बारे में कोई प्रश्न और सुझाव है। तो आप inquiry@inquiryhere.com पर एक मेल छोड़ सकते हैं या आप सीधे अनुभाग से नीचे पोस्ट कर सकते हैं। हम जल्द से जल्द आपकी मदद करेंगे।";

            } else {
                WELCOME_NOTES = "Thanks for your interest in inquiryhere.com";
                WELCOME_NOTES_DESCRIPTION = "Dear user, If you have any query and suggestion about inquiryhere.com then you can drop a mail on inquiry@inquiryhere.com or you can directly post from below section.We will help you as soon as possible.";
            }
        %>

    </head>

    <body>
        <div class="main-page-wrapper">

            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="themeBox" style="height:auto;">
                                    <center><div class="boxHeading">
                                            Get more YouTube subscriber
                                        </div></center>
                                    <form action="saveyoutubesubscriber.jsp">
                                        <div>Channel Link <input type="text"  placeholder="Enter the YouTube channel link" name="channel_link" width="auto" required=""></div>
                                        <div>Latest video link <input type="text"  placeholder="Enter latest video link" name="video_line" required=""></div>
                                        <div>WhatsApp Number <input type="text"  placeholder="Enter the WhatsApp mobile number" name="whatsapp_number" required=""></div>
                                        <div class="float-right margintop20" style="vertical-align:bottom">
                                            <button type="submit" class="btn" data-toggle="modal" name="submit_button" >Click to save</button>
                                        </div>
                                    </form>
                                    <div class="clear-fix"></div>
                                </div>
                                        <%!    public int nullIntconvert(String str) {
                                                int num = 0;
                                                if (str == null) {
                                                    str = "0";
                                                } else if ((str.trim()).equals("null")) {
                                                    str = "0";
                                                } else if (str.equals("")) {
                                                    str = "0";
                                                }
                                                try {
                                                    num = Integer.parseInt(str);
                                                } catch (Exception e) {
                                                }
                                                return num;
                                            }
                                        %>
                                        <%
                                            ResultSet rs1 = null;
                                            ResultSet rs2 = null;
                                            ResultSet rs3 = null;
                                            PreparedStatement ps1 = null;
                                            PreparedStatement ps2 = null;
                                            PreparedStatement ps3 = null;
                                            Connection connection = null;

                                            int showRows = 10;
                                            int totalRecords = 5;
                                            int totalRows = nullIntconvert(request.getParameter("totalRows"));
                                            int totalPages = nullIntconvert(request.getParameter("totalPages"));
                                            int iPageNo;
                                            iPageNo = nullIntconvert(request.getParameter("iPageNo"));
                                            int cPageNo = nullIntconvert(request.getParameter("cPageNo"));

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

                                                String query1 = "SELECT SQL_CALC_FOUND_ROWS * FROM youtube ORDER BY unique_id DESC LIMIT " + iPageNo + "," + showRows + "";
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
                                               YouTube Channel Link -> <a href="<%=rs1.getString("channel_link")%>" target="_blank"><%=rs1.getString("channel_link")%></a><br>
                                               Latest Video Link -> <a href="<%=rs1.getString("latest_video_link")%>" target="_blank"><%=rs1.getString("latest_video_link")%></a><br>
                                                WhatsApp Number -> <%=rs1.getString("whatsapp_number")%>
                                            </div>

                                            <%
                                                    }
                                                } catch (Exception e) {
                                                    out.println(e);
                                                }

                                            %>

                                            <%                                            
                                                try {
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
                                                            <a href="getyoutubesubscriber.jsp?iPageNo=<%=prePageNo%>&cPageNo=<%=prePageNo%>&sl=<%=sl%>"> << Previous</a>
                                                            <%
                                                                }
                                                                for (i = ((cPage * totalRecords) - (totalRecords - 1)); i <= (cPage * totalRecords); i++) {
                                                                    if (i == ((iPageNo / showRows) + 1)) {%>
                                                            <a href="getyoutubesubscriber.jsp?iPageNo=<%=i%>&sl=<%=sl%>" style="cursor:pointer;color:red"><b><%=i%></b></a>
                                                                    <%
                                                                    } else if (i <= totalPages) {
                                                                    %>
                                                            <a href="getyoutubesubscriber.jsp?iPageNo=<%=i%>&sl=<%=sl%>"><%=i%></a>
                                                            <%
                                                                    }
                                                                }
                                                                if (totalPages > totalRecords && i < totalPages) {
                                                            %>
                                                            <a href="getyoutubesubscriber.jsp?iPageNo=<%=i%>&cPageNo=<%=i%>&sl=<%=sl%>"> >> Next</a>
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
                                                if (connection != null) {
                                                    connection.close();
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

                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>

            <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div>

    </body></html>