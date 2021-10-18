<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>

<script>
$(document).ready(function() {
	$("#btnList").click(function(){
		
		history.back();
		
	})

})



</script>

<style>
.container {
	width: 400px;
}
</style>


<div class="container">
<br><br><br><br><br><br><br><br>
<h3>메일 전송이 완료되었습니다</h3>
<br><br><br>
<a href="<%=request.getContextPath() %>/admin/mail/mem/list"><button id="btnBack">이전으로 돌아가기</button></a>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />