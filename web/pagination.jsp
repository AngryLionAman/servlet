<%-- 
    Document   : pagination
    Created on : 1 Sep, 2019, 12:15:34 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <c:if test="${requestScope.tab ne null}">
            ${requestScope.tab} is not null
        </c:if>
        <c:if test="${requestScope.tab eq null}">
            ${requestScope.tab} is null
        </c:if><br>
        <table style="width:100%" border="1">
            <tr>
                <th>S.No</th>
                <th>Question id</th>
                <th>Question</th> 
                <th>Full Name</th> 
                <th>Email</th>
                <th>password</th>
            </tr>
            <c:catch var="exe">
                <c:forEach items="${list}" var="l" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${l.questionId}</td>
                        <td>${l.question}</td> 
                        <td>${l.fullName}</td> 
                        <td>${l.userName}</td> 
                        <td>${l.userType}</td> 
                    </tr>
                </c:forEach>
            </c:catch>
            <c:if test="${exe ne null}">
                ${exe}
            </c:if>

        </table>
        <c:catch var="msg">
            <c:set value="1" var="pageNo"/>
            <c:if test="${param.p ne null}">
                <c:set value="${param.p}" var="pageNo"/>
            </c:if>
            <c:if test="${pageNo gt 1}">
                <a href="getAllQuestion?p=${pageNo - 1}">Pre</a>&nbsp;
            </c:if>
            <c:if test="${totalNumberOfpage <= 15}">
                <c:forEach begin="1" end="${totalNumberOfpage}" step="1" varStatus="loop">
                    <a href="getAllQuestion?p=${loop.count}">${loop.count}</a>&nbsp;
                </c:forEach>
            </c:if>
            <c:if test="${totalNumberOfpage > 15}">
                <c:forEach begin="1" end="8" step="1" varStatus="loop">
                    <a href="getAllQuestion?p=${loop.count}">${loop.count}</a>&nbsp;
                </c:forEach>
                ......
                <c:set scope="page" value="${totalNumberOfpage - 8}" var="startFrom"/>
                <c:forEach begin="${startFrom}" end="${totalNumberOfpage}" step="1">
                    <a href="getAllQuestion?p=${startFrom}">${startFrom}</a>&nbsp;
                    <c:set scope="page" value="${startFrom + 1}" var="startFrom"/>
                </c:forEach>
            </c:if>
            <c:if test="${pageNo lt totalNumberOfpage}">
                <a href="getAllQuestion?p=${pageNo + 1}">Next</a>&nbsp;
            </c:if>
        </c:catch>
        <c:if test="${msg ne null}">
            ${msg}
        </c:if>
    </body>
</html>
