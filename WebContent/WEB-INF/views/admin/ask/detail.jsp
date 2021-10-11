<%@page import="dto.XAsk"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	XAsk xask = (XAsk) request.getAttribute("xask");
%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">

$(document).ready(function(){
	
	//목록 버튼 동작
	$("#btnList").click(function(){
		history.go(-1);
	});


</script>

<style type="text/css">

th {
	height: 50px;
	
}

td {
	border: 1px solid #ccc;
	height: 30px;

}

</style>

<div id="view" class="container">


<table style="width: 1000px; text-align: center; margin: 0 auto; border-collapse: collapse;">

<tr>
	<th colspan="10" style="text-align: center; border: 1px solid #ccc; font-size: 30px;">${xask.ask_title }</th>
</tr>

<tr>
	<td style="background-color: #ccc; width: 12.5%">문의번호</td>
	<td style="width: 5%">${xask.ask_no }</td>
	<td style="background-color: #ccc; width: 15%">닉네임</td>
	<td style="width: 10%">${nick }</td>
	<td style="background-color: #ccc; width: 12.5%">문의유형</td>
	<td style="width: 10%">${xask.ask_kind }</td>
	<td style="background-color: #ccc; width: 7.5%">답변여부</td>
	<td style="width: 5%">${xask.ask_state }</td>
	<td style="background-color: #ccc; width: 7.5%">작성일</td>
	<td style="width: 15%">${xask.ask_date }</td>
</tr>

<tr>
	<td colspan="10" style="height: 300px; border: 1px solid #ccc;">${xask.ask_content }</td>
</tr>

</table>

<c:if test="${login }"> <!-- 관리자 권한 설정 어떻게 하지.. -->
<div id="answer"></div>
<br>
<div class="container" style="width: 1000px; text-align: center;">
<form method="post" action="<%=request.getContextPath() %>/admin/ask/write?ask_no=${xask.ask_no }">
<input type="hidden" name="admin_id" value="${adminid }" />
	<textarea id="comment" name="comment" style="width: 950px; height: 100px; padding: 10px;"placeholder="문의의 답변을 입력하세요."></textarea>


<br><br>

	<button id="btnList" class="btn btn-default">목록으로</button>
	<button type="submit" id="btnAnswer" class="btn btn-info">답변하기</button>

</form>

</div>
</c:if>

<c:if test="${empty login }">

<div class="container" style="text-align: center;">
<strong>잘못된 접근입니다. 관리자 로그인이 필요합니다.</strong>
<br><br>
<button id="btnList" class="btn btn-default">목록으로</button>
<a href="<%=request.getContextPath() %>/admin"><button id="btnLogin" class="btn btn-info">로그인</button></a>


</div>

</c:if>
</div>

</body>
</html>
