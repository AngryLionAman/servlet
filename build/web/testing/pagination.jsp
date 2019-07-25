<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/dbname" user="root" password="dbpass" />
 
<sql:query dataSource="jdbc/mydatabase" var="categories" scope="session">
       select unique_id,topic_name from topic limit 50;
</sql:query>
 
<c:import url="dispresult.jsp?pageNumber=1"/>