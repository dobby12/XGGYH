<%@page import="dto.XAsk"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	XAsk xask = (XAsk) request.getAttribute("xask");
%>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#btnDelete").click(function(){
		
		var answer = confirm("댓글을 정말 삭제하시겠습니까?")
		
		if( answer == true ){
			location.href = "<%=request.getContextPath() %>/admin/comment/delete?askNo=${xask.askNo }";
		} else {
			return false;
		}
	})
	
	$("#btnUpdate").click(function(){
		
		var answer = confirm("댓글을 수정하시겠습니까?")
		
		if(answer == true){
			location.href="<%=request.getContextPath() %>/admin/comment/update?askNo=${xask.askNo }";
		} else {
			return false;
		}
		
	})
	
	$("#btnAnswer").click(function(){
		
		var answer = confirm("댓글을 작성하시겠습니까?")
		
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


#answer {
	width: 1000px;
	padding: 10px 10px 10px 0;
	text-align: center;
	vertical-align: middle;
	
	border: 1px solid #ccc;
	border-collapse: collapse;
}

#cont {
	background-color: #F2F2F2;
	height:70px;
	width: 100%;
	padding:10px;
	
	display: table;
}

#cont p {
	display: table-cell;
	text-align: center;
	vertical-align:middle;
}

</style>

<div id="view" class="container">


<table class="table table-condensed table-bordered" style="width: 1000px; text-align: center; margin: 0 auto; border-collapse: collapse;">

<tr>
	<th colspan="10" style="text-align: center; border: 1px solid #ccc; font-size: 30px;">${xask.askTitle }</th>
</tr>

<tr>
	<td class="item" style="width: 12.5%">문의번호</td>
	<td style="width: 5%">${xask.askNo }</td>
	<td class="item" style="width: 15%">닉네임</td>
	<td style="width: 10%">${nick }</td>
	<td class="item" style="width: 12.5%">문의유형</td>
	<td style="width: 10%">${xask.askKind }</td>
	<td class="item"style="width: 7.5%">답변여부</td>
	<td style="width: 5%">${xask.askState }</td>
	<td class="item" style="width: 7.5%">작성일</td>
	<td style="width: 15%">${xask.askDate }</td>
</tr>

<tr>
	<td colspan="10" style="height: 300px; vertical-align: middle; border: 1px solid #ccc;">${xask.askContent }</td>
</tr>

</table>


<div id="answer" class="container">
<c:if test="${xask.askState == 'y'}">
<table style="width: 1000px;">

<tr>	
	<!-- 아이디 -->
	<td colspan="2" class="ans" width="10%">${xcomment.adminId }</td>
	
	<!-- 본문 -->
	<td colspan="2" width="75%" >
		<div id="cont" class="ans"><p>${xcomment.commentContent }</p></div>
	</td>

		<td style="width: 10%" class="ans">
			<button id="btnUpdate" class="btnUpdate" style="margin: 0 0 5px 0;">수정</button>
			<button id="btnDelete" class="btnDelete">삭제</button>			
		</td>
</tr>

</table>
<br><br>
<a href="<%=request.getContextPath() %>/admin/ask/list"><button id="btnList" class="btnBack">목록</button></a>
</c:if>

<c:if test="${xask.askState == 'n' }">
	
	<br>
	<form method="post" action="<%=request.getContextPath() %>/admin/comment/write?askNo=${xask.askNo }">
	<input type="hidden" name="adminId" value="${adminid }" />
	<input type="hidden" name="askNo" value="${xask.askNo }" />
	
		<textarea id="comment" name="comment" style="width: 950px; height: 100px; padding: 10px;"placeholder="문의의 답변을 입력하세요."></textarea>
	
	
		<br><br>
	
		<a href="<%=request.getContextPath() %>/admin/ask/list"><button type="button" class="btnBack">목록</button></a>
		<button type="button" id="btnAnswer" class="btnSubmit">답변</button>
	
	</form>
	
</c:if>
</div>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />