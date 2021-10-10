<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!------------------------------------------------------>

<h1>LOGIN</h1>
<%-- <c:if test="${not empty loginfail }">
로그인에 실패하였습니다. ID와 PW를 확인하세요.
</c:if> --%>

<form action="<%=request.getContextPath() %>/login" method="post">
<div>
	<label for="memid">ID</label>
	<div>
		<input type="text" id="memid" name="memid"/><br>
	</div>
</div>
<div>
	<label for="mempw">PW</label>
	<div>
		<input type="text" id="mempw" name="mempw"/><br><%-- @@@type="password" --%>
	</div>
</div>
	<button>LOGIN</button>
</form>

<!------------------------------------------------------>
</body>
</html>