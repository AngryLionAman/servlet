<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Progress Report</title>
        <!--meta http-equiv="refresh" content="3"-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            * {
                box-sizing: border-box;
            }

            h2{
                text-align: center;
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
    <body>

        <h2>Progress Report of inquiryhere.com</h2>
        <div class="row">
            <div class="column" style="background-color:#aaa;">
                <h2>Total Question</h2>
                <sql:query var="question" dataSource="jdbc/mydatabase">
                    select count(*) totalquestion from question;
                </sql:query>
                <c:forEach var="q" items="${question.rows}">
                    <p style="font-size: 60px;"><c:out value="${q.totalquestion}"/></p>
                </c:forEach>
            </div>
            <div class="column" style="background-color:#bbb;">
                <h2>Total Answer</h2>
                <sql:query var="answer" dataSource="jdbc/mydatabase">
                    select count(*) totalanswer from answer;
                </sql:query>
                <c:forEach var="a" items="${answer.rows}">
                    <p style="font-size: 60px;"><c:out value="${a.totalanswer}"/></p>
                </c:forEach>
            </div>
            <div class="column" style="background-color:#ccc;">
                <h2>Total User</h2>
                <sql:query var="user" dataSource="jdbc/mydatabase">
                    select count(*) totaluser from newuser;
                </sql:query>
                <c:forEach var="u" items="${user.rows}">
                    <p style="font-size: 60px;"><c:out value="${u.totaluser}"/></p>
                </c:forEach>
            </div>
            <div class="column" style="background-color:#ddd;">
                <h2>Panding</h2>
                <p style="font-size: 30px;"><%="panding"%></p>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="column" style="background-color:#aaa;">
                <h2>Last Question posted</h2>
                <sql:query var="lq" dataSource="jdbc/mydatabase">
                    select * from question order by q_id desc limit 1;
                </sql:query>
                <c:forEach var="l" items="${lq.rows}">
                    <p style="font-size: 20px;"><c:out value="${l.question}"/>(${l.total_view})</p>
                </c:forEach>
            </div>
            <div class="column" style="background-color:#bbb;">
                <h2>Last user created</h2>
                <sql:query var="lu" dataSource="jdbc/mydatabase">
                    select * from newuser order by id desc limit 1;
                </sql:query>
                <c:forEach var="l" items="${lu.rows}">
                    <p style="font-size: 30px;"><c:out value="${l.firstname}"/>(${l.total_view})</p>
                </c:forEach>
            </div>
            <div class="column" style="background-color:#ccc;">

                <h2>Most view question</h2>
                <sql:query var="mvq" dataSource="jdbc/mydatabase">
                    select q_id,question,total_view from question order by total_view desc limit 1;
                </sql:query>
                <c:forEach var="mq" items="${mvq.rows}">
                    <p style="font-size: 25px;">${mq.question} (${mq.total_view})</p>
                </c:forEach>

            </div>
            <div class="column" style="background-color:#ddd;">
                <h2>Most view Profile</h2>
                <sql:query var="mvf" dataSource="jdbc/mydatabase">
                    select firstname,total_view from newuser order by total_view desc limit 1;
                </sql:query>
                <c:forEach var="mf" items="${mvf.rows}">
                    <p style="font-size: 25px;">${mf.firstname} (${mf.total_view})</p>
                </c:forEach>
            </div>
            <div class="column" style="background-color:#ddd;">
                <h2>Searched query</h2>
                <sql:query var="searched_queary" dataSource="jdbc/mydatabase">                     
                    select * from searched_queary order by primary_key desc limit 20;
                </sql:query>
                <c:forEach var="sq" items="${searched_queary.rows}">
                    ${sq.searched_queary},
                </c:forEach>
                <br>------------------------------------------<br>
            </div>
        </div>
        <div>
            <sql:query var="unq" dataSource="jdbc/mydatabase">                     
                select q_id,question,(select count(a_id) from answer where q_id = question.q_id)t_ans 
                from question where (select count(a_id) from answer where q_id = question.q_id) = 0;
            </sql:query><br><br><br>
            <c:forEach var="uq" items="${unq.rows}" varStatus="c">
            (${c.count})  ${uq.q_id} : ${uq.question} <br>
            </c:forEach>
        </div>
    </body>
</html>

