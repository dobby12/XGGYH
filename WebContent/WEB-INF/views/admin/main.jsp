<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<style>

#cont {
	width: 600px;
	height: 400px;
	margin: 0 auto;
	background: #f2f2f2;
	padding: 30px;
}

#loginzone {
	float: right;
	margin : 0 auto;
	width: 60%;
	height: 350px;
	padding: 20px;
}


</style>


<div class="container">

<div id="cont">
	<div style="float: left; width: 40%; height: 350px;"><a href="<%=request.getContextPath() %>/admin"><img id="logo_header_admin" style="width: 150px; height: 150px; margin: 95px auto;" src="/resources/file/logo_square.png" /></a></div>

	
	<div id="loginzone">
	<div style="margin: auto; padding: 20px;">
		<h3>공공연히 관리자 페이지</h3>
		<br>
		
		<c:if test="${empty adminlogin || not adminlogin }">
		<strong>로그인이 필요한 페이지입니다.</strong>
		
		<br>
		
		<form action="<%=request.getContextPath() %>/admin" method="post">
			
			<input type="text" id="adminid" name="adminid" placeholder="ID" style="margin: 10px 0 7px 0;"/>
			<input type="password" id="adminpw" name="adminpw" placeholder="PASSWORD"/><br><%-- @@@type="password" --%>
		
		
		<c:if test="${not empty loginfail }">
		<br>
		<strong>로그인에 실패하였습니다.<br>ID와 PW를 확인하세요.</strong>
		<br>
		</c:if>
		<button style="margin: 25px 0 0 0;">로그인</button>
		</form>
		
		</c:if><!-- empty adminlogin end -->
		
	</div><!-- #log end -->
	
	<div id="loggedin">	
		<c:if test="${adminlogin }">
		관리자 아이디 : ${adminid }<br>
		관리자 이름 : ${adminname }<br> 
		관리자 상태? : ${adminauthority }<br>
		<hr>
			<a href="<%=request.getContextPath() %>/admin/logout"><button>로그아웃</button></a>
		<hr>
		</c:if>
	</div><!-- #loggedin end -->
	</div><!-- .loginzone end -->
</div><!-- #cont end -->
</div><!-- .container end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />