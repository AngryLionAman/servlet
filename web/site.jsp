<%!
    String DB_URL_ = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    String DB_USERNAME_ = "root";
    String DB_PASSWORD_ = null;
    String DB_AJAX_PATH = "http://localhost:8080/inquiryhere";
%>
<sql:setDataSource var="dbsource" 
                   driver="com.mysql.jdbc.Driver"                           
                   url="jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8"
                   user="root"  
                   password=""/>