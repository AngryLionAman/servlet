<%-- 
    Document   : approva_by_admin
    Created on : 28 Dec, 2019, 2:38:19 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.adminUserId eq null}">
    <c:redirect url="Admin/visit.jsp?msg=Session is not valid"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Question Approval</title>
        <style>
            td{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Hello,   ${sessionScope.userName} <br>Request for modified Question approval !!!
        <a href="<%=request.getContextPath()%>/approval_request_for_admin_servlet">Refresh</a>
            <a href="<%=request.getContextPath()%>/Admin/adminModule.jsp">HOME</a>
        </h1>

        <c:if test="${message ne null}">
            <div class="clear-fix" align="center" style="font-size: 20px;color: green;background-color: yellow;">
                ${message}   
            </div><br>
        </c:if>

        <c:choose>
            <c:when test="${approvalForQuestion ne null and not empty approvalForQuestion}">
                <table border="1">
                    <tr>
                        <th>O_Q_ID</th>
                        <th>Old_Q</th>
                        <th>N_Q_ID</th>
                        <th>NEW_Q</th>
                        <th>A_BY_USER</th>
                        <th>A_BY_ADMIN</th>
                        <th>R_BY_USER</th>
                        <th>R_BY_ADMIN</th>
                        <th>MESSAGE</th>
                        <th>DATE</th>
                        <th>ACTION</th>
                    </tr>
                    <c:forEach items="${approvalForQuestion}" var="q">
                        <tr>
                            <td>${q.old_question_id}</td>
                            <td>${q.old_question}</td>
                            <td>${q.new_question_id}</td>
                            <td>${q.new_question}</td>
                            <td>${q.approve_by_user}</td>
                            <td>${q.approve_by_admin}</td>
                            <td>${q.rejected_by_user}</td>
                            <td>${q.rejected_by_admin}</td>
                            <td>${q.message}</td>
                            <td>${q.date}</td>
                            <td><a href="<%=request.getContextPath()%>/action_servlet_question_approval?action=Accept&o_q_id=${q.old_question_id}&n_q_id=${q.new_question_id}">Accept</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/action_servlet_question_approval?action=Delete&o_q_id=${q.old_question_id}&n_q_id=${q.new_question_id}">Reject</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                ${'No question is panding for apprval'}
            </c:otherwise>
        </c:choose>

        <c:if test="">

        </c:if>

    </body>
</html>
