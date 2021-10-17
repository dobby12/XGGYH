<%@page import="dto.XAsk"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

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
	height: auto;
	margin: 0 auto;
	padding: 10px;
	text-align: center;
	vertical-align: middle;
	
	border: 1px solid #ccc;
	border-collapse: collapse;
}

.cont {
	background-color: #F2F2F2;
	min-height: 70px;
	width: 90%;
	padding:10px;
	margin: 0 auto;
	
	display: table;
}

.cont p {
	display: table-cell;
	text-align: center;
	vertical-align:middle;
}

</style>

<div id="detailAsk" class="container">

<table style="width: 1000px; text-align: center; margin: 0 auto; border-collapse: collapse;">

<tr>
	<th colspan="10" style="text-align: center; border: 1px solid #ccc; font-size: 30px;">${detailAsk.askTitle }</th>
</tr>

<tr>
	<td class="item" style="width: 12.5%">문의번호</td>
	<td style="width: 5%">${detailAsk.askNo }</td>
	<td class="item" style="width: 15%">아이디</td>
	<td style="width: 10%">${detailAsk.memId }</td>
	<td class="item" style="width: 12.5%">문의유형</td>
	<td style="width: 10%">${detailAsk.askKind }</td>
	<td class="item" style="width: 7.5%">답변여부</td>
	<td style="width: 5%">${detailAsk.askState }</td>
	<td class="item" style="width: 7.5%">작성일</td>
	<td style="width: 15%">${detailAsk.askDate }</td>
</tr>

<tr>
	<td colspan="10" style="height: 300px; border: 1px solid #ccc;">${detailAsk.askContent }</td>
</tr>
</table>


<div id="answer">

<c:if test="${not empty AskComment.commentContent }">
	<div class="cont"><p>${AskComment.commentContent }</p></div>
</c:if>

<c:if test="${empty AskComment.commentContent }">
	<div class="cont" style="background: #fff"><p><strong>아직 문의에 대한 답변이 없습니다.</strong></p></div>
</c:if>


</div><!-- #answer end -->
<br><br>
<a href="<%=request.getContextPath() %>/mypage/myask"><button id="btnList" class="btnBack">목록</button></a>

</div><!-- .container end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />