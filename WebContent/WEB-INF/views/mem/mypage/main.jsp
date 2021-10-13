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
		location.href="<%=request.getContextPath() %>";
	});
	
	$("#userdel").click(function() {
		location.href="<%=request.getContextPath() %>";
	});
	
	$("#myrev").click(function() {
		location.href="<%=request.getContextPath() %>";
	});
	
	$("#myjim").click(function() {
		location.href="<%=request.getContextPath() %>";
	});
	
	$("#mycal").click(function() {
		location.href="<%=request.getContextPath() %>";
	});
	
	$("#notice").click(function() {
		location.href="<%=request.getContextPath() %>/notice";
	});
	
	$("#askwrite").click(function() {
		location.href="<%=request.getContextPath() %>";
	});
	
	$("#asklist").click(function() {
		location.href="<%=request.getContextPath() %>";
	});
	
});
</script>

<meta charset="UTF-8">
<title>마이 페이지</title>

<div class="container">

<h2>마이 페이지</h2>
<hr>

<h3><%=id %>(<%=nick %>)님 안녕하세요.</h3>

<table style = none>
	<tr>
	<td><h4>회원정보</h4>
	<button type="button" id="myinfo" class="btn-fill-s">계정 관리</button><br>
	<button type="button" id="userdel" class="btn-fill-s">회원 탈퇴</button><br>
	<td><h4>회원활동</h4>
	<button type="button" id="myrev" class="btn-fill-s">내가 리뷰한 공연</button><br>
	<button type="button" id="myjim" class="btn-fill-s">내가 찜한 공연</button><br>
	<button type="button" id="mycal" class="btn-fill-s">마이 캘린더</button><br>
	<td><h4>고객센터</h4>
	<button type="button" id="notice" class="btn-fill-s">공지사항</button><br>
	<button type="button" id="askwrite" class="btn-fill-s">1:1문의</button><br>
	<button type="button" id="asklist" class="btn-fill-s">문의내역</button><br>
	</tr>
</table>

</div>