<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="dto.XNotice"%>
<%@page import="dao.face.MemberDao"%>    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
form {
	width: 400px;
	margin: 0 auto;
}
.btn {
	height:40px;
}
#aside {
	width:300px;
	height:auto;
	float: left;
}
#section {
	width:800px;
	height:auto;
	float: left;
}
</style>
</head>
<body>

<div id="aside">
<table style = none>
	<tr>
	<td><h4>회원정보</h4>
		<button type="button" id="myinfo" class="btn">계정 관리</button><br>
		<button type="button" id="userdel" class="btn">회원 탈퇴</button><br>
	</td>
	</tr>
	<tr>
	<td><h4>회원활동</h4>
		<button type="button" id="myrev" class="btn">내가 리뷰한 공연</button><br>
		<button type="button" id="myjim" class="btn">내가 찜한 공연</button><br>
		<button type="button" id="mycal" class="btn">마이 캘린더</button><br>
	</td>
	</tr>
	<tr>
	<td><h4>고객센터</h4>
		<button type="button" id="notice" class="btn">공지사항</button><br>
		<button type="button" id="askwrite" class="btn">1:1문의</button><br>
		<button type="button" id="asklist" class="btn">문의내역</button><br>
	</tr>
</table>
</div>

<!-- 회원 정보 탈퇴 구현 -->
<div id="section">

<h3>회원정보 탈퇴</h3>
<hr>
	<form name="deleteform" method="post" action="deletepro.jsp"
	onsubmit="return checkValue()">
		<table>
		<tr>
			<td bgcolor="skyblue">비밀번호</td>
			<td><input type="password" name="password" maxlength="50"></td>
		</tr>
		</table>
		<br>
		<input type="button" value="취소" onclick="javascript:window.location='MainForm.jsp'">
		<input type="submit" value="탈퇴" /> 
	</form>
</div>
</body>
</html>