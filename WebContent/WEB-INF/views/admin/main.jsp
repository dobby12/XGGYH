<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<style>
.container {
	height: 400px;
	width: 600px;
	

}
.logozone {
	float:left;
	margin: 0 0 0 50px;
	
}

.loginzone {
	float: right;
	margin : 0 50px 10px 0;
}


</style>


<div class="container" style="background-color: #f2f2f2">


<div class="logozone">
<a href="<%=request.getContextPath() %>/admin"><img id="logo_header_admin" class="logoA" width="150px" height="150px" src="/resources/file/logo_square.png" /></a>

</div>

<div class="loginzone">
<h3>공공연히 관리자 페이지</h3>

<c:if test="${empty adminlogin || not adminlogin }">
로그인이 필요한 페이지입니다.

<br>




<form action="<%=request.getContextPath() %>/admin" method="post">
<div>
	
	<div class="mb-3">
		<input type="text" id="adminid" name="adminid" placeholder="ID"/><br>
	</div>
</div>
<div>
	<div class="mb-3">
		<input type="text" id="adminpw" name="adminpw" placeholder="PASSWORD"/><br><%-- @@@type="password" --%>
	</div>
</div>

<br>
<c:if test="${not empty loginfail }">
로그인에 실패하였습니다. ID와 PW를 확인하세요.
</c:if>
<br>
<button>로그인</button>
</form>

</c:if>
</div>

<c:if test="${adminlogin }">
관리자 아이디 : ${adminid }<br>
관리자 이름 : ${adminname }<br> 
관리자 상태? : ${adminauthority }<br>
<hr>
	<a href="<%=request.getContextPath() %>/admin/logout"><button>로그아웃</button></a>
<hr>
</c:if>

</div><!-- .container end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />