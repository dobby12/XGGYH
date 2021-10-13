<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>

<h1>FIND ID/PW</h1>
<c:if test="${id }">
	<c:if test="${noMailId ne null }">
	${noMailId }<br>
	</c:if>
	<c:if test="${noMailId eq null }">
	ID 찾기 결과 : ${findid }
	</c:if>
</c:if>

<c:if test="${pw }">
	<c:if test="${noMailPw ne null }">
	${noMailPw }
	</c:if>
	<c:if test="${noMailPw eq null }">
	PW 재발급 메일이 발송되었습니다. 재발급된 비밀번호로 로그인 후 비밀번호를 변경하세요.	
	</c:if>
</c:if>


</body>
</html>