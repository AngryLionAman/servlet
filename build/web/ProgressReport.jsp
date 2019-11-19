<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Progress Report</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="noindex, nofollow" />
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <style>
            * {
                box-sizing: border-box;
            }

            h2{
                text-align: center;
                color: black;
            }
            p{
                text-align: center;
                color: #ff0099;
            }
            /* Create three equal columns that floats next to each other */
            .column {
                float: left;
                width: 24.33%;
                padding: 10px;
                height: 200px; /* Should be removed. Only for demonstration */
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }

            /* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
            @media screen and (max-width: 600px) {
                .column {
                    width: 100%;
                }
            }
        </style>
    </head>
    <body style="font-size: 25px;color: #ff0099;text-align: center;">
        <c:catch var="msg">
            <h2 style="font-style: oblique;">Progress Report of inquiryhere.com</h2>
            <h2>Total Question</h2>
            <sql:query var="question" dataSource="jdbc/mydatabase">
                select count(*) totalquestion from question;
            </sql:query>
            <c:forEach var="q" items="${question.rows}">
                <p style="font-size: 60px;"><c:out value="${q.totalquestion}"/></p>
            </c:forEach>


            <h2>Total Answer</h2>
            <sql:query var="answer" dataSource="jdbc/mydatabase">
                select count(*) totalanswer from answer;
            </sql:query>
            <c:forEach var="a" items="${answer.rows}">
                <p style="font-size: 60px;"><c:out value="${a.totalanswer}"/></p>
            </c:forEach>


            <h2>Total User</h2>
            <sql:query var="user" dataSource="jdbc/mydatabase">
                select count(*) totaluser from newuser;
            </sql:query>
            <c:forEach var="u" items="${user.rows}">
                <p style="font-size: 60px;"><c:out value="${u.totaluser}"/></p>
            </c:forEach>


            <h2>Last 10 Question posted</h2>
            <sql:query var="lq" dataSource="jdbc/mydatabase">
                select * from question order by q_id desc limit 10;
            </sql:query>
            <c:forEach var="l" items="${lq.rows}">
                <c:out value="${l.question}"/>(${l.total_view}),${l. posted_time}<br><br>
            </c:forEach>


            <h2>Most 10 viewed question</h2>
            <sql:query var="mvq" dataSource="jdbc/mydatabase">
                select q_id,question,total_view,posted_time from question order by total_view desc limit 10;
            </sql:query>
            <c:forEach var="mq" items="${mvq.rows}">
                ${mq.question} (${mq.total_view}), ${mq.posted_time}<br><br>
            </c:forEach>


            <h2>Most 10 viewed answer</h2>
            <c:catch var="ms">
                <sql:query var="mva" dataSource="jdbc/mydatabase">
                     select q_id, a_id , total_view , postedtime from answer order by total_view desc limit 10;
                </sql:query>
                    question id, answer id ,  totla view<br>
                <c:forEach var="mq" items="${mva.rows}">
                    ${mq.q_id}, ${mq.a_id}, (${mq.total_view}), ${mq.postedtime}<br><br>
                </c:forEach>
            </c:catch>
            <c:if test="${ms ne null}">
                ${ms}
            </c:if>



            <h2>Last 10 user created</h2>
            <sql:query var="lu" dataSource="jdbc/mydatabase">
                select * from newuser order by id desc limit 10;
            </sql:query>
            <c:forEach var="l" items="${lu.rows}">
                <c:out value="${l.firstname}"/>(${l.total_view}), ${l.date_time}<br><br>
            </c:forEach>


            <h2>Most 10 viewed Profile</h2>
            <sql:query var="mvf" dataSource="jdbc/mydatabase">
                select firstname,total_view,date_time from newuser order by total_view desc limit 10;
            </sql:query>
            <c:forEach var="mf" items="${mvf.rows}">
                ${mf.firstname} (${mf.total_view}), ${mf.date_time}<br><br>
            </c:forEach>


            <h2>Searched query</h2>
            <sql:query var="searched_queary" dataSource="jdbc/mydatabase">                     
                select * from searched_queary order by primary_key desc limit 20;
            </sql:query>
            <c:forEach var="sq" items="${searched_queary.rows}">
                ${sq.searched_queary},
            </c:forEach>

            <h2>Unanwered question</h2>
            <sql:query var="unq" dataSource="jdbc/mydatabase">                     
                select q_id,question,(select count(a_id) from answer where q_id = question.q_id)t_ans 
                from question where (select count(a_id) from answer where q_id = question.q_id) = 0;
            </sql:query>
            <c:forEach var="uq" items="${unq.rows}" varStatus="c">
                (${c.count})  ${uq.q_id} : ${uq.question} <br><br>
            </c:forEach>

            <h2>Latest 10 comments</h2>
            <sql:query var="comment" dataSource="jdbc/mydatabase">                     
                select * from comments order by unique_id desc limit 10;
            </sql:query>
            <c:forEach var="c" items="${comment.rows}">
                <p style="color: red;"> ${c.comments}</p>, Commented on - 
                <c:choose>
                    <c:when test="${c.q_id ne null}">
                        Question and question id : ${c.q_id}
                    </c:when>
                    <c:when test="${c.ans_id ne null}">
                        Answer and answer id :${c.ans_id}
                    </c:when>
                    <c:when test="${c.blog_id ne null}">
                        Blog and blog id :${c.blog_id}
                    </c:when>
                    <c:when test="${c.userprofileid ne null}">
                        Profile and user id :${c.userprofileid}
                    </c:when>
                    <c:otherwise>
                        Undefined
                    </c:otherwise>
                </c:choose>, Commented By Id - ${c.user_id},${c.time}<br><br>
            </c:forEach>
        </c:catch>
        <c:if test="${msg ne null}">
            ${msg}
        </c:if>
    </body>
</html>

