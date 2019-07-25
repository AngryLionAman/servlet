<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${4 eq 45}">
    <c:set var="name" value="Aman kumar"/>
</c:if>
<c:out value="${name}" default="0"></c:out>