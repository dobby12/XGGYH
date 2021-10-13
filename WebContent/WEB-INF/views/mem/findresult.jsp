<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>

<h1>FIND ID/PW</h1>
@@@영문입력만 필터링
<c:if test="${id }">
<c:if test="${alert ne null }">
${alert }<br>
</c:if>
<c:if test="${alert eq null }">
ID 찾기 결과 : ${findid }
</c:if>
</c:if>
<c:if test="${id eq null }">
PW 재발급 메일이 발송되었습니다.
</c:if>


</body>
</html>