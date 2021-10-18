<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<div class="container">

<a href="<%=request.getContextPath() %>/admin"><img id="logo_header" class="logoA" src="/resources/file/logo_square.png" height: /></a>
<h1>관리자 로그인</h1>


<c:if test="${empty adminlogin || not adminlogin }">
로그인이 필요한 페이지입니다.

<br>

<c:if test="${not empty loginfail }">
로그인에 실패하였습니다. ID와 PW를 확인하세요.
</c:if>

<br>

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

<br>
<button>LOGIN</button>
</form>

</c:if>

<c:if test="${adminlogin }">
관리자 아이디 : ${adminid }<br>
관리자 이름 : ${adminname }<br> 
관리자 상태? : ${adminauthority }<br>
<hr>
	<strong>로그인 한 상태일 때 보여질 영역</strong><br>
	<a href="<%=request.getContextPath() %>/admin/logout"><button>LOGOUT</button></a>
<hr>
</c:if>
</div><!-- .container end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />