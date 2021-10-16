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
		location.href="<%=request.getContextPath() %>";
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
	padding-bottom: 20px;
}

td {
	padding-bottom: 10px;
}

button {
	width: 50%;
	height: 100px;
}

</style>

<div class="container">

<h2>마이 페이지</h2>
<hr>
<br>

<h3><%=nick %>님 안녕하세요!</h3>

<br>

<table>
	<tr>
		<th>회원정보</th>
		<th>회원활동</th>
		<th>고객센터</th>
		
	</tr>
	
	<tr>
		<td><button type="button" id="myinfo" class="btn-fill-s">계정 관리</button></td>
		<td><button type="button" id="myrev" class="btn-fill-s">내가 리뷰한 공연</button></td>
		<td><button type="button" id="notice" class="btn-fill-s">공지사항</button></td>
	</tr>
	
	<tr>
		<td><button type="button" id="userdel" class="btn-fill-s">회원 탈퇴</button></td>
		<td><button type="button" id="myjim" class="btn-fill-s">내가 찜한 공연</button></td>
		<td><button type="button" id="askwrite" class="btn-fill-s">1:1문의</button></td>
	</tr>
	
	<tr>
		<td></td>
		<td><button type="button" id="mycal" class="btn-fill-s">마이 캘린더</button></td>
		<td><button type="button" id="asklist" class="btn-fill-s">문의내역</button></td>
	</tr>

</table>

</div>