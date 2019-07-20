<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %>

<sql:setDataSource var="dataSource" driver="org.gjt.mm.mysql.Driver"
url="jdbc:mysql://localhost/forum?user=forumuser"
scope="session" />

<html>
  <head>
    <title>Query Example</title>
  </head>

  <body>

<sql:query var = "users" dataSource="dataSource">
select column_uid,column_pwd,column_accesses,column_first,column_last,column_bad,column_posted,column_type from t_users
</sql:query>

<table border=1>
<c:forEach var="row" items="users">
<tr>
<td><c:out value="${row.column_uid}"/></td>
<td><c:out value="${row.column_pwd}"/></td>
<td><c:out value="${row.column_accesses}"/></td>
<td><c:out value="${row.column_first}"/></td>
<td><c:out value="${row.column_last}"/></td>
<td><c:out value="${row.column_bad}"/></td>
<td><c:out value="${row.column_posted}"/></td>
<td><c:out value="${row.column_type}"/></td>
</tr>
</c:forEach>
</table>


  </body>
</html>

