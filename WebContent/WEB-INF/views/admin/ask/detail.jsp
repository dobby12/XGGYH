<%@page import="dto.XAsk"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	XAsk xask = (XAsk) request.getAttribute("xask");
%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript" src="/resources/js/httpRequest.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	//목록 버튼 동작
	$("#btnList").click(function(){
		history.go(-1);
	});

});


window.onload = function(){
	btnAnswer.onclick = function(){
		console.log("btnAnswer called")
		
		sendRequest("POST", "/admin/comment/write", "", callback);
		
	}
}

function callback(){
	if(httpRequest.readyState == 4){
		if(httpRequest.status == 200){
			
			console.log("정상적인 AJAX 요청/응답 성공")
			
			printData();
			
		} else {
			console.log("AJAX 요청/응답 실패")
		}
	}
}

function printData(){
	console.log("printData() called")
	
	//ajax 응답으로 받은 html 코드를 #result에 반영하기
	answer.innerHTML = httpRequest.responseText;
}


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
	<th colspan="10" style="text-align: center; border: 1px solid #ccc; font-size: 30px;">${xask.askTitle }</th>
</tr>

<tr>
	<td style="background-color: #ccc; width: 12.5%">문의번호</td>
	<td style="width: 5%">${xask.askNo }</td>
	<td style="background-color: #ccc; width: 15%">닉네임</td>
	<td style="width: 10%">${nick }</td>
	<td style="background-color: #ccc; width: 12.5%">문의유형</td>
	<td style="width: 10%">${xask.askKind }</td>
	<td style="background-color: #ccc; width: 7.5%">답변여부</td>
	<td style="width: 5%">${xask.askState }</td>
	<td style="background-color: #ccc; width: 7.5%">작성일</td>
	<td style="width: 15%">${xask.askDate }</td>
</tr>

<tr>
	<td colspan="10" style="height: 300px; border: 1px solid #ccc;">${xask.askContent }</td>
</tr>

</table>
<div id="answer"></div>

<c:if test="${login }"> <!-- 관리자 권한 설정 어떻게 하지.. -->
<br>
<div class="container" style="width: 1000px; text-align: center;">
<form method="post" action="<%=request.getContextPath() %>/admin/ask/write?askNo=${xask.askNo }">
<input type="hidden" name="adminId" value="${adminid }" />
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
