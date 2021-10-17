<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>

<style>
.container {
	width: 400px;
}
</style>


<div class="container">
<br><br><br><br><br><br><br><br>
<h2>아이디/비밀번호 찾기</h2>
<hr>
<c:if test="${id }">
	<c:if test="${noMailId ne null }">
	${noMailId }
	</c:if>
	<c:if test="${noMailId eq null }">
	가입하신 아이디는 ${findid } 입니다.
	</c:if>
</c:if>

<c:if test="${pw }">
	<c:if test="${noMailPw ne null }">
	${noMailPw }
	</c:if>
	<c:if test="${noMailPw eq null }">
	 임시 비밀번호가 전송되었습니다. 이메일을 확인해주세요!	
	</c:if>
</c:if>
<br><br><br>
<a href="<%=request.getContextPath() %>/member/find"><button>이전으로 돌아가기</button></a>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />