<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <c:if test="${sessionScope.Session_id_of_user eq null}">
                    <h4 class="modal-title">Post question as guest</h4>    
                </c:if>
                <c:if test="${sessionScope.Session_id_of_user ne null}">
                    <h4 class="modal-title">Post your question here</h4>    
                </c:if>

            </div>
            <c:if test="${sessionScope.Session_id_of_user eq null}">
                <form name="form_name_guest_submitquestion" method="get" action="<%=request.getContextPath()%>/guestSaveQuestion">
                </c:if>
                <c:if test="${sessionScope.Session_id_of_user ne null}">
                    <form name="form_name_submitquestion" method="post" action="savequestion">
                        <input type="hidden" name="userid" value="<%=session.getAttribute("Session_id_of_user")%>">
                    </c:if>                        
                    <div class="modal-body">
                        <div>
                            <div>Put your question here <i style="color: red;">*</i></div>
                            <textarea type="text" class="anstext" name="question" placeholder="Ex: What is,How to.." required=""></textarea>
                        </div>
                        <div class="margintop20">
                            <div>Tag suggestion description <i style="color: red;">*</i></div>
                            <textarea type="text" class="anstext" name="tag_of_question" placeholder="Ex:Java,Database,c language" required=""></textarea>
                        </div>
                        <c:if test="${sessionScope.Session_id_of_user eq null}">
                            <div class="margintop20">
                                <div>Guest Name </div>
                                <textarea type="text" name="guestName" placeholder="Aman Kumar"></textarea>
                            </div>
                            <div class="margintop20">
                                <div>Guest Email (Will not display publicly) </div>
                                <textarea type="email" name="guestEmail" placeholder="email@gmail.com"></textarea>
                            </div>
                        </c:if>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn">POST</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>
                    </div>
                </form>
        </div>
    </div>
</div>
<footer style="text-align: center;">
    <a href="index.jsp" style="color: white;">Home</a> &nbsp;&nbsp;
    <a href="privacy.jsp" style="color: white;">Privacy Policy</a> &nbsp;&nbsp;
    <a href="terms.jsp" style="color: white;">Terms</a> &nbsp;&nbsp;
    <a href="help.jsp" style="color: white;">Help</a> &nbsp;&nbsp;
    <a href="AboutUs.jsp" style="color: white;">About Us</a>   &nbsp;&nbsp; 
    <a href="ContactUs.jsp" style="color: white;">Contact Us</a> &nbsp;&nbsp;
</footer>