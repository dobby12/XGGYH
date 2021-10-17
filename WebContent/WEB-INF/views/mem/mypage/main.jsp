<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<%
	String id = (String)session.getAttribute("memid");
	String nick = (String)session.getAttribute("memnick");
%>

<script type="text/javascript">
$(document).ready(function() {
	
	//버튼 이동
	$("#myinfo").click(function() {
		location.href="<%=request.getContextPath() %>/mypage/myinfo";
	});
	
	$("#userdel").click(function() {
		location.href="<%=request.getContextPath() %>/mypage/myinfo/delete";
	});
	
	$("#myrev").click(function() {
		location.href="<%=request.getContextPath() %>/mypage/myreview";
	});
	
	$("#myjim").click(function() {
		location.href="<%=request.getContextPath() %>/mypage/myjjim";
	});
	
	$("#mycal").click(function() {
		location.href="<%=request.getContextPath() %>/mycalendar";
	});
	
	$("#notice").click(function() {
		location.href="<%=request.getContextPath() %>/notice";
	});
	
	$("#askwrite").click(function() {
		location.href="<%=request.getContextPath() %>/mypage/myask/write";
	});
	
	$("#asklist").click(function() {
		location.href="<%=request.getContextPath() %>/mypage/myask";
	});
	
});
</script>

<style type="text/css">

th {
	text-align: center;
	padding-bottom: 20px;
}

td {
	padding-bottom: 10px;
}

button {
	width: 150px;
	height: 75px;
}

</style>

<div class="container">

<h2>마이 페이지</h2>
<hr>

<h3><strong><%=nick %>님 안녕하세요!</strong></h3>

<br>
<table>
	<tr>
		<th><h4><strong>회원정보</strong></h4></th>
		<th><h4><strong>회원활동</strong></h4></th>
		<th><h4><strong>고객센터</strong></h4></th>
		
	</tr>
	
	<tr>
		<td><button type="button" id="myinfo">계정 관리</button></td>
		<td><button type="button" id="myrev">내가 작성한 리뷰</button></td>
		<td><button type="button" id="notice">공지사항</button></td>
	</tr>

	<tr>
		<td><button type="button" id="userdel">회원 탈퇴</button></td>
		<td><button type="button" id="myjim">내가 찜한 공연</button></td>
		<td><button type="button" id="askwrite">1:1문의</button></td>
	</tr>

	<tr>
		<td></td>
		<td><button type="button" id="mycal">마이 캘린더</button></td>
		<td><button type="button" id="asklist">문의내역</button></td>
	</tr>

</table>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />