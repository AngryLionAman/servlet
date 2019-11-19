<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:forEach items="${allQuestionByTopicId}" var="rq" varStatus="loop">
    <c:set scope="page" value="${loop.count}" var="count"/>
    <a href="Answer.jsp?q=${fn:replace(fn:replace(rq.value, "|", ""), " ", "-")}&Id=${rq.key}" >&nbsp;${rq.value}?</a><br><br>
</c:forEach>
<c:if test="${count eq 0}">
    Opps !!!!! No question found related to this topic...
</c:if>

<c:forEach items="${randomQuestionByLimit}" var="rq">
    <a href="Answer.jsp?q=${fn:replace(fn:replace(rq.value, "|", ""), " ", "-")}&Id=${rq.key}" >&nbsp;${rq.value}?</a><br><br>                                           
</c:forEach>

<c:forEach items="${topicDetailByRefId}" var="rt" varStatus="loop">  
    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="border: #000000; border-style: solid;">
        <span title="Total followers of ${rt.topicName} is ${rt.totalFollowers} and total question is ${rt.relatedQuestion}"><a href="topic.jsp?t=${fn:replace(rt.topicName, ' ', '-')}&id=${rt.topicId}">
                <c:choose>
                    <c:when test="${rt.imageUrl ne null or not empty rt.imageUrl}">
                        <img src="${rt.imageUrl}" alt="inquiryhere.com" height="100" width="100">
                    </c:when>
                    <c:otherwise>
                        <img src="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" alt="inquiryhere.com" height="100" width="100">
                    </c:otherwise>
                </c:choose>       
                ${word.convertStringUpperToLower(rt.topicName)}</a>
        </span>
    </div>  
</c:forEach> 