<%@page import="dto.XAsk"%>
<%@page import="dto.XComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	XComment xcomment = (XComment) request.getAttribute("xcomment");
	XAsk xask = (XAsk) request.getAttribute("xask");
%>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#btnCancel").click(function(){
		history.go(-1);
	})
	
	$("#btnUpdate").click(function(){
		
		var answer = confirm("댓글을 수정하시겠습니까?")
		
		if( answer == true ){
			
			$("form").submit();
		} else {
			return false;
		}
	})
})

</script>

<style type="text/css">

th {
	height: 50px;
	
}

td {
	border: 1px solid #ccc;
	height: 30px;

}

#answer {
	width: 1000px;
	padding: 10px;
	text-align: center;
	vertical-align: middle;
	
	border: 1px solid #ccc;
	border-collapse: collapse;
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


<div id="answer" class="container">
	<form method="post" action="<%=request.getContextPath() %>/admin/comment/update?commentNo=${xcomment.commentNo }">
	<input type="hidden" name="adminId" value="${adminid }" />
	<input type="hidden" name="askNo" value="${xask.askNo }" />
	
		<textarea id="comment" name="comment" style="width: 950px; height: 100px; padding: 10px;">${xcomment.commentContent }</textarea>
	
	
		<br><br>
	
		<button type="button" id="btnCancel" class="btn btn-default">취소</button>
		<button type="button" id="btnUpdate" class="btn btn-info">수정하기</button>
	
	</form>

</div>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />