<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>ADMIN MAIN + LOGIN</h1>


<c:if test="${empty login || not login }">
로그인 상태 아님


<c:if test="${not empty loginfail }">
로그인에 실패하였습니다. ID와 PW를 확인하세요.
</c:if>

<form action="<%=request.getContextPath() %>/admin" method="post">
<div>
	<label for="adminid">ID</label>
	<div>
		<input type="text" id="adminid" name="adminid"/><br>
	</div>
</div>
<div>
	<label for="adminpw">PW</label>
	<div>
		<input type="text" id="adminpw" name="adminpw"/><br><%-- @@@type="password" --%>
	</div>
</div>
	<button>LOGIN</button>
</form>

</c:if>

<c:if test="${login }">
관리자 아이디 : ${adminid }<br>
관리자 이름 : ${adminname }<br> 
관리자 상태? : ${adminauthority }<br>
<hr>
	<strong>로그인 한 상태일 때 보여질 영역</strong><br>
	<a href="<%=request.getContextPath() %>/admin/logout"><button>LOGOUT</button></a>
	<a href="<%=request.getContextPath() %>/admin/notice/list"><button>NOTICE LIST</button></a>
<hr>
</c:if>


</body>
</html>