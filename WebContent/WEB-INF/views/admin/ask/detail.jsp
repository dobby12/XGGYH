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
		$(location).attr("href", "/XGGYH/admin/ask/list");
	})
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
	<td colspan="10" style="height: 500px; border: 1px solid #ccc;">${xask.ask_content }</td>
</tr>

</table>

<br>
<div class="text-center">
	<button id="btnList" class="btn btn-default">목록으로</button>
	<button id="btnUpdate" class="btn btn-info">답변하기</button>
</div>


</div>



</body>
</html>
